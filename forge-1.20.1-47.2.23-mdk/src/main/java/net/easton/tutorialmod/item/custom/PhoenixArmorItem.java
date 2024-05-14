package net.easton.tutorialmod.item.custom;

import net.easton.tutorialmod.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import com.google.common.collect.ImmutableMap;
import net.easton.tutorialmod.TutorialMod;
import net.easton.tutorialmod.item.PhoenixArmorMaterials;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.client.extensions.common.IClientItemExtensions;
//import software.bernie.geckolib.animatable.GeoItem;
//import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
//import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
//import software.bernie.geckolib.core.animation.AnimatableManager;
//import software.bernie.geckolib.core.animation.Animation;
//import software.bernie.geckolib.core.animation.RawAnimation;
//import software.bernie.geckolib.core.object.PlayState;
//import software.bernie.geckolib.core.animation.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.HashMap;
import java.util.Map;


import java.util.Map;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PhoenixArmorItem extends ArmorItem {
    private static final Map<Player, Long> resurrectionCooldowns = new HashMap<>();
    private static final int COOLDOWN_PERIOD_TICKS = 20 * 60; // 60 seconds cooldown


    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(PhoenixArmorMaterials.PHOENIX_FORGED_STEEL, new MobEffectInstance(MobEffects.REGENERATION, 200, 0,
                            false, false, true)).build();


    public PhoenixArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
        MinecraftForge.EVENT_BUS.register(this);
    }


    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide()) {
            if (hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }

    private static boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    // Event listener for player death
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!player.level().isClientSide && hasFullSuitOfArmorOn(player) && !isOnCooldown(player)) {
                applyResurrection(player);
                // Start cooldown
                startCooldown(player);
                // Cancel PlayerDropsEvent to prevent the death screen
                event.setCanceled(true);
            }
        }
    }

    // Resurrection logic
    private static void applyResurrection(Player player) {
        Level level = player.level();
        player.setHealth(1.0f); // Set player health to 1 heart
        player.clearFire(); // Remove fire damage effect
        player.removeAllEffects(); // Remove all negative effects
        // You may want to teleport the player to a safe location here
        // Apply any additional effects or conditions for resurrection
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1)); // Example: Apply regeneration effect

        // Get the resurrection sound from ModSounds class
        SoundEvent resurrectionSound = ModSounds.RESURRECTION_SOUND.get();

        // Get another sound from vanilla Minecraft
        SoundEvent vanillaSound = SoundEvents.TOTEM_USE;

        // Play both sounds simultaneously
        playResurrectionSound(level, player.getX(), player.getY(), player.getZ(),resurrectionSound, vanillaSound);


    }

    private static void playResurrectionSound(Level level, double x, double y, double z, SoundEvent... soundEvents) {
        for (SoundEvent soundEvent : soundEvents) {
            level.playSound(null, x, y, z, soundEvent, SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }

    // Method to start cooldown for a player
    private static void startCooldown(Player player) {
        resurrectionCooldowns.put(player, player.getCommandSenderWorld().getGameTime());
    }

    // Method to check if a player is on cooldown
    private static boolean isOnCooldown(Player player) {
        if (!resurrectionCooldowns.containsKey(player)) {
            return false;
        }
        long cooldownStartTime = resurrectionCooldowns.get(player);
        long currentTime = player.getCommandSenderWorld().getGameTime();
        return currentTime - cooldownStartTime < COOLDOWN_PERIOD_TICKS;
    }
}

