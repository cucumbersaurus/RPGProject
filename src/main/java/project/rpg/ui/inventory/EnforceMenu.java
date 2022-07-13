package project.rpg.ui.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static net.kyori.adventure.text.Component.text;

public class EnforceMenu extends GuiBase{

    public EnforceMenu(@NotNull Player player){
        super(player, 54,  text("메인 메뉴"));
    }

    @Override
    protected void init(@NotNull Player player) {
        for(int i = 0; i < 54; i++){
            setItem(text(" "), null, Material.WHITE_STAINED_GLASS_PANE, 1, i, "enforce.background", false);
        }
        setItem(text("강화하기"), null, Material.ANVIL, 1, 31, "enforce.enable", false);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        String value = getValue(event.getSlot());
        switch (value) {
            case "enforce.background":
                break;
            case "enforce.enable":
                //enforce()
                break;
            case "enforce.close":
                forceCloseGUI( (Player) event.getWhoClicked());
                break;
            default:
                break;
        }
    }

    private void enforce(short level,Player player) {
        Random random = new Random();
        int result = random.nextInt(10000);
        int success,fail;
        switch (level) {
            case 0:
                success = 9000;
                fail = 900;
            case 1:
                success = 8000;
                fail = 1500;
            case 2:
                success = 6500;
                fail = 2000;
            case 3:
                success = 4500;
                fail = 3000;
            case 4:
                success = 3000;
                fail = 3500;
            case 5:
                success = 1000;
                fail = 4000;
            case 6:
                success = 500;
                fail = 3000;
            case 7:
                success = 100;
                fail = 1900;
            case 8:
                success = 50;
                fail = 1500;
            case 9:
                success = 1;
                fail = 1000;
            default:
                success = -1;
                fail = -1;
        }
        if (success==-1) {
            onNotAble(player);
        } else {
            if (result<=success){
                onSuccess(player);
            } else if(result<=success+fail) {
                onFail(player);
            } else {
                onBreak(player);
            }
        }
    }
    private void onNotAble(Player player) {  //풀강이거나 강화못하는 아이템
    }

    private void onSuccess(Player player){  //성공시  강화단계 +1
    }

    private void onFail(Player player) { //실패시   강화단계 -1, 0이면 파괴
    }

    private void onBreak(Player player) {  //파괴시  아이템 삭제
    }
}
