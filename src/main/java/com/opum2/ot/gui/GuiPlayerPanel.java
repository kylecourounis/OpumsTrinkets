package com.opum2.ot.gui;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.opum2.ot.ModInfo;
import com.opum2.ot.utils.Logging;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPlayerPanel extends GuiScreen {

    public EntityPlayer selectedPlayer;

    private float xSize;
    private float ySize;

    private int guiWidth = 256;
    private int guiHeight = 256;

    private static final ResourceLocation TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":textures/gui/container/player_panel.png");

    public GuiPlayerPanel(EntityPlayer player)
    {
        this.selectedPlayer = player;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        // this.fontRenderer.drawString(GuiPlayerPanel.selectedPlayer.getName(),
        // this.guiLeft / 2 -
        // this.fontRenderer.getStringWidth(I18n.format(GuiPlayerPanel.selectedPlayer.getName(),
        // new Object[0])) / 2, 4, 4210752);

        this.buttonList.add(new GuiButton(1, 100, 200, 100, 20, "Test"));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 1)
        {
            Logging.info("Button #1 pressed!");
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        this.xSize = mouseX;
        this.ySize = mouseY;
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        this.mc.getTextureManager().bindTexture(GuiPlayerPanel.TEXTURE);

        int x = (this.width - 218) / 2;
        int y = (this.height - 144) / 2;

        this.drawTexturedModalRect(x, y, 0, 0, 218, 144);

        GuiPlayerPanel.drawPlayerModel((x + 51), (y + 73), 30, (x + 51 - this.xSize), (y + 75 - 50 - this.ySize), this.selectedPlayer);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private static void drawPlayerModel(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity)
    {
        GuiInventory.drawEntityOnScreen(x, y, scale, yaw, pitch, entity);
    }
}