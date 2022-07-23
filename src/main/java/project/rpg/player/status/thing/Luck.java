package project.rpg.player.status.thing;

import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;

public class Luck extends StatusBase {   //행운

    public Luck() {
        super(StatusName.LUCK);
    }

    @Override
    public void effect(Player player) {
    }

    public static Luck deserialize(Map<String, String> map){
        Luck luck = new Luck();
        luck.setValue(Integer.parseInt(map.get("value")));
        return luck;
    }

}
