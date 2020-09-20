package com.paraduction.hat.listener

import com.paraduction.hat.core.PlayerMessage
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class EventListener : Listener {
    @EventHandler
    fun onClickHatGUI(e:InventoryClickEvent) {
        if(e.view.title == ChatColor.BOLD.toString() + "Hats") {
            e.whoClicked.sendMessage("${e.slot} ${e.currentItem?.type}")
            if(e.currentItem?.type != Material.BLACK_STAINED_GLASS_PANE && e.currentItem?.type != Material.RED_STAINED_GLASS_PANE && e.currentItem?.type != null) {
                e.whoClicked.inventory.helmet = e.currentItem
                PlayerMessage.send("Give to you <3", e.whoClicked as Player, ChatColor.GREEN, true)
            } else if (e.currentItem?.type == Material.RED_STAINED_GLASS_PANE) {
                e.whoClicked.closeInventory()
            }
            e.isCancelled = true
        }
    }
}