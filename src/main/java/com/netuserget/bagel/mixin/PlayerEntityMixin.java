package com.netuserget.bagel.mixin;

import com.netuserget.bagel.BagelMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    private PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;updateTurtleHelmet()V"))
    private void bagel$BunnyBoots(CallbackInfo ci) {
        updateBunnyBoots();
    }

    @Unique
    private void updateBunnyBoots() {
        ItemStack bootItem = this.getEquippedStack(EquipmentSlot.FEET);
        if (bootItem.isOf(BagelMod.BUNNY_BOOTS)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10 * 20, 1, false, false, true));
        }
    }
}
