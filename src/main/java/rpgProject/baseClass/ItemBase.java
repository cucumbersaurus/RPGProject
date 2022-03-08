package rpgProject.baseClass;

import org.bukkit.Material;
import rpgProject.enums.ItemType;

public abstract class ItemBase {

    Material material = Material.getMaterial("AIR");
    int count = 0;
    ItemType itemType = ItemType.NULL;


}
