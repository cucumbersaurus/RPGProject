package project.rpg.extensions

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.textComponets.color.TextColors

fun Component.color(textColor: TextColors):Component{
    return this.color(TextColor.color(textColor.color))
}

fun Component.color(textColor: DefaultTextColors):Component{
    return this.color(TextColor.color(textColor.color))
}