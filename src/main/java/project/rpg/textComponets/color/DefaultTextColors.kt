package project.rpg.textComponets.color

import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextColor.color

enum class DefaultTextColors(originalColor: TextColor) {

    BLACK(color(0x000000)),
    DARK_BLUE(color(0x0000aa)),
    DARK_GREEN(color(0x00aa00)),
    DARK_AQUA(color(0x00aaaa)),
    DARK_RED(color(0xaa0000)),
    DARK_PURPLE(color(0xaa00aa)),
    GOLD(color(0xffaa00)),
    GRAY(color(0xaaaaaa)),
    DARK_GRAY(color(0x555555)),
    BLUE(color(0x5555ff)),
    GREEN(color(0x55ff55)),
    AQUA(color(0x55fffff)),
    RED(color(0xff5555)),
    LIGHT_PURPLE(color(0xff55ff)),
    YELLOW(color(0xffff55)),
    WHITE(color(0xffffff)),
    ;

    val color: TextColor

    init {
        color = originalColor
    }
}