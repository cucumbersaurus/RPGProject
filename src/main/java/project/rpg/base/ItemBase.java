package project.rpg.base;

import org.bukkit.Material;
import project.rpg.enums.ItemType;

public abstract class ItemBase {

    Material material_ = Material.getMaterial("AIR");
    int count_ = 0;
    ItemType itemType_ = ItemType.NULL;


}
