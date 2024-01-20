package project.rpg.commands.debug

import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import net.kyori.adventure.text.Component

fun PluginKommand.registerSampleKommand() {
    register("sample") {
        requires { isPlayer && isOp }
        executes {
            sender.sendMessage(Component.text("Hello World!"))
        }
        then("foo") {
            executes {
                player.sendMessage(Component.text("Hello Foo!"))
            }
            then("myint" to int()) {
                executes {
                    val myint: Int by it
                    for (i in 0..myint) {
                        sender.sendMessage("Hello Foo!")
                    }
                }
            }
        }
    }
}