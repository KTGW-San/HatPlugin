package com.paraduction.hat.core.yaml

import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.Serializable
import java.util.logging.Level

class YamlBuilder : Serializable {
    val config: YamlConfiguration = YamlConfiguration()
    private var file: File? = null
    private var root: String? = null
    private var hasRoot = false

    fun save(): Boolean {
        return if (file != null) {
            config.save(file!!)
            Bukkit.getLogger().log(Level.INFO, "saved successfully")
            true
        } else {
            throw NullPointerException("Failed to build yaml config. aborting.")
        }
    }

    fun addEntry(config: YamlConfiguration): Boolean {
        return if (file != null) {
            config.addDefaults(config)
            Bukkit.getLogger().log(Level.INFO, "Saved.")
            true
        } else {
            Bukkit.getLogger().log(Level.WARNING, "Failed to save yaml config. aborting.")
            false
        }
    }

    fun load(file: File): YamlBuilder {
        Bukkit.getLogger().log(Level.INFO, "Loading Config")
        this.file = file
        if (!file.exists()) {
            Bukkit.getLogger().log(Level.INFO, "Config not found. Creating new one.")
            config.save(file)
        }
        config.load(this.file!!)
        Bukkit.getLogger().log(Level.INFO, "Loaded: " + config.currentPath)
        return this
    }

    fun root(root: String): YamlBuilder {
        if (!hasRoot) {
            this.root = "$root."
            hasRoot = true
        }
        return this
    }

    fun isExists(content: String?): Boolean {
        return config.contains(content!!)
    }

    private fun append(K: Any, V: Any): YamlBuilder {
        config[K.toString()] = V.toString()
        Bukkit.getLogger().log(Level.INFO, "Appending Key: $K", " Value: $V")
        return this
    }

    fun append(key: String, value: String): YamlBuilder {
        val path = if (hasRoot) root + key else key
        return append(path, value as Any)
    }

    fun append(key: String, value: Int): YamlBuilder {
        val path = if (hasRoot) root + key else key
        return append(path, value as Any)
    }

    fun append(key: String, value: List<String?>): YamlBuilder {
        val path = if (hasRoot) root + key else key
        return append(path, value as Any)
    }
}