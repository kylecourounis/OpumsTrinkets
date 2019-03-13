package com.opum2.ot.items;

import java.util.List;

import javax.annotation.Nullable;

import com.opum2.ot.init.ModStructures;
import com.opum2.ot.structures.ModStructure;
import com.opum2.ot.utils.Logging;
import com.opum2.ot.utils.Utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBuilder extends ModItem {

	private static int index;

	private static ModStructure structure;

	public ItemBuilder()
	{
		super("builder");

		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
		if (!world.isRemote && player.isSneaking())
		{				
			if (ItemBuilder.index >= ModStructures.STRUCTURES.size())
			{
				ItemBuilder.index = 0;
			}
			
			ItemBuilder.structure = ModStructures.STRUCTURES.get(index);
			ItemBuilder.structure.setWorld(world);					

			Utils.sendGameInfoMessage(player, (TextFormatting.YELLOW + "Structure: ") + (TextFormatting.AQUA + ItemBuilder.structure.getName()));
			
			ItemBuilder.index++;
		}
		
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack item = player.getHeldItem(hand);

		if (!world.isRemote)
		{
			if (player.isSneaking())
			{	
				if (ItemBuilder.index >= ModStructures.STRUCTURES.size())
				{
					ItemBuilder.index = 0;
				}
				
				ItemBuilder.structure = ModStructures.STRUCTURES.get(index);
				ItemBuilder.structure.setWorld(world);					
				
				Utils.sendGameInfoMessage(player, (TextFormatting.YELLOW + "Structure: ") + (TextFormatting.AQUA + ItemBuilder.structure.getName()));
				
				index++;
				
				return EnumActionResult.PASS;
			}
			else
			{
				if (ItemBuilder.structure != null)
				{
					ItemBuilder.structure.build(pos);	
					Utils.sendGameInfoMessage(player, (TextFormatting.GREEN + "Built ") + (TextFormatting.AQUA + ItemBuilder.structure.getName()) + (TextFormatting.RESET + "!"));

					return EnumActionResult.SUCCESS;
				}
				else
				{
					player.sendMessage(new TextComponentString(TextFormatting.RED + "No structure selected! To select a structure, shift + right click while holding this item."));
				}				
			}
		}
		else
		{
			Logging.error("Error building structure!");	
		}		
		
		return EnumActionResult.PASS;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{
		String name = "Not Set";
		
		if (ItemBuilder.structure != null)
		{
			name = ItemBuilder.structure.getName();
			tooltip.add((TextFormatting.GREEN + "Structure: ") + (TextFormatting.AQUA + name));
		}
		else
		{
			tooltip.add((TextFormatting.GREEN + "Structure: ") + (TextFormatting.AQUA + name));
		}
	}
}
