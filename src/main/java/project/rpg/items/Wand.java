package project.rpg.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.rpg.enums.ItemType;
import project.rpg.items.base.ItemBase;

import java.util.ArrayList;
import java.util.Arrays;

public class Wand extends ItemBase {


    public static void init() {
        createItem();
    }

    public static void createItem(){
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();

        //display name
        meta.setDisplayName("전설의 막대기");
        //lore
        meta.setLore(new ArrayList<> (Arrays.asList("이 막대기는 아주 위대하고", "전설적인 나무젓가락 입니다")));
        //enchantments
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        //아이템 아이디 설정
        meta.setCustomModelData(ItemType.WAND.getValue());

        item.setItemMeta(meta);
        _item = item;
    }
}
