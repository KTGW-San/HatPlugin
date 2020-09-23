package com.paraduction.hat.core.yaml

import org.bukkit.Bukkit
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.util.*

abstract class YamlConstructor {
    private lateinit var configFile: File;
    lateinit var builder: YamlBuilder;

    constructor(plugin: String?, profile: String, format: String) {
        val dataDirectory = pathBuilder(File.separator, Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(plugin!!))!!.dataFolder.path)
        this.configFile = File(dataDirectory + profile + format)
        try {
            builder = YamlBuilder().load(configFile)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
    }

    constructor(plugin: String?, profile: String, location: String?, format: String) {
        val dataDirectory = pathBuilder(File.separator, Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(plugin!!))!!.dataFolder.path, location)
        val configFile = File(dataDirectory + profile + format)
        try {
            builder = YamlBuilder().load(configFile)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
    }

    private fun pathBuilder(separator: String?, vararg content: String?): String {
        val builder = StringBuilder()
        var i = 0
        while (content.size - 1 >= i) {
            builder.append(content[i]).append(separator)
            i++
        }
        return builder.toString()
    }

    open fun reload() {
        builder.load(configFile)
    }

    open fun save(any: Any?) { }

    open fun save(configuration: YamlConfiguration?) { }

    val config: YamlConfiguration?
        get() = builder.config
}