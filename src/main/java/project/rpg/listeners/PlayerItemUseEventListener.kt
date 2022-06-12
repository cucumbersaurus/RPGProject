package project.rpg.listeners

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import project.rpg.Rpg
import project.rpg.items.ItemType
import project.rpg.manager.ItemManager
import project.rpg.player.info.Mana
import project.rpg.player.info.Skill
import project.rpg.skill.SkillType

class PlayerItemUseEventListener(private val _plugin: Rpg) : Listener {

    @EventHandler
    fun itemUseEvent(event: PlayerInteractEvent) {
        val player = event.player

        if (event.item != null) {
            if (ItemManager.isEquals(event.item, ItemManager.getItem(ItemType.WAND))) {
                if (event.action.isRightClick && Mana.useMana(player, 10)) {

                    var location = player.location
                    if (player.getTargetBlock(30) != null) {
                        location = player.getTargetBlock(30)!!.location
                    }

                    player.world.spawnEntity(location, EntityType.LIGHTNING)
                    _plugin.actionBar.updateActionBar()
                    event.isCancelled = true
                }
            } else {
                if(useSkill(player, SkillType.METEOR_STRIKE, event, event.item!!, Material.FIRE_CHARGE, 10));
                else if(useSkill(player, SkillType.TP_ARROW, event, event.item!!, Material.ARROW, 5));
            }
        }
    }

    private fun useSkill(player: Player, skillType:SkillType, event: PlayerInteractEvent, usedItem: ItemStack, skillItem:Material, mana:Int):Boolean{
        val skill = Skill.getSkill(player, skillType.skillName)
        if(usedItem.type == skillItem && skill != null && Mana.useMana(player, mana)){
            skill.onEnable(event.action)
            _plugin.actionBar.updateActionBar()
            event.isCancelled = true
            return true
        }
        return false
    }

    private fun useSkill(player: Player, skillType:SkillType, event: PlayerInteractEvent, usedItem: ItemStack, skillItem:ItemStack, mana:Int):Boolean {
        val skill = Skill.getSkill(player, skillType.skillName)
        if(ItemManager.isEquals(usedItem, skillItem) && skill != null && Mana.useMana(player, mana)){
            skill.onEnable(event.action)
            _plugin.actionBar.updateActionBar()
            event.isCancelled = true
            return true
        }
        return false
    }

}