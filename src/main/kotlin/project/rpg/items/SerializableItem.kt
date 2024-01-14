package project.rpg.items

import io.typst.bukkit.kotlin.serialization.ItemStackSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.inventory.ItemStack

@Serializable
data class SerializableItem( @Serializable(ItemStackSerializer::class) val item: ItemStack)

fun ItemStack.toSerializable() = SerializableItem(this)
fun ItemStack.serializeToJson() = Json.Default.encodeToString(this.toSerializable())
fun String.deSerializeToItemStack() = Json.Default.decodeFromString<SerializableItem>(this).item