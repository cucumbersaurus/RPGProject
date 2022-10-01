package project.rpg.player.name.title;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.name.base.TitleBase;
import project.rpg.player.name.base.TitleName;

import java.util.HashMap;
import java.util.Map;

public class HOGU extends TitleBase {  //https://www.notion.so/HOGU-6bb38af7552343be95a486c5743c8383 만들기 쉽다~~

    @Override
    public void onEnable() {
        //ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
    }

    @Override
    public void onDisable() {
        //ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
    }

    @Override
    public void eared() {
        //ㅋㅋㅋㅋㅋㅋㅋ
    }

    public HOGU(Player player) {
        super(player, TitleName.HOGU.getName(),TitleName.HOGU.getDescription(),TitleName.HOGU.getAcquisitionConditions());
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", player.getName());
        map.put("name", name);
        map.put("description", description);
        map.put("acquisitionConditions", acquisitionConditions);
        return map;
    }
}
