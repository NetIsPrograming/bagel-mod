package com.netuserget.bagel.mixin;

import com.netuserget.bagel.BagelMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    /*
        @author NetUserGet
        @reason When the boots are worn treat every block like a slime block.
    */
    @Inject(method = "onEntityLand", at = @At("HEAD"), cancellable = true)
    private void bagel$Bounciness(BlockView world, Entity entity, CallbackInfo ci){
        if (bagel$CheckForBoots(entity)) {
            bagel$Bounce((LivingEntity) entity); /* We know for sure that this is a LivingEntity */
            ci.cancel();
        }
    }

    /*
        @author NetUserGet
        @reason Having fall damage would make the boots less fun.
    */
    @Inject(method = "onLandedUpon", at = @At("HEAD"), cancellable = true)
    private void bagel$Landing(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if (bagel$CheckForBoots(entity)) {
            entity.handleFallDamage(fallDistance, 0.0f, entity.getDamageSources().fall());
            ci.cancel();
        }
    }

    /*
        @author NetUserGet
        @reason Don't keep bouncing when walking.
    */
    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    private void bagel$Walking(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (bagel$CheckForBoots(entity)) {
            ci.cancel();
        }
    }

    @Unique
    private void bagel$Bounce(LivingEntity entity) {
        Vec3d currentVelocity = entity.getVelocity();
        if (currentVelocity.y < 0.0F) {
            entity.setVelocity(currentVelocity.x, -currentVelocity.y, currentVelocity.z);
        }
    }

    @Unique
    private boolean bagel$CheckForBoots(Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) {
            return false;
        }
        if (livingEntity.bypassesLandingEffects()) {
            return false;
        }
        return livingEntity.getEquippedStack(EquipmentSlot.FEET).isOf(BagelMod.BUNNY_BOOTS);
    }
}

