package project.rpg.ui.inventory.interection.block

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import project.rpg.ui.inventory.GuiBase

class EnchantUI protected constructor(p: Player) : GuiBase(p, Component.text("마법부여대"), 54) {
    override fun initialize() {}
}