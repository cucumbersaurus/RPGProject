package project.rpg.items

import org.bukkit.inventory.ItemStack

abstract class ItemBase {
    var item: ItemStack? = null
        protected set

    abstract fun createItem()
    override operator fun equals(other: Any?): Boolean {//만들어 봤지만 쓸모가 있을까?
        if (other==null){
            return false
        }
        else{
            return if (other !is ItemStack){
                false
            } else{
                val meta1 = this.item?.itemMeta
                val meta2 = other.itemMeta
                if(meta1==null || meta2==null){
                    false
                } else{
                    meta1.customModelData == meta2.customModelData
                }
            }
        }
    }

    override fun hashCode(): Int {//이게 뭘까? 만들라 해서 만들긴 했는데
        return item?.hashCode() ?: 0
    }
}