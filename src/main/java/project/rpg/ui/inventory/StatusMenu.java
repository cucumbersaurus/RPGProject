package project.rpg.ui.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Levels;
import project.rpg.player.info.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static net.kyori.adventure.text.Component.text;
import static project.rpg.manager.AttributeManager.setAttributes;

public class StatusMenu extends GuiBase{

    private Status _status;
    private Player _player;

    private static final HashMap<Player, Integer> onPage = new HashMap<>();

    public StatusMenu(@NotNull Player player) {
        super(player, 54, text("스텟 메뉴"));
    }

    @Override
    protected void init(@NotNull Player player)
    {
        _player = player;
        _status = Status.getPlayerMap().get(player.getUniqueId());
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

        setItem(text("힘 strength"), new ArrayList<>(Collections.singletonList(text(_status.getStrength()))), Material.IRON_AXE, 1, 12, "stats.strength", true);
        setItem(text("신속 speed"), new ArrayList<>(Collections.singletonList(text(_status.getSpeed()))), Material.FEATHER, 1, 14, "stats.speed", true);
        setItem(text("민첩 agility"), new ArrayList<>(Collections.singletonList(text(_status.getAgility()))), Material.IRON_SWORD, 1, 20, "stats.agility", true);
        setItem(text("체력 health"), new ArrayList<>(Collections.singletonList(text(_status.getHealth()))), Material.GOLDEN_APPLE, 1, 24, "stats.health", true);
        setItem(text("방어 defense"), new ArrayList<>(Collections.singletonList(text(_status.getDefense()))), Material.IRON_CHESTPLATE, 1, 38, "stats.defense", true);
        setItem(text("행운 luck"), new ArrayList<>(Collections.singletonList(text(_status.getLuck()))), Material.DIAMOND, 1, 42, "stats.luck", true);
        setItem(text("손재주 handicraft"), new ArrayList<>(Collections.singletonList(text(_status.getHandicraft()))), Material.IRON_PICKAXE, 1, 48, "stats.handicraft", true);
        setItem(text("마력 intelligence"), new ArrayList<>(Collections.singletonList(text(_status.getIntelligence()))), Material.BOOK, 1, 50, "stats.intelligence", true);
        setItem(text("정보 info"), new ArrayList<>(Arrays.asList(text("================"), text(_status.getPlayerName()),text(Levels.getPlayerMap(_player).get(Levels.LEVEL) + ".lv"), text(Levels.getPlayerMap(_player).get(Levels.EXP) + " / " + Levels.getNeedForNextLev(player)) , text(" "), text(" "),
                text("hp : " + _status.getHealth()), text("strength : " + _status.getStrength()), text("agility : " + _status.getAgility()), text("defense : " + _status.getDefense()),
                                                               text("speed : " + _status.getSpeed()), text("luck : " + _status.getLuck()), text("intelligence : " + _status.getIntelligence()), text("handicraft : " + _status.getHandicraft()),
                                                               text(" "), text("more : " + _status.getAdditionalStatusPoint()), text("================"))), Material.PLAYER_HEAD, 1, 31, "stats.info", false);
        setItem(text("현재 페이지/새로고침"),new ArrayList<>(Arrays.asList(text("현재 페이지를 나타냅니다."), text("눌러서 새로고침"))),  Material.BEACON, 1, 45, "stats.reload", false);
        setItem(text("현재 페이지/닫기"),new ArrayList<>(Collections.singletonList(text("페이지 닫기"))),  Material.BARRIER, 1, 53, "stats.close", false);
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        //Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        String btn = getValue(event.getSlot());
        if(btn==null) return;

        switch (btn){
            case "stats.strength":
                _status.addStrength(1);
                reloadUi();
                break;
            case "stats.health":
                _status.addHealth(1);
                reloadUi();
                break;
            case "stats.agility":
                _status.addAgility(1);
                reloadUi();
                break;
            case "stats.speed":
                _status.addSpeed(1);
                reloadUi();
                break;
            case "stats.luck":
                _status.addLuck(1);
                reloadUi();
                break;
            case "stats.defense":
                _status.addDefense(1);
                reloadUi();
                break;
            case "stats.handicraft":
                _status.addHandicraft(1);
                reloadUi();
                break;
            case "stats.intelligence":
                _status.addIntelligence(1);
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
        setAttributes(_player, _status);
        init(_player);
    }


}