package com.opum2.ot.events;

import net.minecraftforge.common.MinecraftForge;

public class EventHandlers {

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
        MinecraftForge.EVENT_BUS.register(new ChatEvents());
    }
}
