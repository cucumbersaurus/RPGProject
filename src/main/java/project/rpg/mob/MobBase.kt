package project.rpg.mob

import org.bukkit.entity.Entity

interface MobBase {

    fun createMobFun():(entity: Entity) ->Unit

}