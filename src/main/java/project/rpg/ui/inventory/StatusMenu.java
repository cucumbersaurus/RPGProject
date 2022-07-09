package project.rpg.ui.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.Human;
import project.rpg.player.status.Stats;
import project.rpg.player.status.base.StatusName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static net.kyori.adventure.text.Component.text;

public class StatusMenu extends GuiBase{

    private Human _human;
    private Stats _status;
    private Player _player;

    private static final HashMap<Player, Integer> onPage = new HashMap<>();

    public StatusMenu(@NotNull Player player) {
        super(player, 54, text("스텟 메뉴"));
    }

    @Override
    protected void init(@NotNull Player player)
    {
        _player = player;
        _human = Human.getPlayer(player);
        _status = _human.getStats();
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

        setItem(text("힘 strength"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.STRENGTH.getName())))), Material.IRON_AXE, 1, 12, "stats.strength", true);
        setItem(text("신속 speed"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.SPEED.getName())))), Material.FEATHER, 1, 14, "stats.speed", true);
        setItem(text("민첩 agility"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.AGILITY.getName())))), Material.IRON_SWORD, 1, 20, "stats.agility", true);
        setItem(text("체력 health"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.HEALTH.getName())))), Material.GOLDEN_APPLE, 1, 24, "stats.health", true);
        setItem(text("방어 defense"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.DEFENSE.getName())))), Material.IRON_CHESTPLATE, 1, 38, "stats.defense", true);
        setItem(text("행운 luck"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.LUCK.getName())))), Material.DIAMOND, 1, 42, "stats.luck", true);
        setItem(text("손재주 handicraft"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.HANDICRAFT.getName())))), Material.IRON_PICKAXE, 1, 48, "stats.handicraft", true);
        setItem(text("마력 intelligence"), new ArrayList<>(Collections.singletonList(text(_status.getStatus(StatusName.INTELLIGENCE.getName())))), Material.BOOK, 1, 50, "stats.intelligence", true);
        setItem(text("정보 info"), new ArrayList<>(Arrays.asList(text("================"), text(_human.getName().getName()), text(_human.getLevel().getLevel()+ ".lv"), text(_human.getLevel().getExp() + " / " + _human.getLevel().getNeedForNextLev()) , text(" "), text(" "),
                                                               text("hp : " + _status.getStatus(StatusName.HEALTH.getName())), text("strength : " + _status.getStatus(StatusName.STRENGTH.getName())), text("agility : " + _status.getStatus(StatusName.AGILITY.getName())), text("defense : " + _status.getStatus(StatusName.DEFENSE.getName())),
                                                               text("speed : " + _status.getStatus(StatusName.SPEED.getName())), text("luck : " + _status.getStatus(StatusName.LUCK.getName())), text("intelligence : " + _status.getStatus(StatusName.INTELLIGENCE.getName())), text("handicraft : " + _status.getStatus(StatusName.HANDICRAFT.getName())),
                                                               text(" "), text("more : " + _status.getAdditionalStatusPoint()), text("================"))), Material.PLAYER_HEAD, 1, 31, "stats.info", false);
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
                _status.addStatus(StatusName.STRENGTH.getName(), 1);
                reloadUi();
                break;
            case "stats.health":
                _status.addStatus(StatusName.HEALTH.getName(), 1);
                reloadUi();
                break;
            case "stats.agility":
                _status.addStatus(StatusName.AGILITY.getName(), 1);
                reloadUi();
                break;
            case "stats.speed":
                _status.addStatus(StatusName.SPEED.getName(), 1);
                reloadUi();
                break;
            case "stats.luck":
                _status.addStatus(StatusName.LUCK.getName(), 1);
                reloadUi();
                break;
            case "stats.defense":
                _status.addStatus(StatusName.DEFENSE.getName(), 1);
                reloadUi();
                break;
            case "stats.handicraft":
                _status.addStatus(StatusName.HANDICRAFT.getName(), 1);
                reloadUi();
                break;
            case "stats.intelligence":
                _status.addStatus(StatusName.INTELLIGENCE.getName(), 1);
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
        _human.getStats().reloadMap();
        init(_player);
    }
}