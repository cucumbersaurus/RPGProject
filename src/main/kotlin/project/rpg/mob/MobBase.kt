package project.rpg.mob

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity

interface MobBase {
    fun spawn(world: World, loc:Location): Entity

}