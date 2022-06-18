package project.rpg.ui.inventory;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.text;

public class MainMenu extends GuiBase {

    public MainMenu(@NotNull Player player){
        super(player, 54,  text("메인 메뉴"));
    }

    @Override
    protected void init(@NotNull Player player) {
        for(int i = 0; i < 54; i++){
            setItem(" ", null, Material.WHITE_STAINED_GLASS_PANE, 1, i, Button.BACKGROUND._name, false);
        }
        setItem("친구/파티", null, Material.SKELETON_SKULL, 1, 10, Button.FRIENDS_PARTY._name, false);
        setItem("퀘스트", null, Material.MOJANG_BANNER_PATTERN, 1, 12, Button.QUESTS._name, false);
        setItem("강화", null, Material.ANVIL, 1, 14, Button.REINFORCE._name, false);
        setItem("제작", null, Material.CRAFTING_TABLE, 1, 16, Button.CRAFT._name, false);
        setItem(getPlayerHead(player), 28, Button.MY_PROFILE._name);
        setItem("도감", null, Material.KNOWLEDGE_BOOK, 1, 30,Button. DICTIONARY._name, false);
        setItem("워프", null, Material.NETHER_STAR, 1, 32, Button.WARP._name, false);
        setItem("설정", null, Material.FIREWORK_STAR, 1, 34, Button.SETTINGS._name, false);
        setItem("닫기", null, Material.BARRIER, 1, 49, Button.CLOSE._name, false);

    }

    private ItemStack getPlayerHead(@NotNull Player player) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        meta.setOwningPlayer(player);
        meta.displayName(text("내 프로필").color(TextColor.color(0xffff55)));
        playerHead.setItemMeta(meta);
        return playerHead;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        String value = getValue(event.getSlot());
        switch (Button.valueOf(value)) {
            case BACKGROUND:
                break;
            case FRIENDS_PARTY:
                break;
            case QUESTS:
                break;
            case REINFORCE:
                break;
            case CRAFT:
                break;
            case MY_PROFILE:
                new StatusMenu((Player) event.getWhoClicked());
                break;
            case DICTIONARY:
                break;
            case WARP:
                break;
            case SETTINGS:
                break;
            case CLOSE:
                forceCloseGUI( (Player) event.getWhoClicked());
                break;
            default:
                break;
        }
    }

    private enum Button{

        BACKGROUND("background"),
        FRIENDS_PARTY("friends_party"),
        QUESTS("quests"),
        REINFORCE("reinforce"),
        CRAFT("craft"),
        MY_PROFILE("my_profile"),
        DICTIONARY("dictionary"),
        WARP("warp"),
        SETTINGS("settings"),
        CLOSE("close");

        public final String _name;

        Button(String name) {
            _name = name;
        }
    }
}
