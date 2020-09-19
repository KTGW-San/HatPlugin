package com.paraduction.hat.core.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CommandListener : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(label.equals("hat", true)) {
            when(args[0]) {
                "help" -> {}
                "give" -> {}
                "reload" -> {}
            }
            return true;
        }
        return false
    }

    private fun sendHelp() {

    }
}