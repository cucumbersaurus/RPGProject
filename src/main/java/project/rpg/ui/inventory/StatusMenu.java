package project.rpg.ui.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static net.kyori.adventure.text.Component.text;
import static project.rpg.manager.AttributeManager.setAttributes;

public class StatusMenu extends GuiBase{

    private static final HashMap<Player, Integer> onPage = new HashMap<>();

    public StatusMenu(@NotNull Player p) {
        super(p, 54, text("스텟 메뉴"));
    }

    @Override
    protected void init(@NotNull Player player)
    {
        Status human = Status.getPlayerMap().get(player.getUniqueId());
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

        setItem(text("힘 strength"),new ArrayList<>(Collections.singletonList(text(human.getStrength()))),Material.IRON_AXE, 1, 12, "stats.strength", true);
        setItem(text("신속 speed"),new ArrayList<>(Collections.singletonList(text(human.getSpeed()))),Material.FEATHER, 1, 14, "stats.speed", true);
        setItem(text("민첩 agility"),new ArrayList<>(Collections.singletonList(text(human.getAgility()))),  Material.IRON_SWORD, 1, 20, "stats.agility", true);
        setItem(text("체력 health"),new ArrayList<>(Collections.singletonList(text(human.getHealth()))),  Material.GOLDEN_APPLE, 1, 24, "stats.health", true);
        setItem(text("방어 defense"),new ArrayList<>(Collections.singletonList(text(human.getDefense()))),  Material.IRON_CHESTPLATE, 1, 38, "stats.defense", true);
        setItem(text("행운 luck"),new ArrayList<>(Collections.singletonList(text(human.getLuck()))),  Material.DIAMOND, 1, 42, "stats.luck", true);
        setItem(text("손재주 handicraft"),new ArrayList<>(Collections.singletonList(text(human.getHandicraft()))),  Material.IRON_PICKAXE, 1, 48, "stats.handicraft", true);
        setItem(text("마력 intelligence"),new ArrayList<>(Collections.singletonList(text(human.getIntelligence()))),  Material.BOOK, 1, 50, "stats.intelligence", true);
        setItem(text("정보 info"), new ArrayList<>(Arrays.asList(text("================"),text(human.getPlayerName()),text(" "),text(" "), text(human.getLevel() + ".lv"),
                text("hp : " + human.getHealth()), text("strength : " + human.getStrength()), text("agility : " + human.getAgility()), text("defense : " + human.getDefense()),
                text("speed : " + human.getSpeed()), text("luck : " + human.getLuck()), text("intelligence : " + human.getIntelligence()), text("handicraft : " + human.getHandicraft()),
                text(" "), text("more : " + human.getAdditionalStatusPoint()), text("================"))),  Material.PLAYER_HEAD, 1, 31, "stats.info", false);
        setItem(text("현재 페이지/새로고침"),new ArrayList<>(Arrays.asList(text("현재 페이지를 나타냅니다."), text("눌러서 새로고침"))),  Material.BEACON, 1, 45, "stats.reload", false);
        setItem(text("현재 페이지/닫기"),new ArrayList<>(Collections.singletonList(text("페이지 닫기"))),  Material.BARRIER, 1, 53, "stats.close", false);
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        String btn = getValue(event.getSlot());

        Status human = Status.getPlayerMap().get(event.getWhoClicked().getUniqueId());

        if(btn==null) return;

        switch (btn){
            case "stats.strength":
                human.addStrength(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.health":
                human.addHealth(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.agility":
                human.addAgility(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.speed":
                human.addSpeed(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.luck":
                human.addLuck(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.defense":
                human.addDefense(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.handicraft":
                human.addHandicraft(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.intelligence":
                human.addIntelligence(1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                new StatusMenu(player);
                break;
            case "stats.reload":
                new StatusMenu(player);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(player,human);
                break;
            case "stats.close":
                this.forceCloseGUI(player);
                break;
            default:
                break;
        }
    }
}