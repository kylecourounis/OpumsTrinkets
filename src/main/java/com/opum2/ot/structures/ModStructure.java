package com.opum2.ot.structures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ModStructure {

    private World world;

    private String name;

    public ModStructure(World world)
    {
    	this.setWorld(world);
        this.setName("None");
    }

    public abstract void build(BlockPos pos);

    public World getWorld()
    {
        return this.world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
