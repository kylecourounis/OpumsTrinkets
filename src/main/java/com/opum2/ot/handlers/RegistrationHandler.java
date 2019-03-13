package com.opum2.ot.handlers;

import com.opum2.ot.OpumsTrinkets;
import com.opum2.ot.init.ModBlocks;
import com.opum2.ot.init.ModItems;
import com.opum2.ot.utils.Logging;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistrationHandler {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        Logging.debug("Initialized Blocks.");
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
        Logging.debug("Initialized Items.");
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        OpumsTrinkets.proxy.registerRenders();
        Logging.debug("Initialized Renders.");
    }
}
