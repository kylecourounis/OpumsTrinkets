package com.opum2.ot;

import com.opum2.ot.client.gui.ModGuiHandler;
import com.opum2.ot.commands.PlayerPanelCommand;
import com.opum2.ot.events.EventHandlers;
import com.opum2.ot.handlers.WorldManager;
import com.opum2.ot.init.ModBlocks;
import com.opum2.ot.init.ModItems;
import com.opum2.ot.init.ModRecipes;
import com.opum2.ot.init.ModStructures;
import com.opum2.ot.proxy.IProxy;
import com.opum2.ot.utils.Logging;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION)
public class OpumsTrinkets {

    @Mod.Instance
    public static OpumsTrinkets instance;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static SimpleNetworkWrapper network;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        ModItems.init();
        
        ModStructures.init();
        Logging.debug("Initialized Structures.");

        OpumsTrinkets.proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModRecipes.init();
        Logging.debug("Initialized Recipes.");
        
        OpumsTrinkets.network = NetworkRegistry.INSTANCE.newSimpleChannel("net." + ModInfo.MOD_ID);
        NetworkRegistry.INSTANCE.registerGuiHandler(OpumsTrinkets.instance, new ModGuiHandler());

        OpumsTrinkets.proxy.init();        
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        EventHandlers.init();
        Logging.debug("Initialized Events.");

        OpumsTrinkets.proxy.postInit();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
    	WorldManager.init(event.getServer());
        Logging.debug("Initialized World Manager.");
    	
        event.registerServerCommand(new PlayerPanelCommand());
        Logging.debug("Initialized Commands.");
    }
}
