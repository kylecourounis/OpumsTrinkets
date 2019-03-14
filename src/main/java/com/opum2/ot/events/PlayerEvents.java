package com.opum2.ot.events;

import com.opum2.ot.handlers.ConfigHandler;
import com.opum2.ot.init.ModItems;
import com.opum2.ot.utils.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEvents {

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) 
    {
        EntityPlayer player = (EntityPlayer)event.getEntityLiving();        
        ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        
        if (stack != null) 
        {
            if (stack.getItem() == ModItems.TEST_BOOTS && !player.isSneaking()) 
            {
                player.motionY += 0.35D;
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerSleep(PlayerSleepInBedEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();
        World world = player.getEntityWorld();

        if (ConfigHandler.TWEAKS.enableSleepingTweaks)
        {
            if (ConfigHandler.TWEAKS.sleeping.sleepingSetsSpawn)
            {
                if (ConfigHandler.TWEAKS.sleeping.setSpawnAnytime)
                {
                    if (world.isDaytime())
                    {
                        if (player.getBedLocation() == null)
                        {
                            player.setSpawnPoint(event.getPos().add(0, 0, 1), false);
                            Utils.sendGameInfoMessage(player, TextFormatting.GREEN + "Spawn Set!");
                        }
                        else
                        {
                            Utils.sendGameInfoMessage(player, TextFormatting.RED + "You have already set your spawn! To change it, craft a 'Bed Unlinker'.");
                        }
                    }
                }
            }
            else
            {
                player.setSpawnPoint(world.getSpawnPoint(), false);
            }
        }
    }
}
