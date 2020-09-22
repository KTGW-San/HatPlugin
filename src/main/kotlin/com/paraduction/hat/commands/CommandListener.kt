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
        if(label.equals("hats", true) and (args.isNotEmpty())) {
            when(args[0]) {
                "help" -> { PlayerMessage.send(helpString, player, ChatColor.GOLD, false) }
                "wear" -> {
                    if(args.size > 1) {
                        hatProfiler.wear(args[1], player)
                    } else {
                        PlayerMessage.send("Please specify the hat name!", player, ChatColor.RED, true)
                    }
                }
                "gui" -> { hatProfiler.openGUI(player) }
                "reload" -> {
                    PlayerMessage.send("Executing Reload...", player, ChatColor.GREEN, true)
                    if(hatProfiler.reload()) {
                        PlayerMessage.send("Reloaded successfully", player, ChatColor.GREEN, true)
                    } else {
                        PlayerMessage.send("Something occurred :( printing stacktrace at console", player, ChatColor.RED, true)
                    }
                }
            }
            return true;
        } else if(label.equals("hats", true)) {
            hatProfiler.openGUI(player)
            return true;
        }
        return false
    }

    private val helpString:String = "----------Hats v1.0.0----------\n" +
                                    "/hats help : Sends HelpMessage(This!)\n" +
                                    "/hats reload : Reload hat entry\n" +
                                    "/hats wear [name] : Attach hat on your head!\n" +
                                    "/hats gui : Open Hat selection GUI\n" +
                                    "/hats : Open Hat selection GUI(too!)"

}