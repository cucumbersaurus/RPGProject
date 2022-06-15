package project.rpg.ui.chest;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import project.rpg.base.GuiBase;

import static net.kyori.adventure.text.Component.text;

public class MainMenu extends GuiBase {

    private static final String BACKGROUND = "mainMenu.background";
    private static final String FRIENDS_PARTY = "mainMenu.friends_party";
    private static final String QUESTS = "mainMenu.quests";
    private static final String REINFORCE = "mainMenu.reinforce";
    private static final String CRAFT = "mainMenu.craft";
    private static final String MY_PROFILE = "mainMenu.my_profile";
    private static final String DICTIONARY = "mainMenu.dictionary";
    private static final String WARP = "mainMenu.warp";
    private static final String SETTINGS = "mainMenu.settings";
    private static final String CLOSE = "mainMenu.close";

    public MainMenu(@NotNull Player player){
        super(player, 54,  text("메인 메뉴"));
    }

    @Override
    protected void init(@NotNull Player player) {
        for(int i = 0; i < 54; i++){
            setItem(" ", null, Material.WHITE_STAINED_GLASS_PANE, 1, i, BACKGROUND, false);
        }
        setItem("친구/파티", null, Material.SKELETON_SKULL, 1, 10, FRIENDS_PARTY, false);
        setItem("퀘스트", null, Material.MOJANG_BANNER_PATTERN, 1, 12, QUESTS, false);
        setItem("강화", null, Material.ANVIL, 1, 14, REINFORCE, false);
        setItem("제작", null, Material.CRAFTING_TABLE, 1, 16, CRAFT, false);
        setItem(getPlayerHead(player), 28, MY_PROFILE);
        setItem("도감", null, Material.KNOWLEDGE_BOOK, 1, 30, DICTIONARY, false);
        setItem("워프", null, Material.NETHER_STAR, 1, 32, WARP, false);
        setItem("설정", null, Material.FIREWORK_STAR, 1, 34, SETTINGS, false);
        setItem("닫기", null, Material.BARRIER, 1, 49, CLOSE, false);

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
    }
}
