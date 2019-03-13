package com.opum2.ot.proxy;

import com.opum2.ot.ModInfo;
import com.opum2.ot.init.ModBlocks;
import com.opum2.ot.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders()
    {
        for (final Block block : ModBlocks.BLOCKS)
        {
            ClientProxy.registerItemModel(Item.getItemFromBlock(block), 0);
        }
        for (final Item item : ModItems.ITEMS)
        {
            ClientProxy.registerItemModel(item, 0);
        }
    }

    private static void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    @Override
    public void openGui(GuiScreen screen)
    {
        Minecraft.getMinecraft().displayGuiScreen(screen);
    }
}
