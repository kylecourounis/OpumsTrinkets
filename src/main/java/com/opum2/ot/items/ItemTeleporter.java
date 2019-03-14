package com.opum2.ot.items;

import java.util.List;

import javax.annotation.Nullable;

import com.opum2.ot.utils.Logging;
import com.opum2.ot.utils.ModTeleporter;
import com.opum2.ot.utils.Utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;

public class ItemTeleporter extends ModItem {

	private static int index;

	public ItemTeleporter()
	{
		super("teleporter_item");

		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);

		if (!world.isRemote && player instanceof EntityPlayerMP)
		{
            if (!stack.hasTagCompound())
            {
                NBTTagCompound tag = new NBTTagCompound();
                
                tag.setInteger("count", 0);
                
                stack.setTagCompound(tag);
            }
            else
            {                
                NBTTagCompound tag = stack.getTagCompound();
                int count = tag.getInteger("count");

                int dim = 0;
                BlockPos pos = null;
                
                if (player.isSneaking())
                {
                    if (ItemTeleporter.index >= count)
                    {
                        ItemTeleporter.index = 0;
                    }
                    
                    String[] dest = tag.getString(Integer.toString(ItemTeleporter.index)).split("|");
                    
                    dim = Integer.parseInt(dest[0]);
                    Logging.info(dim);
                    
                    String[] coords = dest[1].substring(1).split(",");
                    Logging.info(Arrays.toString(coords));
                    
                    int[] arr = { Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]) };
                    
                    pos = new BlockPos(arr[0], arr[1], arr[2]);
                    
                    Utils.sendGameInfoMessage(player, (TextFormatting.YELLOW + "Destination: ") + (TextFormatting.AQUA + Integer.toString(ItemTeleporter.index)));
                    
                    ItemTeleporter.index++;                
                }
                else
                {
                    ModTeleporter.teleport((EntityPlayerMP)player, dim, pos, player.rotationYaw);               
                    Utils.sendGameInfoMessage(player, (TextFormatting.YELLOW + "Arrived at destination #") + (TextFormatting.AQUA + Integer.toString(ItemTeleporter.index)) + TextFormatting.RESET + "!");
                }                
            }
		}
		
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = player.getHeldItem(hand);

		if (!world.isRemote)
		{
		    if (!stack.hasTagCompound())
		    {
		        NBTTagCompound tag = new NBTTagCompound();
		        
		        tag.setInteger("count", 0);
		        
		        stack.setTagCompound(tag);
		    }
		    
			if (player.isSneaking())
			{
                NBTTagCompound tag = stack.getTagCompound();

                int count = tag.getInteger("count");
                
                tag.setString(Integer.toString(count), (player.dimension) + "|" + (pos.getX() + "," + pos.getY() + "," + pos.getZ()));

                count += 1;
                tag.setInteger("count", count);
                
                stack.serializeNBT();
                
				return EnumActionResult.SUCCESS;
			}
		}
		else
		{
			Logging.error("Error setting location!");	
		}		
		
		return EnumActionResult.PASS;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{
		String name = "#" + ItemTeleporter.index;

        tooltip.add((TextFormatting.BLUE + "Destination: ") + (TextFormatting.YELLOW + name));
	}
}
