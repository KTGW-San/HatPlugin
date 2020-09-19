package com.paraduction.hat

import com.paraduction.hat.core.commands.CommandListener
import org.bukkit.plugin.java.JavaPlugin

class Hats : JavaPlugin() {
    override fun onEnable() {
        this.server.getPluginCommand("hat")?.setExecutor(CommandListener())
    }

    override fun onDisable() {
    }
}