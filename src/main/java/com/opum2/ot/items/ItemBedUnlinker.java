package com.opum2.ot.items;

import java.util.List;

import javax.annotation.Nullable;

import com.opum2.ot.init.ModCreativeTab;
import com.opum2.ot.utils.Utils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBedUnlinker extends ModItem {

    private BlockPos hitPos;

    public ItemBedUnlinker()
    {
        super("bed_unlinker");

        this.setMaxStackSize(1);

        this.setCreativeTab(ModCreativeTab.MOD_TAB);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack item = player.getHeldItem(hand);

        player.setSpawnPoint(null, true);
        item.setCount(item.getCount() - 1);

        Utils.sendGameInfoMessage(player, TextFormatting.GREEN + "Right click a bed to set a new spawnpoint.");

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
    {
        tooltip.add("Resets your spawn.");
        tooltip.add("To set your spawn again, sleep like normal.");
    }
}
