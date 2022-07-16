package project.rpg.commands.test

import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import project.rpg.Rpg

object TestCommand {
    fun register(kommand: PluginKommand, plugin: Rpg){
        kommand.register("user") {
            then("create") {
                then("name" to string()) {
                    executes { context ->
                        val name: String by context
                        Bukkit.broadcast(text(name))
                    }
                }
            }
        }
    }
}