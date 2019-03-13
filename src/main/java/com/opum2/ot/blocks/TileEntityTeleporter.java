package com.opum2.ot.blocks;

import com.opum2.ot.utils.Logging;
import com.opum2.ot.utils.ModTeleporter;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityTeleporter extends TileEntity {

    public int dimension;
    public BlockPos pos;
    public float rotation;

    public void teleport(EntityPlayerMP player)
    {
        if (this.pos.getY() <= 0)
        {
            Logging.info("Cannot teleport if Y level is <= 0!");
        } 
        else
        {
            ModTeleporter.teleport(player, this.dimension, this.pos, this.rotation = player.rotationYaw);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        tag.setInteger("dimension", this.dimension);
        tag.setIntArray("coords", new int[] { this.pos.getX(), this.pos.getY(), this.pos.getZ() });

        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        this.dimension = tag.getInteger("dimension");
        
        int[] coords = tag.getIntArray("coords");
        this.pos = new BlockPos(coords[0], coords[1], coords[2]);
    }
}
