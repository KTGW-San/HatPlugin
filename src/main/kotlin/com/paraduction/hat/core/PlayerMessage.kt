package com.paraduction.hat.core

import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PlayerMessage {
    private val displayPrefix = ChatColor.BOLD.toString() + "[Hats]"

    fun send(content: String?, player: Player, attachDisplayPrefix: Boolean) {
        val builder = StringBuilder()
        if (attachDisplayPrefix) {
            builder.append(displayPrefix).append(content)
        } else {
            builder.append(content)
        }
        player.sendMessage(builder.toString())
    }

    fun send(components: Array<BaseComponent?>, player: Player, attachDisplayPrefix: Boolean?) {
        player.spigot().sendMessage(*components)
    }

    fun send(content: String?, player: Player, chatColor: ChatColor?, attachDisplayPrefix: Boolean) {
        val builder = StringBuilder()
        if (attachDisplayPrefix) {
            builder.append(displayPrefix).append(chatColor).append(content)
        } else {
            builder.append(chatColor).append(content)
        }
        player.sendMessage(builder.toString())
    }
}