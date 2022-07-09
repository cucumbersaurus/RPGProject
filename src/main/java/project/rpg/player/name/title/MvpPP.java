package project.rpg.player.name.title;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import project.rpg.items.Items;
import project.rpg.items.base.ItemBase;
import project.rpg.player.name.base.TitleBase;
import project.rpg.player.name.base.TitleName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static net.kyori.adventure.text.Component.text;

public class MvpPP extends TitleBase {  //MVP++   https://www.notion.so/MVP-cc9e7ea1f98742f1a2a0ae065e233466

    @Override
    public void onEnable() {
        this._player.setGlowing(true);  //발광 추가 팀색깔로 발광되던데 팀 추가는 나중에
    }

    @Override
    public void onDisable() {
        this._player.setGlowing(false);  //꺼짐
    }

    @Override
    public void eared() {
        _player.getInventory().addItem(Objects.requireNonNull(GoldenTrophy.getInstance().getItem()));  //황금 트로비 지급
    }

    public MvpPP(Player player) {
        super(player, TitleName.MVPPP.getName(),TitleName.MVPPP.getDescription(),TitleName.MVPPP.getAcquisitionConditions());
    }

}

class GoldenTrophy extends ItemBase {  //싱글톤으로
    private static final GoldenTrophy _goldenTrophy= new GoldenTrophy();

    @Override
    public void createItem() {
        final ItemStack item = new ItemStack(Material.GOLD_BLOCK,1);
        final ItemMeta meta = item.getItemMeta();

        //display name
        meta.displayName(text("HIGHPIXEL 트로피").color(TextColor.color(0xfaf337)));
        //lore
        meta.lore(new ArrayList<>(Arrays.asList(text("어디서 많이 본 것 같은데"),text("뭐 기분 탓이겠지"))));
        //enchantments
        meta.addEnchant(Enchantment.LOYALTY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        //아이템 아이디 설정
        meta.setCustomModelData(Items.WAND.getValue());
        item.setItemMeta(meta);
        super.setItem(item);
    }

    public static GoldenTrophy getInstance() {
        return _goldenTrophy;
    }

    private GoldenTrophy() {}
}