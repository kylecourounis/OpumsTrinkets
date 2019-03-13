package com.opum2.ot.structures;

import com.opum2.ot.ModInfo;
import com.opum2.ot.utils.Logging;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class House extends ModStructure {

    public House(World world)
    {
        super(world);

        this.setName("House");
    }

    @Override
    public void build(BlockPos pos)
    {
        World world = this.getWorld();

        if (!world.isRemote)
        {
            WorldServer worldServer = (WorldServer)world;

            MinecraftServer server = world.getMinecraftServer();
            TemplateManager manager = worldServer.getStructureTemplateManager();
            Template template = manager.get(server, new ResourceLocation(ModInfo.MOD_ID, "house"));

            if (template == null)
            {
                Logging.error("Failed to find structure.");
            }

            BlockPos pos2 = template.getSize();

            Logging.info("Size - X: " + pos2.getX() + ", Y: " + pos2.getY() + ", Z: " + pos2.getZ());

            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);

            PlacementSettings settings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE)
                    .setIgnoreEntities(false).setChunk((ChunkPos) null).setReplacedBlock((Block) null)
                    .setIgnoreStructureBlock(false);

            template.addBlocksToWorldChunk(world, pos.add(0, 1, 0), settings);
        }
    }
}
