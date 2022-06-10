package project.rpg.commands

import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import net.kyori.adventure.text.Component.text
import project.rpg.Rpg

object TestCommand {
    fun register(kommand: PluginKommand, plugin: Rpg){
        kommand.register("test"){
            executes {
                sender.sendMessage(text("테스트 성공"))
            }
            then("argument" to string()) {
                executes {
                    val argument: String by it
                    player.sendMessage(text("입력값 : $argument"))
                }
            }
        }
    }
}