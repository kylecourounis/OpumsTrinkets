package com.opum2.ot.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class Utils {

    public static String posToString(BlockPos pos)
    {
        return "[" + pos.getX() + "," + pos.getY() + "," + pos.getZ() + "]";
    }

    public static void sendGameInfoMessage(EntityPlayer player, String message)
    {
        if (player instanceof EntityPlayerMP)
        {
        	((EntityPlayerMP)player).sendStatusMessage(new TextComponentString(message), true);
        }
    }
}
