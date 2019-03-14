package com.opum2.ot.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.opum2.ot.init.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTestArmor extends ModArmor {

    public ItemTestArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipSlot, String name)
    {
        super(material, renderIndex, equipSlot, name);
    }
    
    @Override
    public void onUpdateEquiped(ItemStack stack, World world, EntityLivingBase entity)
    {
        EntityPlayer player = (EntityPlayer)entity;

        if (player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ModItems.TEST_CHEST && !player.isCreative())
        {
            player.capabilities.allowFlying = true;
            player.capabilities.setFlySpeed(0.075F);
        }
        else if (!player.isCreative())
        {
            player.capabilities.isFlying = false;
            player.capabilities.allowFlying = false;
            player.capabilities.setFlySpeed(0.05F);
        }
        
        if (player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ModItems.TEST_BOOTS)
        {
            player.capabilities.setPlayerWalkSpeed(0.15F);
            
            boolean onGround = false;
            
            if (entity.fallDistance >= 3.0F)
            {
                entity.fallDistance = 0.0F;
                
                if (world.isRemote)
                {
                    for (int i = 0; i < 3; ++i)
                    {
                        world.spawnParticle(EnumParticleTypes.CLOUD, entity.posX, entity.posY - 2.0D, entity.posZ,
                                (double) ((Item.itemRand.nextFloat() - 0.5F) / 2.0F), -0.5D,
                                (double) ((Item.itemRand.nextFloat() - 0.5F) / 2.0F));
                    }
                }
            }

            if (entity.isSprinting() && world.isRemote)
            {
                world.spawnParticle(EnumParticleTypes.CLOUD, entity.posX, entity.posY - 1.5D, entity.posZ,
                        (double) ((Item.itemRand.nextFloat() - 0.5F) / 2.0F), 0.1D,
                        (double) ((Item.itemRand.nextFloat() - 0.5F) / 2.0F));
            }

            if (!entity.onGround && entity instanceof EntityPlayer)
            {
                entity.jumpMovementFactor += 0.028F;
            }

            if (entity.collidedHorizontally)
            {
                entity.stepHeight = 1.0F;
            }
            else
            {
                entity.stepHeight = 0.5F;
            }
        }
        else
        {
            player.capabilities.setPlayerWalkSpeed(0.1F);
        }
    }
    
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
    }

    public Multimap getItemAttributeModifiers()
    {
        HashMultimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(ItemArmor.ATTACK_DAMAGE_MODIFIER, "Boots modifier", 0.15D, 2));
        return multimap;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
    {
        Item item = stack.getItem();
        
        if (item == ModItems.TEST_CHEST)
        {
            tooltip.add(TextFormatting.AQUA + "Wing");
        }
        else if (item ==  ModItems.TEST_BOOTS)
        {
            tooltip.add(TextFormatting.AQUA + "Speed");
            tooltip.add(TextFormatting.AQUA + "Cloud");
        }
    }
}
