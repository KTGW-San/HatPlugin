package com.paraduction.hat

import com.paraduction.hat.commands.CommandListener
import org.bukkit.plugin.java.JavaPlugin

class Hats : JavaPlugin() {
    override fun onEnable() {
        this.server.getPluginCommand("hats")?.setExecutor(CommandListener())
    }

    override fun onDisable() {
    }
}