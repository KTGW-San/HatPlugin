package com.paraduction.hat.core

import com.paraduction.hat.core.yaml.YamlBuilder
import com.paraduction.hat.core.yaml.YamlConfigurator
import org.bukkit.Bukkit
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.util.*

class HatProfiler : YamlConfigurator() {
    private val dataDirectory: String
    override var builder: YamlBuilder? = null
    private val configFile: File

    private val configExtension = ".yml"
    private val profileName = "hats"
    private val pluginName = "Hats"

    override var config: YamlConfiguration?
        get() = builder!!.config
        set(value) {}


    override fun save(yamlConfig: YamlConfiguration) {
        builder!!.save()
    }

    override fun reload() {
        builder!!.load(configFile)
    }

    init {
        dataDirectory = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(pluginName))!!.dataFolder.path + File.separator
        configFile = File(dataDirectory + profileName + configExtension)
        try {
            builder = YamlBuilder().load(configFile)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
    }
}