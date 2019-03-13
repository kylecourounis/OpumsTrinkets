package com.opum2.ot.commands;

import java.util.List;

import com.opum2.ot.OpumsTrinkets;
import com.opum2.ot.gui.GuiPlayerPanel;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import scala.actors.threadpool.Arrays;

public class PlayerPanelCommand extends ModCommand {

    public PlayerPanelCommand()
    {
        super("player",
                "/player <name> - Opens up an panel with actions that can be executed on the specified player.");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length > 0)
        {
            EntityPlayer selectedPlayer = server.getPlayerList().getPlayerByUsername(args[0]);
            OpumsTrinkets.proxy.openGui(new GuiPlayerPanel(selectedPlayer));
        } 
        else
        {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "You did not specify the name of a player!"));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
    {
        return Arrays.asList(server.getPlayerList().getOnlinePlayerNames());
    }
}
