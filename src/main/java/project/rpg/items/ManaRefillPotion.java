package project.rpg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.rpg.items.base.ItemBase;

import java.util.ArrayList;
import java.util.Arrays;

public class ManaRefillPotion extends ItemBase {

    protected static ItemStack _item;

    public ManaRefillPotion() {
        createItem();
    }

    @Override
    public void createItem(){
        ItemStack item = new ItemStack(Material.POTION, 1);
        ItemMeta meta = item.getItemMeta();

        //display name
        meta.setDisplayName("마나 회복 포션");
        //lore
        meta.setLore(new ArrayList<>(Arrays.asList("이 포션을 마시면", "최대 100의 마나를 회복합니다")));
        //아이템 아이디 설정
        meta.setCustomModelData(ItemType.MANA_REFILLING_POTION.getValue());
        item.setItemMeta(meta);
        _item = item;
    }
}
