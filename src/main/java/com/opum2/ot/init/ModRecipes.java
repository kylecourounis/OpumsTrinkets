package com.opum2.ot.init;

import com.opum2.ot.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

    private static final ResourceLocation GROUP = new ResourceLocation(ModInfo.MOD_NAME);

    public static void init()
    {
        ModRecipes.addShapedRecipe(ModBlocks.TELEPORTER, 2, 0, new Object[] { "#I#", "IEI", "#I#", 'I', Blocks.IRON_BLOCK, 'E', Items.ENDER_PEARL, '#', Blocks.OBSIDIAN });
    }

    private static void addShapedRecipe(Block block, int quantity, int meta, Object... params)
    {
        GameRegistry.addShapedRecipe(block.getRegistryName(), ModRecipes.GROUP, new ItemStack(block, quantity, meta), params);
    }

    private static void addShapedRecipe(Item item, int quantity, int meta, Object... params)
    {
        GameRegistry.addShapedRecipe(item.getRegistryName(), ModRecipes.GROUP, new ItemStack(item, quantity, meta), params);
    }

    private static void addSmeltingRecipe(ItemStack input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }
}
