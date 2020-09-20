package com.paraduction.hat.core

import com.paraduction.hat.core.yaml.YamlConfigurator
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.IOException

@Suppress("UNCHECKED_CAST")
class HatManager {
    private val gui = GUIManager()
    private val hatProfiler: YamlConfigurator = HatProfiler()
    private val separator = '.'
    private val root = "hats"

    //HatのItemStackを取得する
    private fun get(name: String): ItemStack {
        if (hatProfiler.builder!!.isExists(root + separator + name)) {
            val data:List<*> = getData(name)
            return itemBuilder(data[0] as String, data[1] as String, data[2] as Int, data[3] as List<String>)
        }
        return ItemStack(Material.BARRIER)
    }

    fun wear(name: String, player: Player) {
        if (hatProfiler.builder!!.isExists(name)) {
            val data:List<*> = getData(name)
            player.inventory.helmet = itemBuilder(data[0] as String, data[1] as String, data[2] as Int, data[3] as List<String>)
            PlayerMessage.send("Give to you <3", player, ChatColor.GREEN, true)
        } else {
            PlayerMessage.send("Hat entry didn't find ! :(", player, ChatColor.RED, true)
        }
    }

    private fun getData(name: String): List<*> {
        //コンフィグ、パスの初期化
        val config: YamlConfiguration? = hatProfiler.config
        val path = pathBuilder(root,name)
        //データの定義
        val displayName = config?.getString(path + "displayName")!!
        val material = config.getString(path + "item")!!
        val modelData = config.getInt(path + "data")
        val lore = config.getStringList(path + "lore")
        //Any型のリストを返す
        return listOf(displayName,material,modelData,lore)
    }

    //Hat用のItemStackビルダー
    private fun itemBuilder(name: String, material: String, modelData: Int, lore: List<String>): ItemStack {
        val itemStack = ItemStack(Material.getMaterial(material)!!)
        val itemMeta = itemStack.itemMeta
        //アイテムメタデータの設定
        itemMeta!!.setDisplayName(name)
        itemMeta.setCustomModelData(modelData)
        itemMeta.lore = lore
        itemStack.itemMeta = itemMeta
        return itemStack
    }

    //Yamlパス用のビルダー
    private fun pathBuilder(vararg content:String): String {
        val builder = StringBuilder()
        for(i in content.indices) {
            builder.append(content[i]).append(separator)
        }
        return builder.toString()
    }

    fun openGUI(player: Player) {
        val view = player.openInventory(gui.init(player))
        hatProfiler.config?.getConfigurationSection(root)?.getKeys(false)?.forEach {
            view?.topInventory?.addItem(get(it))
        }
        PlayerMessage.send("Opening GUI ${view?.title}", player, ChatColor.GREEN, true)
    }

    fun reload(): Boolean {
        kotlin.runCatching {
            hatProfiler.reload()
        }.fold(
                onSuccess = { return true },
                onFailure = {
                    it.printStackTrace()
                    return false
                }
        )
    }
}