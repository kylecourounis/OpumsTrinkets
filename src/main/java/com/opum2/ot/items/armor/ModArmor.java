package com.opum2.ot.items.armor;

import com.opum2.ot.init.ModCreativeTab;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModArmor extends ItemArmor {

    public ModArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipSlot, String name)
    {
        super(material, renderIndex, equipSlot);
        
        this.setUnlocalizedName(name);
        this.setRegistryName(name);        

        this.setMaxStackSize(1);

        this.setCreativeTab(ModCreativeTab.MOD_TAB);
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
    {
        this.onUpdateEquiped(stack, world, player);        
    }
    
    public void onUpdateEquiped(ItemStack stack, World world, EntityLivingBase entity)
    {        
    }
}
