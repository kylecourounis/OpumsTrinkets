package com.opum2.ot.proxy;

import net.minecraft.client.gui.GuiScreen;

public interface IProxy 
{
    public void preInit();
    
    public void init();

    public void postInit();

    public void registerRenders();

    public void openGuiScreen(GuiScreen screen);
}