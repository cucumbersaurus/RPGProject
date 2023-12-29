package project.rpg.items.disposable

import org.bukkit.entity.Player
import project.rpg.items.ItemBase

abstract class DisposableBase : ItemBase() {
    abstract fun onUse(player: Player)
}
