package com.opum2.ot.init;

import java.util.ArrayList;
import java.util.List;

import com.opum2.ot.handlers.ConfigHandler;
import com.opum2.ot.items.ItemBedUnlinker;
import com.opum2.ot.items.ItemBuilder;
import com.opum2.ot.items.ItemPortalLink;

import net.minecraft.item.Item;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item BUILDER = new ItemBuilder();
    public static final Item PORTAL_LINK = new ItemPortalLink();
    public static final Item BED_UNLINKER = new ItemBedUnlinker();
    
    public static void init()
    {
        if (ConfigHandler.REGISTRY.items.enableBuilder)
        {
            ModItems.ITEMS.add(ModItems.BUILDER);
        }

        if (ConfigHandler.REGISTRY.blocks.enableTeleporter)
        {
            ModItems.ITEMS.add(ModItems.PORTAL_LINK);
        }

        if (ConfigHandler.TWEAKS.enableSleepingTweaks || ConfigHandler.TWEAKS.sleeping.sleepingSetsSpawn)
        {
            ModItems.ITEMS.add(ModItems.BED_UNLINKER);
        }
    }
}
