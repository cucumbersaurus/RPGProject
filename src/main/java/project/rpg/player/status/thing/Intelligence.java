package project.rpg.player.status.thing;

import org.bukkit.entity.Player;
import project.rpg.player.User;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

public class Intelligence extends StatusBase {   //마력

    public Intelligence() {
        super(StatusName.INTELLIGENCE);
    }

    @Override
    public void effect(Player player) {
        User.getPlayer(player).getMana().setMaxMana(this._value*10);
    }

}
