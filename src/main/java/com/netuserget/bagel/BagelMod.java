package com.netuserget.bagel;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BagelMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MODID = "bagel";
    public static final Logger LOGGER = LoggerFactory.getLogger("bagel");

    public static final ArmorItem BUNNY_BOOTS = new ArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(6)));

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Its like a everything bagel! (BagelMod Initialization)");
        Registry.register(Registries.ITEM, new Identifier(MODID, "bunnyboots"), BUNNY_BOOTS);
    }
}
