/* This file is part of the InternalPluginManager.
 *
 * Copyright (C) 2014 Fabian Damken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.blockhaus2000.ipm.minecraft.bukkit.command;

import java.util.Arrays;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blockhaus2000.ipm.minecraft.InternalPluginManager;
import com.blockhaus2000.ipm.technical.command.util.CommandInfo;

public class DynamicCommand extends Command {
    public DynamicCommand(final CommandInfo commandInfo, final com.blockhaus2000.ipm.technical.command.Command commandAnot) {
        super(commandAnot.aliases()[0], "", "/" + commandAnot.aliases()[0], Arrays.asList(commandAnot.aliases()));
    }

    public DynamicCommand(final CommandInfo commandInfo) {
        this(commandInfo, commandInfo.getCommandAnot());
    }

    /**
     * {@inheritDoc}
     *
     * @see org.bukkit.command.Command#execute(org.bukkit.command.CommandSender,
     *      java.lang.String, java.lang.String[])
     */
    @Override
    public boolean execute(final CommandSender sender, final String label, final String[] args) {
        final com.blockhaus2000.ipm.technical.command.CommandSender commandSender;
        if (sender instanceof Player) {
            commandSender = InternalPluginManager.getServer().getPlayer(((Player) sender).getUniqueId());
        } else if (sender instanceof BlockCommandSender) {
            commandSender = new BukkitBlockCommandSender();
        } else {
            commandSender = new BukkitConsoleCommandSender();
        }

        return InternalPluginManager.getServer().getCommandManager().execute(label, commandSender, args);
    }
}
