package project.rpg.player.name;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.name.base.TitleBase;
import project.rpg.player.name.base.TitleName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Name implements ConfigurationSerializable {  //이름과 칭호

    private final Player _player;

    private final String _name;                  //이름
    private final List<TitleBase> titles = new ArrayList<>();   //칭호
    private TitleBase selectedTitle = null;  //선택된 칭호

    public boolean addTitle(String name) {  //칭호 추가
        TitleBase title = TitleName.getTitle(_player,name);
        if (title != null) {
            titles.add(title);
            if (titles.size()==1) {
                changeTitle(name);
            }
        }
        return false;
    }

    public boolean changeTitle(String name) {  //칭호 변경
        for (TitleBase t : titles) {
            if (t.getName().equals(name)) {
                if (selectedTitle!=null) {
                    selectedTitle.onDisable();
                }
                selectedTitle = t;
                t.onEnable();
                return true;
            }
        }
        return false;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", _name);

        for(TitleBase i :titles){
            map.put(i.getName(), i.serialize());
        }
        return map;
    }

    public String getName() {
        return _name;
    }

    public List<TitleBase> getTitles() {  //칭호 전체 보여줄 때
        return titles;
    }

    public TitleBase getSelectedTitle() {  //현재 칭호 보여줄 때
        return selectedTitle; //null 가능
    }

    public Name(Player player) {
        this._player = player;
        this._name = player.getName();
    }
}
