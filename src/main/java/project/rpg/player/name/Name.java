package project.rpg.player.name;

import org.bukkit.entity.Player;
import project.rpg.player.name.base.TitleBase;
import project.rpg.player.name.base.TitleName;

import java.util.ArrayList;
import java.util.List;

public class Name {  //이름과 칭호

    private Player _player;

    private String _name;                  //이름
    private List<TitleBase> titles = new ArrayList<>();   //칭호
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
