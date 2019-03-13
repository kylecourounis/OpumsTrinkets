package com.opum2.ot.items;

import java.util.List;

import javax.annotation.Nullable;

import com.opum2.ot.blocks.TileEntityTeleporter;
import com.opum2.ot.init.ModCreativeTab;
import com.opum2.ot.utils.Utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPortalLink extends ModItem {

    private BlockPos hitPos;

    public ItemPortalLink()
    {
        super("portal_link");

        this.setMaxStackSize(1);

        this.setCreativeTab(ModCreativeTab.MOD_TAB);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);

        if (!stack.hasTagCompound())
        {
        	stack.setTagCompound(new NBTTagCompound());
        }

        if (!world.isRemote)
        {
            TileEntity tileEntity = world.getTileEntity(pos);

            if (tileEntity instanceof TileEntityTeleporter)
            {
                TileEntityTeleporter teleporter = (TileEntityTeleporter)tileEntity;

                if (this.hitPos == null)
                {
                    Utils.sendGameInfoMessage(player, TextFormatting.YELLOW + "Attached to teleporter @ " + Utils.posToString(pos) + ".");

                    NBTTagCompound tag = stack.getTagCompound();

                    tag.setInteger("dimension", player.dimension);
                    tag.setIntArray("coords", new int[] { pos.getX(), pos.getY(), pos.getZ() });

                    stack.serializeNBT();

                    this.hitPos = pos;
                } 
                else
                {
                    NBTTagCompound compound = stack.getTagCompound();

                    int dimension = compound.getInteger("dimension");
                    int[] coords = compound.getIntArray("coords");

                    BlockPos pos1 = new BlockPos(coords[0], coords[1], coords[2]);
                    BlockPos pos2 = teleporter.getPos();
                    
                    TileEntity te = world.getTileEntity(pos1);

                    if (te instanceof TileEntityTeleporter)
                    {
                        TileEntityTeleporter tp = (TileEntityTeleporter) te;

                        tp.dimension = player.dimension;
                        tp.pos = pos2;
                    }

                    teleporter.dimension = dimension;
                    teleporter.pos = pos1;

                    stack.setCount(stack.getCount() - 1);

                    Utils.sendGameInfoMessage(player, TextFormatting.GREEN + "Successfully linked to teleporter @ " + Utils.posToString(pos2) + "!");

                    this.hitPos = null;
                }

                return EnumActionResult.SUCCESS;
            } 
            else
            {
                Utils.sendGameInfoMessage(player, "Not a teleporter block!");
            }
        }

        return EnumActionResult.PASS;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
    {
        if (this.isLinked(stack))
        {
            tooltip.add(TextFormatting.BLUE + "Linked to " + TextFormatting.YELLOW + "[" + this.hitPos.getX() + "," + this.hitPos.getY() + "," + this.hitPos.getZ() + "]");
        }
        else
        {
            tooltip.add(TextFormatting.RED + "Unlinked.");
        }
    } 
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return this.isLinked(stack);
    }
    
    private boolean isLinked(ItemStack stack)
    {
    	return (stack.hasTagCompound()) && (this.hitPos != null);
    }
}
