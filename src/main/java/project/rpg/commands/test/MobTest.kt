package project.rpg.commands.test

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.CreatureSpawnEvent
import project.rpg.mob.ZombieKing

class MobTest:CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(sender is Player){
            sender.world.spawnEntity(sender.location, EntityType.ZOMBIE,CreatureSpawnEvent.SpawnReason.CUSTOM, ZombieKing().createMobFun())
        }
            return true
    }
}