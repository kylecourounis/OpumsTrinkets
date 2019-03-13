package com.opum2.ot.init;

import java.util.ArrayList;
import java.util.List;

import com.opum2.ot.ModInfo;
import com.opum2.ot.blocks.BlockTeleporter;
import com.opum2.ot.blocks.TileEntityTeleporter;
import com.opum2.ot.handlers.ConfigHandler;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block TELEPORTER = new BlockTeleporter();

    public static void init()
    {
        if (ConfigHandler.GENERAL.registry.blocks.enableTeleporter)
        {        	
            ModBlocks.registerBlock(ModBlocks.TELEPORTER);
        }

        ModBlocks.registerTileEntities();
    }

    private static void registerBlock(Block block)
    {
        ModBlocks.BLOCKS.add(block);
        ModItems.ITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    } 

    private static void registerTileEntities()
    {
        if (ConfigHandler.GENERAL.registry.blocks.enableTeleporter)
        {
            TileEntity.register(ModInfo.MOD_ID + ":teleporter", TileEntityTeleporter.class);
        }
    }
}
