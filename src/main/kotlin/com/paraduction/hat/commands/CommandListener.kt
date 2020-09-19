package com.paraduction.hat.commands

import com.paraduction.hat.core.HatManager
import com.paraduction.hat.core.PlayerMessage
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandListener : CommandExecutor {
    private var hatProfiler = HatManager()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        if(label.equals("hats", true)) {
            when(args[0]) {
                "help" -> { PlayerMessage.send(helpString, player, ChatColor.GOLD, false) }
                "wear" -> {
                    if(args.size > 1) {
                        hatProfiler.wear(args[1], player)
                    } else {
                        PlayerMessage.send("Please specify the hat name!", player, ChatColor.RED, true)
                    }
                }
                "reload" -> {
                    PlayerMessage.send("Executing Reload...", player, ChatColor.GREEN, true)
                    hatProfiler.reload()
                }
                else -> { }
            }
            return true;
        }
        return false
    }

    private val helpString:String = "----------Hats v0.0.1----------\n" +
                                    "/hats help : Sends HelpMessage(This!)\n" +
                                    "/hats wear [name] : Attach hat on your head!\n" +
                                    "/hats reload : Reload hat entry"
}