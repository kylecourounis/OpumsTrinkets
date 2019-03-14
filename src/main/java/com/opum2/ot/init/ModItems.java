package com.opum2.ot.init;

import java.util.ArrayList;
import java.util.List;

import com.opum2.ot.ModInfo;
import com.opum2.ot.handlers.ConfigHandler;
import com.opum2.ot.items.ItemBedUnlinker;
import com.opum2.ot.items.ItemBuilder;
import com.opum2.ot.items.ItemPortalLink;
import com.opum2.ot.items.ItemTeleporter;
import com.opum2.ot.items.armor.ItemTestArmor;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final ArmorMaterial ARMOR_MATERIAL = EnumHelper.addArmorMaterial("test", ModInfo.MOD_ID + ":test", 500, new int[] { 3, 6, 8, 3 }, 2,  SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    
    public static final Item BUILDER = new ItemBuilder();
    
    public static final Item PORTAL_LINK = new ItemPortalLink();
    public static final Item TELEPORTER = new ItemTeleporter();
    
    public static final Item BED_UNLINKER = new ItemBedUnlinker();

    public static final Item TEST_HELMET = new ItemTestArmor(ModItems.ARMOR_MATERIAL, 0, EntityEquipmentSlot.HEAD, "test_helmet");
    public static final Item TEST_CHEST = new ItemTestArmor(ModItems.ARMOR_MATERIAL, 0, EntityEquipmentSlot.CHEST, "test_chestplate");
    public static final Item TEST_LEGS = new ItemTestArmor(ModItems.ARMOR_MATERIAL, 0, EntityEquipmentSlot.LEGS, "test_leggings");
    public static final Item TEST_BOOTS = new ItemTestArmor(ModItems.ARMOR_MATERIAL, 0, EntityEquipmentSlot.FEET, "test_boots");
    
    public static void init()
    {
        if (ConfigHandler.REGISTRY.items.enableBuilder)
        {
            ModItems.ITEMS.add(ModItems.BUILDER);
        }

        if (ConfigHandler.REGISTRY.blocks.enableTeleporter)
        {
            ModItems.ITEMS.add(ModItems.TELEPORTER);
        }        
        
        if (ConfigHandler.REGISTRY.blocks.enableTeleporter)
        {
            ModItems.ITEMS.add(ModItems.PORTAL_LINK);
        }

        if (ConfigHandler.TWEAKS.enableSleepingTweaks || ConfigHandler.TWEAKS.sleeping.sleepingSetsSpawn)
        {
            ModItems.ITEMS.add(ModItems.BED_UNLINKER);
        }
        
        ModItems.ITEMS.add(ModItems.TEST_HELMET);
        ModItems.ITEMS.add(ModItems.TEST_CHEST);
        ModItems.ITEMS.add(ModItems.TEST_LEGS);
        ModItems.ITEMS.add(ModItems.TEST_BOOTS);        
    }
}
