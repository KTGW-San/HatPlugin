package com.paraduction.hat.core

import com.paraduction.hat.core.yaml.YamlConfigurator
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.io.IOException

class HatManager {
    private val hatProfiler: YamlConfigurator = HatProfiler()
    private val separator = '.'
    private val root = "hats"
    private val clearIndex = arrayListOf<Int>()

    fun openGUI(player: Player) {
        val view = player.openInventory(initGUI(player))
        PlayerMessage.send("Opening GUI ${view?.title}", player, ChatColor.GREEN, true)
    }

    private fun initGUI(player: Player): Inventory {
        val inventory = Bukkit.createInventory(player, 54, ChatColor.BOLD.toString() + "Hats")

        for(i in 0 until inventory.size) {
            inventory.setItem(i, emptyItem())
        }
        for(i in 10..16){
            inventory.setItem(i, null)
        }
        for(i in 19..25){
            inventory.setItem(i, null)
        }
        for(i in 28..34){
            inventory.setItem(i, null)
        }
        for(i in 37..43){
            inventory.setItem(i, null)
        }

        inventory.setItem(48, pageItem("BACK"))
        inventory.setItem(49, closeItem())
        inventory.setItem(50, pageItem("NEXT"))
        hatProfiler.config?.getConfigurationSection("hats")?.getKeys(false)?.forEach {
            inventory.addItem(getHatItem(it))
        }
        return inventory
    }

    private fun emptyItem(): ItemStack {
        val empty = ItemStack(Material.BLACK_STAINED_GLASS_PANE)
        val meta = empty.itemMeta
        meta?.setDisplayName(" ")
        empty.itemMeta = meta
        return empty
    }

    private fun closeItem(): ItemStack {
        val close = ItemStack(Material.RED_STAINED_GLASS_PANE)
        val meta = close.itemMeta
        meta?.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD.toString() + "CLOSE")
        close.itemMeta = meta
        return close
    }

    private fun pageItem(name:String): ItemStack {
        val page = ItemStack(Material.GREEN_STAINED_GLASS_PANE)
        val meta = page.itemMeta
        meta?.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + name)
        page.itemMeta = meta
        return page
    }

    private fun getHatItem(name: String): ItemStack {
        if (hatProfiler.builder!!.isExists(root + separator + name)) {
            val config: YamlConfiguration? = hatProfiler.config

            val displayName = config?.getString(root + separator + name + separator + "displayName")!!
            val material = config.getString(root + separator + name + separator + "item")!!
            val modelData = config.getInt(root + separator + name + separator + "data")
            val lore = config.getStringList(root + separator + name + separator + "lore")

            return itemStackBuilder(displayName, material, modelData, lore)
        }
        return ItemStack(Material.BARRIER)
    }

    fun wear(name: String, player: Player) {
        if (hatProfiler.builder!!.isExists(name)) {
            val config: YamlConfiguration? = hatProfiler.config

            val displayName = config?.getString(root + separator + name + separator + "displayName")!!
            val material = config.getString(root + separator + name + separator + "item")!!
            val modelData = config.getInt(root + separator + name + separator + "data")
            val lore = config.getStringList(root + separator + name + separator + "lore")

            player.inventory.helmet = itemStackBuilder(displayName, material, modelData, lore)
            PlayerMessage.send("Give to you <3", player, ChatColor.GREEN, true)
        } else {
            PlayerMessage.send("Hat entry didn't find ! :(", player, ChatColor.RED, true)
        }
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

    private fun itemStackBuilder(name: String, material: String, modelData: Int, lore: List<String>): ItemStack {
        val itemStack = ItemStack(Material.getMaterial(material)!!)
        val itemMeta = itemStack.itemMeta
        itemMeta!!.setDisplayName(name)
        itemMeta.setCustomModelData(modelData)
        itemMeta.lore = lore
        itemStack.itemMeta = itemMeta
        return itemStack
    }
}