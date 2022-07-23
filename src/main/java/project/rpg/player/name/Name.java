package project.rpg.player.name;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.name.base.TitleBase;
import project.rpg.player.name.base.TitleName;

import java.util.*;

public class Name implements ConfigurationSerializable {  //이름과 칭호

    private Player _player;

    private  String _name; //이름
    private List<TitleBase> _titles = new ArrayList<>();   //칭호
    private TitleBase _selectedTitle = null;  //선택된 칭호

    public boolean addTitle(String name) {  //칭호 추가
        TitleBase title = TitleName.getTitle(_player, name);
        if (title != null) {
            _titles.add(title);
            if (_titles.size()==1) {
                changeTitle(name);
            }
        }
        return false;
    }

    public boolean addTitle(TitleName title) { //  //칭호 추가
        _titles.add(TitleName.getTitle(_player, title.name()));
        return false;
    }

    public boolean changeTitle(String name) {  //칭호 변경
        for (TitleBase t : _titles) {
            if (t.getName().equals(name)) {
                if (_selectedTitle !=null) {
                    _selectedTitle.onDisable();
                }
                _selectedTitle = t;
                t.onEnable();
                return true;
            }
        }
        return false;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());
        map.put("name", _name);

        Map<String, Object> titleMap = new HashMap<>();
        for(TitleBase i : _titles){
            titleMap.put(i.getName(), i.serialize());
        }
        map.put("titles", titleMap);
        if(_selectedTitle != null) map.put("selectedTitle", _selectedTitle.serialize());
        return map;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public List<TitleBase> getTitles() {  //칭호 전체 보여줄 때
        return _titles;
    }

    public void setTitles(List<TitleBase> titles) { _titles = titles; }

    public TitleBase getSelectedTitle() {  //현재 칭호 보여줄 때
        return _selectedTitle; //null 가능
    }

    public void setSelectedTitle(TitleBase selectedTitle) {
        _selectedTitle = selectedTitle;
    }

    public Name(Player player) {
        this._player = player;
        this._name = player.getName();
    }

    public static Name deserialize(Map<String, String>map){
        Name name = new Name(Objects.requireNonNull(Bukkit.getPlayer(map.get("player"))));
        name.setName(map.get("name"));

        Gson gson = new Gson();
        Map<String, String> titleMap = gson.fromJson(map.get("titles"), Map.class);

        for(TitleName title:TitleName.values()){
            String s = titleMap.get(title.getName());
            if(s != null){
                name.addTitle(title.getName());
                //TitleName.deserialize(s) 으로 바꿀 예정
            }
        }

        TitleBase.deserialize(gson.fromJson(map.get("selectedTitle"), Map.class));

        return null;
    }
}
