package com.opum2.ot.events;

import com.opum2.ot.handlers.ConfigHandler;
import com.opum2.ot.utils.Utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvents {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onServerChat(ServerChatEvent event)
    {
    	String message = event.getMessage();
    	
		String[] words = ConfigHandler.TWEAKS.multiplayer.bannedWords;		

    	if (words.length > 0)
    	{
        	EntityPlayerMP player = event.getPlayer();

    		for (String word : words)
    		{
    			message = message.trim().toLowerCase();
    			
    			if (message.contains(word.toLowerCase().trim()))
    			{
    				event.setCanceled(true);
    				Utils.sendGameInfoMessage(event.getPlayer(), TextFormatting.RED + "'" + word + "' is banned!");
    			}
    		}    		
    	}
    }
	
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onClientChat(ClientChatReceivedEvent event)
    {
        String message = event.getMessage().getFormattedText();

        switch (event.getType())
        {
            case SYSTEM:
            {
            	if (ConfigHandler.TWEAKS.enableChatTweaks)
            	{
            		if (ConfigHandler.TWEAKS.chat.disableGiveOutput)
            		{
                        if (message.contains("Given") && message.contains("*"))
                        {
                            event.setCanceled(true);
                        }            			
            		}
            	}
            }
            case GAME_INFO:
            {
            	if (ConfigHandler.TWEAKS.enableSleepingTweaks)
            	{
                    if (message.contains("only sleep at") && !message.endsWith("!"))
                    {
                        event.setCanceled(true);
                    }
            	}
            }
            default:
            {
                break;
            }
        }
    }
}
