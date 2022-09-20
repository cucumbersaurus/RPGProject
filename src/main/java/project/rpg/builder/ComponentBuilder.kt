package project.rpg.builder

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor


interface ResultComponent{
    val component: Component
}

class AppendText(private val source:Component, private val text: String): ResultComponent{
    override val component: Component
        get() = source.append(text(text))
}

class AddColor(private val source:Component, private val color:TextColor): ResultComponent{
    override val component: Component
        get() = source.color(color)
}

class From(private val source:Component): ResultComponent{
    override val component: Component
    get() = source
}

fun from(source:Component, func: ResultComponent.() -> Unit): ResultComponent {
    val from = From(source)
    from.func()
    return from
}


fun from(source:Component): From {
    return From(source)
}

operator fun From.plus(text:String) = AppendText(this.component, text)

infix fun ResultComponent.add(text:String) = AppendText(this.component, text)

infix fun AddColor.text(text:String) = AppendText(this.component, text)

infix fun AppendText.col(color:TextColor) = AddColor(this.component, color)
