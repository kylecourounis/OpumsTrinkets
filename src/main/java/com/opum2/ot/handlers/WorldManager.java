package com.opum2.ot.handlers;

import com.opum2.ot.utils.ModTeleporter;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class WorldManager {

    public static MinecraftServer theServer;

    public static void init(MinecraftServer server)
    {
        WorldManager.theServer = server;
    }
    
    public static BlockPos getSpawnCoords(int dimension)
    {
        return WorldManager.theServer.getWorld(dimension).getSpawnPoint();
    }

    public static void transferPlayerToDimension(EntityPlayerMP player, int dimension, BlockPos pos, float yaw)
    {
    	WorldManager.theServer.getPlayerList().transferPlayerToDimension(player, dimension, new ModTeleporter(dimension, pos, yaw));
    }
    
    public static float getSpawnRotation()
    {
        return 0.0F;
    }
}
