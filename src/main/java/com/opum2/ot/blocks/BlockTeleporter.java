package com.opum2.ot.blocks;

import com.opum2.ot.init.ModItems;
import com.opum2.ot.utils.Logging;
import com.opum2.ot.utils.Utils;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTeleporter extends ModBlock implements ITileEntityProvider {

    public BlockTeleporter()
    {
        super(Material.PISTON, "teleporter");

        this.setHardness(0.75F);
        this.setLightLevel(0.5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityTeleporter();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!player.getEntityWorld().isRemote && player instanceof EntityPlayerMP)
        {
            try
            {
                if (player.getHeldItem(hand).getItem() == ModItems.PORTAL_LINK)
                {
                    // ..
                }
                else
                {
                    ((TileEntityTeleporter)world.getTileEntity(pos)).teleport((EntityPlayerMP) player);
                }
            } 
            catch (Exception ex)
            {
                Logging.error("Error fetching teleporter entity! Teleporter failed: @" + Utils.posToString(pos) + "@DIM" + player.dimension);
                ex.printStackTrace();
            }
        }

        return true;
    }
}
