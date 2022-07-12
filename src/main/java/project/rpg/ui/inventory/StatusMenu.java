package project.rpg.ui.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.User;
import project.rpg.player.status.Status;
import project.rpg.player.status.base.StatusName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static net.kyori.adventure.text.Component.text;

public class StatusMenu extends GuiBase{

    private User _user;
    private Status _status;
    private Player _player;

    public StatusMenu(@NotNull Player player) {
        super(player, 54, text("스텟 메뉴"));
    }

    @Override
    protected void init(@NotNull Player player)
    {
        _player = player;
        _user = User.getPlayer(player);
        _status = _user.getStatus();
        for(int i=0;i<54;i++){
            setItem(text(" "),null,  Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }
        for(int i=0;i<9;i++){
            setItem(text(" "),null,  Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }
        for(int i=9;i<45;i+=9){
            setItem(text(" "),null,  Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }
        for(int i=8;i<45;i+=9){
            setItem(text(" "),null,  Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }

        setItem(text("힘 strength"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.STRENGTH)))), Material.IRON_AXE, 1, 12, "stats.strength", true);
        setItem(text("신속 speed"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.SPEED)))), Material.FEATHER, 1, 14, "stats.speed", true);
        setItem(text("민첩 agility"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.AGILITY)))), Material.IRON_SWORD, 1, 20, "stats.agility", true);
        setItem(text("체력 health"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.HEALTH)))), Material.GOLDEN_APPLE, 1, 24, "stats.health", true);
        setItem(text("방어 defense"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.DEFENSE)))), Material.IRON_CHESTPLATE, 1, 38, "stats.defense", true);
        setItem(text("행운 luck"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.LUCK)))), Material.DIAMOND, 1, 42, "stats.luck", true);
        setItem(text("손재주 handicraft"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.HANDICRAFT)))), Material.IRON_PICKAXE, 1, 48, "stats.handicraft", true);
        setItem(text("마력 intelligence"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.INTELLIGENCE)))), Material.BOOK, 1, 50, "stats.intelligence", true);

        setItem(text("정보 info"), new ArrayList<>(Arrays.asList(
                text("================"),
                text(_user.getName().getName()),
                text(_user.getLevel().getLevel()+ ".lv"),
                text(_user.getLevel().getExp() + " / " + _user.getLevel().getNeedForNextLev()) ,
                text(" "),
                text(" "),
                text("hp : " + _status.getStatus(StatusName.HEALTH)),
                text("strength : " + _status.getStatus(StatusName.STRENGTH)),
                text("agility : " + _status.getStatus(StatusName.AGILITY)),
                text("defense : " + _status.getStatus(StatusName.DEFENSE)),
                text("speed : " + _status.getStatus(StatusName.SPEED)),
                text("luck : " + _status.getStatus(StatusName.LUCK)),
                text("intelligence : " + _status.getStatus(StatusName.INTELLIGENCE)),
                text("handicraft : " + _status.getStatus(StatusName.HANDICRAFT)),
                text(" "),
                text("more : " + _status.getAdditionalStatusPoint()),
                text("================"))), Material.PLAYER_HEAD,
                1, 31, "stats.info", false);

        setItem(text("현재 페이지/새로고침"),new ArrayList<>(Arrays.asList(text("현재 페이지를 나타냅니다."), text("눌러서 새로고침"))),  Material.BEACON, 1, 45, "stats.reload", false);
        setItem(text("현재 페이지/닫기"),new ArrayList<>(Collections.singletonList(text("페이지 닫기"))),  Material.BARRIER, 1, 53, "stats.close", false);
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        event.setCancelled(true);
        String btn = getValue(event.getSlot());
        if(btn==null) return;

        switch (btn){
            case "stats.strength":
                _status.addStatus(StatusName.STRENGTH, 1);
                reloadUi();
                break;
            case "stats.health":
                _status.addStatus(StatusName.HEALTH, 1);
                reloadUi();
                break;
            case "stats.agility":
                _status.addStatus(StatusName.AGILITY, 1);
                reloadUi();
                break;
            case "stats.speed":
                _status.addStatus(StatusName.SPEED, 1);
                reloadUi();
                break;
            case "stats.luck":
                _status.addStatus(StatusName.LUCK, 1);
                reloadUi();
                break;
            case "stats.defense":
                _status.addStatus(StatusName.DEFENSE, 1);
                reloadUi();
                break;
            case "stats.handicraft":
                _status.addStatus(StatusName.HANDICRAFT, 1);
                reloadUi();
                break;
            case "stats.intelligence":
                _status.addStatus(StatusName.INTELLIGENCE, 1);
                reloadUi();
                break;
            case "stats.reload":
               reloadUi();
                break;
            case "stats.close":
                this.forceCloseGUI(_player);
                break;
            default:
                break;
        }
    }

    private void reloadUi() {
        _player.playSound(_player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
        _user.getStatus().reloadMap();
        init(_player);
    }
}