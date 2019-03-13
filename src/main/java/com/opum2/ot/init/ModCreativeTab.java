package com.opum2.ot.init;

import com.opum2.ot.ModInfo;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCreativeTab {

    public static final CreativeTabs MOD_TAB = new CreativeTabs(ModInfo.MOD_ID)
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.BUILDER);
        }
    };
}
