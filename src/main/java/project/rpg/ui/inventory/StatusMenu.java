package project.rpg.ui.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Status;

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
            setItem(" ",null,  Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }
        for(int i=0;i<9;i++){
            setItem(" ",null,  Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }
        for(int i=9;i<45;i+=9){
            setItem(" ",null,  Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }
        for(int i=8;i<45;i+=9){
            setItem(" ",null,  Material.BLACK_STAINED_GLASS_PANE, 1, i, "stats.background", false);
        }

        setItem("힘 strength", Collections.singletonList(""+human.getStrength()), Material.IRON_SWORD, 1, 12, "stats.strength", true);
        setItem("신속 speed", Collections.singletonList(""+human.getSpeed()), Material.FEATHER, 1, 14, "stats.speed", true);
        setItem("민첩 agility",Collections.singletonList(""+human.getAgility()),  Material.BLUE_STAINED_GLASS_PANE, 1, 20, "stats.agility", true);
        setItem("체력 health",Collections.singletonList(""+human.getHealth()),  Material.PINK_STAINED_GLASS_PANE, 1, 24, "stats.health", true);
        setItem("방어 defense",Collections.singletonList(""+human.getDefense()),  Material.IRON_CHESTPLATE, 1, 38, "stats.defense", true);
        setItem("행운 luck",Collections.singletonList(""+human.getLuck()),  Material.YELLOW_STAINED_GLASS_PANE, 1, 42, "stats.luck", true);
        setItem("손재주 handicraft",Collections.singletonList(""+human.getHandicraft()),  Material.MAGENTA_STAINED_GLASS_PANE, 1, 48, "stats.handicraft", true);
        setItem("마력 intelligence",Collections.singletonList(""+human.getIntelligence()),  Material.PURPLE_STAINED_GLASS_PANE, 1, 50, "stats.intelligence", true);
        setItem("정보 info", Arrays.asList("================",human.getPlayerName()," "," ", human.getLevel() + ".lv",
                "hp : " + human.getHealth(), "strength : " + human.getStrength(), "agility : " + human.getAgility(), "defense : " + human.getDefense(),
                "speed : " + human.getSpeed(), "luck : " + human.getLuck(), "intelligence : " + human.getIntelligence(), "handicraft : " + human.getHandicraft(),
                " ", "more : " + human.getAdditionalStatusPoint(), "================"),  Material.PLAYER_HEAD, 1, 31, "stats.info", false);

        setItem("현재 페이지/새로고침",Arrays.asList("현재 페이지를 나타냅니다.", "눌러서 새로고침"),  Material.BEACON, 1, 45, "stats.reload", false);
        setItem("현재 페이지/닫기",Collections.singletonList("페이지 닫기"),  Material.BARRIER, 1, 53, "stats.close", false);
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);
        String btn = getValue(event.getSlot());

        Status human = Status.getPlayerMap().get(event.getWhoClicked().getUniqueId());

        if(btn==null) return;

        switch (btn){
            case "stats.strength":
                human.addStrength(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.health":
                human.addHealth(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.agility":
                human.addAgility(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.speed":
                human.addSpeed(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.luck":
                human.addLuck(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.defense":
                human.addDefense(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.handicraft":
                human.addHandicraft(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.intelligence":
                human.addIntelligence(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                new StatusMenu(p);
                break;
            case "stats.reload":
                new StatusMenu(p);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1);
                setAttributes(p,human);
                break;
            case "stats.close":
                this.forceCloseGUI(p);
                break;
            default:
                break;
        }
    }
}