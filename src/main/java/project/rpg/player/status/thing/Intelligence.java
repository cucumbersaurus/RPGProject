package project.rpg.player.status.thing;

import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import static project.rpg.player.User._playerMap;

public class Intelligence extends StatusBase {   //마력

    public Intelligence() {
        super(StatusName.INTELLIGENCE);
    }

    @Override
    public void effect(Player player) {
        _playerMap.get(player.getUniqueId()).getMana().setMaxMana(this._value*10);
    }

}
