package rpgProject.baseClass;

import org.bukkit.Material;
import rpgProject.enums.ItemType;

public abstract class ItemBase {

    Material material_ = Material.getMaterial("AIR");
    int count_ = 0;
    ItemType itemType_ = ItemType.NULL;


}
