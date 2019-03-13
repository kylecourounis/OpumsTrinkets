package com.opum2.ot.utils;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Builder {

    public static void placeBlock(World world, int x, int y, int z, Block block, int meta)
    {
        world.setBlockState(new BlockPos(x, y, z), block.getStateFromMeta(meta), 11);
    }

    public static void placeBlock(World world, BlockPos pos, Block block, int meta)
    {
        world.setBlockState(pos, block.getStateFromMeta(meta), 11);
    }

    public static void removeBlock(World world, int x, int y, int z)
    {
        world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 11);
    }

    public static void removeBlock(World world, BlockPos pos)
    {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
    }
}
