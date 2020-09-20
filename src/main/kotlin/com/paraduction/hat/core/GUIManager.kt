package com.paraduction.hat.core

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class GUIManager {
    fun init(player: Player): Inventory {
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
}