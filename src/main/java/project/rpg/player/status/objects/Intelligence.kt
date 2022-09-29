package project.rpg.player.status.objects;

import org.bukkit.entity.Player;
import project.rpg.player.User;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;

public class Intelligence extends StatusBase {   //마력

    public Intelligence() {
        super(StatusName.INTELLIGENCE);
    }

    @Override
    public void effect(Player player) {
        User.getPlayer(player).getMana().setMaxMana(this._value*10);
    }

    public static Intelligence deserialize(Map<String, String> map){
        Intelligence intelligence = new Intelligence();
        intelligence.setValue(Integer.parseInt(map.get("value")));
        return intelligence;
    }

}
