package project.rpg.player.name.base;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import project.rpg.player.name.title.HOGU;
import project.rpg.player.name.title.MvpPP;

import java.util.HashMap;
import java.util.Map;

public abstract class TitleBase implements ConfigurationSerializable {  //칭호 베이스

    protected final Player _player;  //플레이어 onEnable 이나 disable 에서 쓸때
    protected final String _name;   //칭호 이릉
    protected final String _description;  //칭호 설명
    protected final String _acquisitionConditions;  //획득 조건

    public abstract void onEnable();  //착용시 효과    -> 교감 신경
    public abstract void onDisable();  //착용 해제시   -> 부교감 신경 //???? //ㅋㅋㅋㅋ
    public abstract void eared();   //획득시 발동

    public String getName() {
        return _name;
    }

    protected TitleBase(Player player,String name) {
        this(player,name,null,null);
    }

    protected TitleBase(Player player,String name,String description,String condition) {
        this._player = player;
        this._name = name;
        this._description = description;
        this._acquisitionConditions = condition;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player);
        map.put("name", _name);
        map.put("description", _description);
        map.put("acquisitionConditions", _acquisitionConditions);
        return map;
    }

    public static @Nullable TitleBase deserialize(@NotNull Map<String, String> map) {
        TitleBase titleBase =null;
        Player player = Bukkit.getPlayer(map.get("player"));
        String typt = map.get("name");
        switch (typt) {
            case "[MVP++]":
                titleBase = new MvpPP(player);
                break;
            case "HOGU":
                titleBase = new HOGU(player);
                break;
            default:
                break;
        }
        return titleBase;
    }

}
