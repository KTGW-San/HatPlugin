package com.paraduction.hat.core.yaml

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player

abstract class YamlConfigurator {
    open var config: YamlConfiguration? = null

    open fun YamlConfigurator() {
        config = YamlConfiguration()
    }

    open val builder: YamlBuilder?
        get() = null

    open fun reload() { }

    open fun load(player: Player): YamlConfiguration? {
        return null
    }

    open fun load(): YamlConfiguration? {
        return null
    }

    open fun save(yamlConfig: YamlConfiguration) { }

    open fun save(yamlConfig: YamlConfiguration, player: Player) { }

    open fun saveDefault() { }

    open fun saveDefault(obj : Any) { }
}