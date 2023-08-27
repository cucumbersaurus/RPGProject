package project.rpg.commands.debug

import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.CreatureSpawnEvent
import project.rpg.mob.ZombieKing

class MobTest : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (args != null) {
                return when (args.size) {
                    0 -> {
                        sender.world.spawnEntity(
                            sender.location,
                            EntityType.ZOMBIE,
                            CreatureSpawnEvent.SpawnReason.CUSTOM,
                            ZombieKing().createMobFun()
                        )
                        true
                    }

                    3 -> {
                        sender.world.spawnEntity(
                            Location(sender.world, args[0].toDouble(), args[1].toDouble(), args[2].toDouble()),
                            EntityType.ZOMBIE,
                            CreatureSpawnEvent.SpawnReason.CUSTOM,
                            ZombieKing().createMobFun()
                        )
                        true
                    }

                    else -> {
                        false
                    }
                }
            } else {
                sender.world.spawnEntity(
                    sender.location,
                    EntityType.ZOMBIE,
                    CreatureSpawnEvent.SpawnReason.CUSTOM,
                    ZombieKing().createMobFun()
                )
                return true
            }
        }
        return false
    }
}