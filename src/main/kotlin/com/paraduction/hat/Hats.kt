package com.paraduction.hat

import com.paraduction.hat.commands.CommandListener
import com.paraduction.hat.listener.EventListener
import org.bukkit.plugin.java.JavaPlugin

class Hats : JavaPlugin() {
    override fun onEnable() {
        this.server.getPluginCommand("hats")?.setExecutor(CommandListener())
        this.server.pluginManager.registerEvents(EventListener(), this)
    }

    override fun onDisable() {
    }
}