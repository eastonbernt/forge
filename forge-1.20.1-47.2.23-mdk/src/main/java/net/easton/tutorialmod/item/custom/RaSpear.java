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
        private final Map<Player, Long> cooldowns;
    private final List<MobEffectInstance> effects;


    public RaSpear(Properties pProperties) {
        super(pProperties);
        effects = new ArrayList<>();
        // Add desired effects to the list
        effects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1)); // Strength effect for 30 seconds
        effects.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1)); // Speed effect for 30 seconds
        effects.add(new MobEffectInstance(MobEffects.REGENERATION, 300, 1)); // Speed effect for 30 seconds
        // Add more effects as needed

        //this.cooldownTime = 400;
        this.cooldowns = new HashMap<>();
    }

    // Method to apply effects when the item is used
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            if (!isOnCooldown(player)) {
                applyEffects(player);
                startCooldown(player);
                return InteractionResultHolder.success(player.getItemInHand(hand));
            } else {
                return InteractionResultHolder.fail(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    // Method to apply effects to the player
    private void applyEffects(Player player) {
        // Apply desired effects to the player
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1)); // Strength effect for 15 seconds
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1)); // Speed effect for 15 seconds
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1)); // Regeneration effect for 15 seconds
        // Add more effects as needed
    }

    // Method to start cooldown for a player
    private void startCooldown(Player player) {
        cooldowns.put(player, player.getCommandSenderWorld().getGameTime() + (30 * 30)); // Cooldown time: 45 seconds
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