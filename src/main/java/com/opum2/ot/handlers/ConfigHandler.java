package com.opum2.ot.handlers;

import com.opum2.ot.ModInfo;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, category = "", type = Type.INSTANCE)
@Config.LangKey("config.title")
public class ConfigHandler {

    @Config.Name("general")
    @Config.LangKey("config.category:general")
    public static final General GENERAL = new General();

    @Config.Name("tweaks")
    @Config.LangKey("config.category:tweaks")
    public static final Tweaks TWEAKS = new Tweaks();

    @Config.Name("registry")
    @Config.LangKey("config.category:registry")
    public static final Registry REGISTRY = new Registry();
    
    public static class General 
    {
        @Config.Name("client")
        @Config.LangKey("config.category:client")
        public final Client client = new General.Client();
        
        public static class Client
        {
        }
    }
    
    public static class Registry
    {
        @Config.Name("blocks")
        @Config.LangKey("config.category:blocks")
        public final Blocks blocks = new Registry.Blocks();

        @Config.Name("items")
        @Config.LangKey("config.category:items")
        public final Items items = new Registry.Items();

        public static class Blocks 
        {
            @Config.LangKey("config.option:enableTeleporter")
            @Config.Comment({ "Enable/disable the teleporter block.", "This will also toggle the item that is used to link the portals." })
            public boolean enableTeleporter = true;
        }

        public static class Items
        {
            @Config.LangKey("config.option:enableBuilder")
            @Config.Comment({ "Enable/disable the Builder item." })
            public boolean enableBuilder = true;
        }
    }

    public static class Tweaks
    {
        @Config.LangKey("config.option:enableSleepingTweaks")
        public boolean enableSleepingTweaks = true;
        
        @Config.LangKey("config.option:enableChatTweaks")
        public boolean enableChatTweaks = true;

        @Config.Name("chat")
        @Config.LangKey("config.category:chat")
        public final Tweaks.Chat chat = new Tweaks.Chat();

        @Config.Name("multiplayer")
        @Config.LangKey("config.category:multiplayer")
        public final Tweaks.Multiplayer multiplayer = new Tweaks.Multiplayer();
        
        @Config.Name("sleeping")
        @Config.LangKey("config.category:sleeping")
        public final Tweaks.Sleeping sleeping = new Tweaks.Sleeping();

        public static class Chat 
        {
            @Config.LangKey("config.option:disableGiveResponse")
            @Config.Comment({ "Enable/disable the response in chat when a player gives themselves an item." })
            public boolean disableGiveOutput = true;      
        }
        
        public static class Multiplayer 
        {
            @Config.LangKey("config.option:bannedWords")
            @Config.Comment({ "Words that are banned from chat." })
            public String[] bannedWords = {};            
        }
        
        public static class Sleeping 
        {
            @Config.LangKey("config.option:sleepingSetsSpawn")
            @Config.Comment({ "When true, sleeping in a bed will set the player's spawn point.", "Default is true." })
            public boolean sleepingSetsSpawn = true;

            @Config.LangKey("config.option:setSpawnAnytime")
            @Config.Comment({ "When true, the player can set their spawn point by right clicking on a bed at any time.", "'sleepingSetsSpawn' will override this when set to false.", "Default is true." })
            public boolean setSpawnAnytime = true;
        }
    }

    @Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
    private static class EventHandler 
    {
        @SubscribeEvent
        public static void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(ModInfo.MOD_ID))
            {
                ConfigManager.sync(ModInfo.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
