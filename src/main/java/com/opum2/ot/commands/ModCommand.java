package com.opum2.ot.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class ModCommand implements ICommand {

    private String name;
    private String description;

    private List<String> aliases;

    public ModCommand(String name, String description)
    {
        this.name = name;
        this.description = description;

        this.aliases = new ArrayList();
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/" + this.getName() + " - " + this.description;
    }

    @Override
    public List<String> getAliases()
    {
        return this.aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return false;
    }

    @Override
    public int compareTo(ICommand command)
    {
        return 0;
    }
}
