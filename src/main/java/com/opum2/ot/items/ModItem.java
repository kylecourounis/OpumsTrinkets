package com.opum2.ot.items;

import com.opum2.ot.init.ModCreativeTab;

import net.minecraft.item.Item;

public class ModItem extends Item {

    public ModItem(String name)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ModCreativeTab.MOD_TAB);
    }
}
