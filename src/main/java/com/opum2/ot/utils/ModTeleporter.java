package com.opum2.ot.utils;

import com.opum2.ot.blocks.TileEntityTeleporter;
import com.opum2.ot.handlers.WorldManager;
import com.opum2.ot.init.ModBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ModTeleporter extends Teleporter {

    public final WorldServer theWorld;

    public final BlockPos pos;

    public final float rotation;

    public ModTeleporter()
    {
        this(0, new BlockPos(0, -1, 0), 0.0F);
    }

    public ModTeleporter(int dimension, BlockPos pos, float yaw)
    {
        this(WorldManager.theServer.getWorld(dimension), pos, yaw);
    }

    public ModTeleporter(WorldServer world, BlockPos pos, float yaw)
    {
        super(world);

        this.theWorld = world;
        this.pos = pos;
        this.rotation = yaw;
    }

    public static void teleport(EntityPlayerMP player)
    {
        ModTeleporter.teleport(player, 0, WorldManager.getSpawnCoords(player.getSpawnDimension()), WorldManager.getSpawnRotation());
    }

    public static void teleport(EntityPlayerMP player, int dimension, BlockPos pos, float yaw)
    {
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();
    	
        if (player.dimension == dimension)
        {
            player.rotationYaw = yaw;
            player.setPositionAndUpdate(x + 0.5, y + 1, z + 0.5);

            if (player.isBurning())
            {
                player.extinguish();
            }
        } 
        else
        {
            player.timeUntilPortal = player.getPortalCooldown();
            WorldManager.transferPlayerToDimension(player, dimension, pos, yaw);
        }
    }

    public void buildPortal(World world, BlockPos pos)
    {
        world.setBlockState(pos, ModBlocks.TELEPORTER.getDefaultState(), 0);

        try
        {
            TileEntityTeleporter teleportBlock = (TileEntityTeleporter) world.getTileEntity(pos);

            teleportBlock.dimension = this.theWorld.provider.getDimension();
            teleportBlock.pos = this.pos;
            teleportBlock.rotation = this.rotation;
        } 
        catch (Exception ex)
        {
            Logging.error("Error fetching teleporter entity! Teleporter failed: " + this.theWorld.provider.getDimension() + "@" + Utils.posToString(pos));
            ex.printStackTrace();
            
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);
        }
    }

    @Override
    public void placeInPortal(Entity entity, float yaw)
    {
    	int x = this.pos.getX();
    	int y = this.pos.getY();
    	int z = this.pos.getZ();
    	
        if (y < 0)
        {
            BlockPos spawnCoords = WorldManager.getSpawnCoords(entity.dimension);
            entity.setLocationAndAngles(spawnCoords.getX() + 0.5, spawnCoords.getY(), spawnCoords.getZ() + 0.5, WorldManager.getSpawnRotation(), 0.0F);
        }
        else
        {
            entity.setLocationAndAngles(x + 0.5, y, z + 0.5, this.rotation, 0.0F);
        }

        entity.motionX = entity.motionY = entity.motionZ = 0.0;
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float yaw)
    {
        this.placeInPortal(entity, yaw);
        return true;
    }

    @Override
    public boolean makePortal(Entity entity)
    {
        return true;
    }

    @Override
    public void removeStalePortalLocations(long worldTime)
    {
    }
}
