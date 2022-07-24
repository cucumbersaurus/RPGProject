package project.rpg.textComponets.color

import net.kyori.adventure.text.format.TextColor

enum class DefaultTextColors(originalColor: TextColor) {

    BLACK(TextColor.color(0x000000)),
    DARK_BLUE(TextColor.color(0x0000aa)),
    DARK_GREEN(TextColor.color(0x00aa00)),
    DARK_AQUA(TextColor.color(0x00aaaa)),
    DARK_RED(TextColor.color(0xaa0000)),
    DARK_PURPLE(TextColor.color(0xaa00aa)),
    GOLD(TextColor.color(0xffaa00)),
    GRAY(TextColor.color(0xaaaaaa)),
    DARK_GRAY(TextColor.color(0x555555)),
    BLUE(TextColor.color(0x5555ff)),
    GREEN(TextColor.color(0x55ff55)),
    AQUA(TextColor.color(0x55fffff)),
    RED(TextColor.color(0xff5555)),
    LIGHT_PURPLE(TextColor.color(0xff55ff)),
    YELLOW(TextColor.color(0xffff55)),
    WHITE(TextColor.color(0xffffff));

    var color: TextColor
    
    init{
        color = originalColor
    }

}