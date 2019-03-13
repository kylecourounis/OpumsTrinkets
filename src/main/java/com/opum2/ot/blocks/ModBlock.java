package com.opum2.ot.blocks;

import com.opum2.ot.init.ModCreativeTab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlock extends Block {

    public ModBlock(Material material, String name)
    {
        super(material);

        this.setUnlocalizedName(name);
        this.setRegistryName(name);

        this.setCreativeTab(ModCreativeTab.MOD_TAB);
    }
}
