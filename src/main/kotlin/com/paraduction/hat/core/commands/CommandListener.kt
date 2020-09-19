package com.paraduction.hat.core.commands

import com.paraduction.hat.core.HatManager
import com.paraduction.hat.core.HatProfiler
import com.paraduction.hat.core.PlayerMessage
import com.paraduction.hat.yaml.YamlConfigurator
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandListener : CommandExecutor {
    private var hatProfiler = HatManager()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        if(label.equals("hat", true)) {
            when(args[0]) {
                "help" -> { PlayerMessage.send(helpString, player, ChatColor.GOLD, false) }
                "wear" -> {
                    if(args.size > 1) {
                        hatProfiler.giveItemHat(args[1], player)
                    } else {
                        PlayerMessage.send("Please specify the hat name!", player, ChatColor.GOLD, false)
                    }
                }
                "reload" -> {}
                else -> { }
            }
            return true;
        }
        return false
    }

    private val helpString:String = "---dad--HAT PLUGIN-----\n" +
                                    "/hat help : Sends HelpMessage(This!)\n" +
                                    "/hat wear [name] : Attach hat on your head!\n" +
                                    "/hat reload : Reload hat entry"
}