package project.rpg.player.status.objects;

import org.bukkit.entity.Player;
import project.rpg.player.status.base.StatusBase;
import project.rpg.player.status.base.StatusName;

import java.util.Map;

public class Handicraft extends StatusBase{   //손재주

    public Handicraft() {
        super(StatusName.HANDICRAFT);
    }

    @Override
    public void effect(Player player) {
    }

    public static Handicraft deserialize(Map<String, String> map){
        Handicraft handicraft = new Handicraft();
        handicraft.setValue(Integer.parseInt(map.get("value")));
        return handicraft;
    }

}
