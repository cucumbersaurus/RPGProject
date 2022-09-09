package project.rpg.ui.inventory.blockInterection;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import project.rpg.ui.inventory.GuiBase;

import java.util.List;
import java.util.Random;

import static net.kyori.adventure.text.Component.text;

public class EnforceUI extends GuiBase {

    public EnforceUI(@NotNull Player player){
        super(player, 54,  text("메인 메뉴"));
    }

    @Override
    protected void initialize(@NotNull Player player) {
        for(int i = 0; i < 54; i++){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, "enforce.background", false);
        }
        setItem(text("강화하기"), null, Material.ANVIL, 1, 31, "enforce.enable", false);
    }

    @Override
    public void onClick(@NotNull InventoryClickEvent event) {
        event.setCancelled(true);
        String value = getValue(event.getSlot());
        switch (value) {
            case "enforce.background":
                break;
            case "enforce.enable":
                //enforce()
                break;
            case "enforce.close":
                forceCloseGUI();
                break;
            default:
                break;
        }
    }

    private void enforce(short level,Player player,@NotNull InventoryClickEvent event) {
        int result = new Random().nextInt(10000);
        int success;
        int fail;
        switch (level) {
            case 0:
                success = 9000;
                fail = 900;
                break;
            case 1:
                success = 8000;
                fail = 1500;
                break;
            case 2:
                success = 6500;
                fail = 2000;
                break;
            case 3:
                success = 4500;
                fail = 3000;
                break;
            case 4:
                success = 3000;
                fail = 3500;
                break;
            case 5:
                success = 1000;
                fail = 4000;
                break;
            case 6:
                success = 500;
                fail = 3000;
                break;
            case 7:
                success = 100;
                fail = 1900;
                break;
            case 8:
                success = 50;
                fail = 1500;
                break;
            case 9:
                success = 1;
                fail = 1000;
                break;
            default:
                success = -1;
                fail = -1;
        }
        if (success==-1) {
            onNotAble(player,event);
        } else {
            if (result<=success){
                onSuccess(player,event);
            } else if(result<=success+fail) {
                onFail(player,event);
            } else {
                onBreak(player, event);
            }
        }
    }
    private void onNotAble(Player player,@NotNull InventoryClickEvent event) {  //풀강이거나 강화못하는 아이템
        player.playSound(player, Sound.BLOCK_ANVIL_BREAK,0.6f,1f);
        event.setCancelled(true);
    }

    private void onSuccess(Player player,@NotNull InventoryClickEvent event){  //성공시  강화단계 +1
        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,0.6f,1f);
        ItemStack item = event.getCurrentItem();
        List<Component> lore = item.lore();
    }

    private void onFail(Player player,@NotNull InventoryClickEvent event) { //실패시   강화단계 -1, 0이면 파괴
    }

    private void onBreak(Player player,@NotNull InventoryClickEvent event) {  //파괴시  아이템 삭제
        player.playSound(player, Sound.BLOCK_ANVIL_DESTROY,0.6f,1f);
    }
}
