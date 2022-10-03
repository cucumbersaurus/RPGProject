package project.rpg.items.accessory

import project.rpg.items.ItemBase

abstract class AccessoryBase : ItemBase() {
    abstract fun onLift()
    abstract fun inInventory()
}