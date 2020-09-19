package com.paraduction.hat.core

import com.paraduction.hat.core.yaml.YamlConfigurator
import org.bukkit.Material
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.IOException

class HatManager {
    private val hatProfiler: YamlConfigurator = HatProfiler()
    private val separator = '.'

    fun openGUI(player: Player) {}

    fun giveItemHat(name: String, player: Player) {
        if (hatProfiler.builder!!.isExists(name)) {
            val config: YamlConfiguration? = hatProfiler.config
            val displayName = config?.getString(name + separator + "displayName")!!
            val material = config.getString(name + separator + "item")!!
            val modelData = config.getInt(name + separator + "data")

            player.sendMessage("あげる<3")
            player.inventory.helmet = itemStackBuilder(displayName, material, modelData)
        } else {
            player.sendMessage("Hatエントリーが見つかりません")
        }
    }

    fun add(config: YamlConfiguration) {
        try {
            hatProfiler.builder!!.addEntry(config)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun itemStackBuilder(name: String, material: String, modelData: Int): ItemStack {
        val itemStack = ItemStack(Material.getMaterial(material)!!)
        val itemMeta = itemStack.itemMeta
        itemMeta!!.setDisplayName(name)
        itemMeta.setCustomModelData(modelData)
        itemStack.itemMeta = itemMeta
        return itemStack
    }

    fun reload() {
        try {
            hatProfiler.reload()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
    }
}