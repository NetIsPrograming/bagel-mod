package com.netuserget.bagel.item;

import net.minecraft.entity.TntEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class BombProjectile implements ProjectileItem {
    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        //return new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), null);
        return null;
    }
}
