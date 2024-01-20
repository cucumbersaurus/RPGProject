package project.rpg.commands.debug

import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit

fun PluginKommand.registerTestCommand() {
    register("user") {
        then("create") {
            then("name" to string()) {
                executes {
                    val name: String by it
                    Bukkit.broadcast(text(name))
                }
            }
        }
    }
}
