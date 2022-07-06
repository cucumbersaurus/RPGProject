package project.rpg.events.listeners

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import project.rpg.Rpg
import project.rpg.items.Items
import project.rpg.manager.ItemManager
import project.rpg.player.info.Mana
import project.rpg.player.info.Skill
import project.rpg.skill.SkillType
import project.rpg.skill.base.MagicSkillBase
import project.rpg.skill.base.SkillBase

class PlayerItemUseEventListener(private val plugin: Rpg) : Listener {

    @EventHandler
    fun itemUseEvent(event: PlayerInteractEvent) {
        val player = event.player

        if (event.item != null) {
            if(event.item!!.type == Material.WOODEN_AXE){
                event.isCancelled = true
            }
            if (ItemManager.isEquals(event.item!!, Items.WAND.item!!)) {
                if (event.action.isRightClick && Mana.useMana(player, 10)) {

                    var location = player.location
                    if (player.getTargetBlock(30) != null) {
                        location = player.getTargetBlock(30)!!.location
                    }

                    player.world.spawnEntity(location, EntityType.LIGHTNING)
                    plugin.actionBar.updateActionBar()
                    event.isCancelled = true
                }
            } else {
                if(useSkill(player, SkillType.METEOR_STRIKE, event, event.item!!, Material.FIRE_CHARGE));
                else if(useSkill(player, SkillType.TP_ARROW, event, event.item!!, Material.ARROW));
                else if(useSkill(player, SkillType.SHOONBOW, event, event.item!!, Material.IRON_SWORD));
            }
        }
    }

    private fun useSkill(player: Player, skillType:SkillType, event: PlayerInteractEvent, usedItem: ItemStack, skillItem:Material):Boolean{
        val skill:SkillBase? = Skill.getSkill(player, skillType.skillName)
        if(skill != null && skill is MagicSkillBase){
            if(usedItem.type == skillItem && Mana.useMana(player, skill.needMana)){
                return executeSkill(skill, event.action, event)
            }
        }
        else {
            if (usedItem.type == skillItem && skill != null && Mana.useMana(player, 0)) {
                return executeSkill(skill, event.action, event)
            }
        }
        return false
    }

    private fun useSkill(player: Player, skillType:SkillType, event: PlayerInteractEvent, usedItem: ItemStack, skillItem:ItemStack, mana:Int):Boolean {
        val skill:SkillBase? = Skill.getSkill(player, skillType.skillName)
        if(ItemManager.isEquals(usedItem, skillItem) && skill != null && Mana.useMana(player, mana)){
            return executeSkill(skill, event.action ,  event)
        }
        return false
    }

    private fun executeSkill(skill:SkillBase, action: Action, event:PlayerInteractEvent):Boolean{
        try{
            skill.onEnable(action)
            plugin.actionBar.updateActionBar(event.player)
            event.isCancelled = true
            return true
        }
        catch (exception:java.lang.Exception){
            exception.printStackTrace()
        }
        return false
    }
}