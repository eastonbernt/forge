package net.easton.tutorialmod.item.custom;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaSpear extends Item {
    private final List<MobEffectInstance> effects;
    private final int cooldownTime; // Cooldown time in ticks
    private final Map<Player, Long> cooldowns;

    public RaSpear(Properties pProperties) {
        super(pProperties);
        effects = new ArrayList<>();
        // Add desired effects to the list
        effects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1)); // Strength effect for 30 seconds
        effects.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0)); // Speed effect for 30 seconds
        effects.add(new MobEffectInstance(MobEffects.REGENERATION, 300, 0)); // Speed effect for 30 seconds
        // Add more effects as needed

        this.cooldownTime = 400;
        this.cooldowns = new HashMap<>();
    }

    // Method to apply effects when the item is used
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        // Check cooldown
        if (!isOnCooldown(pPlayer)) {
            // Apply all effects to the player
            applyEffects(pPlayer);
            // Start cooldown
            startCooldown(pPlayer);
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    // Method to apply all effects to the player
    private void applyEffects(Player player) {
        for (MobEffectInstance effect : effects) {
            // Check if the player is not already affected by the same effect
            if (!player.hasEffect(effect.getEffect())) {
                // Apply the effect to the player
                player.addEffect(effect);
            }
        }
    }

    // Method to start cooldown for a player
    private void startCooldown(Player player) {
        cooldowns.put(player, player.getCommandSenderWorld().getGameTime() + cooldownTime);
    }

    // Method to check if a player is on cooldown
    private boolean isOnCooldown(Player player) {
        if (!cooldowns.containsKey(player)) {
            return false;
        }
        long cooldownEndTime = cooldowns.get(player);
        return player.getCommandSenderWorld().getGameTime() < cooldownEndTime;
    }
}