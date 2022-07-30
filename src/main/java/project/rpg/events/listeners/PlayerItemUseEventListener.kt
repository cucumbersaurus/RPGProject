package project.rpg.events.listeners

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import project.rpg.Rpg
import project.rpg.items.ItemType
import project.rpg.items.weapon.WeaponBase
import project.rpg.manager.ItemManager
import project.rpg.player.User
import project.rpg.skill.SkillType
import project.rpg.skill.base.SkillBase
import project.rpg.skill.magic.MagicSkillBase

class PlayerItemUseEventListener(private val plugin: Rpg) : Listener {

    @EventHandler
    fun itemUseEvent(event: PlayerInteractEvent) {
        val player = event.player
        //val mana = User.getPlayer(player).mana

        if (event.item != null) {
            if(event.item!!.type == Material.WOODEN_AXE){
                event.isCancelled = true
            }
            if (event.item!!.itemMeta.hasCustomModelData()) {
                val item = ItemManager.getItem(event.item!!.itemMeta.customModelData)
                if (event.action.isRightClick && item!=null && ItemManager.getType(event.item!!.itemMeta.customModelData)==ItemType.WEAPON) {
                    if (item is WeaponBase) {
                        item.onEnable(event.action, player)
                        plugin.actionBar.updateActionBar()
                        event.isCancelled = true
                    }
                }
            } else {
                if(useSkill(player, SkillType.METEOR_STRIKE, event, event.item!!, Material.FIRE_CHARGE));
                else if(useSkill(player, SkillType.TP_ARROW, event, event.item!!, Material.ARROW));
                else if(useSkill(player, SkillType.SHOONBOW, event, event.item!!, Material.IRON_SWORD));
            }
        }
    }

    private fun useSkill(player: Player, skillType:SkillType, event: PlayerInteractEvent, usedItem: ItemStack, skillItem:Material):Boolean{
        val skill = User.getPlayer(player).skill.getSkill(skillType.skillName)
        if(skill != null && skill is MagicSkillBase){
            if(usedItem.type == skillItem){        event.isCancelled=true
                event.isCancelled=true
                if(User.getPlayer(player).mana.useMana(skill.needMana)){
                    return executeSkill(player, skill, event.action, event)
                }
            }
        }
        else {
            if (usedItem.type == skillItem && skill != null && User.getPlayer(player).mana.useMana(0)) {

                return executeSkill(player, skill, event.action, event)
            }
        }
        return false
    }

    private fun useSkill(player: Player, skillType:SkillType, event: PlayerInteractEvent, usedItem: ItemStack, skillItem:ItemStack, mana:Int):Boolean {
        val skill = User.getPlayer(player).skill.getSkill(skillType.skillName)
        if(ItemManager.isEquals(usedItem, skillItem) && skill != null){
            event.isCancelled=true
            if(User.getPlayer(player).mana.useMana(mana)){
                return executeSkill(player, skill, event.action ,  event)
            }
        }
        return false
    }

    private fun executeSkill(player:Player, skill:SkillBase, action: Action, event:PlayerInteractEvent):Boolean{
        try{
            skill.onEnable(player, action)
            plugin.actionBar.updateActionBar(event.player)
            return true
        }
        catch (exception:java.lang.Exception){
            exception.printStackTrace()
        }
        return false
    }
}