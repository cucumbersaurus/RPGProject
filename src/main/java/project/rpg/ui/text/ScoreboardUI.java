package project.rpg.ui.text;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import project.rpg.textComponets.color.DefaultTextColors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.kyori.adventure.text.Component.text;

public class ScoreboardUI {

    protected static final Map<Player, ScoreboardUI> _scoreboardMap = new HashMap<>();
    private final Scoreboard _scoreboard;
    private  Objective _objective;
    private final Player _player;

    public ScoreboardUI(Player player){
        _player = player;
        _scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        _objective = _scoreboard.registerNewObjective("quest", "dummy", text("quest"));
        _objective.displayName(text("퀘스트 목록").color(DefaultTextColors.YELLOW.getColor()));

        _scoreboardMap.put(player, this);
    }

    public void setContents(List<String> contents){

        _objective.unregister();
        _objective = _scoreboard.registerNewObjective("quest", "dummy", text("quest"));
        _objective.displayName(text("퀘스트 목록").color(DefaultTextColors.YELLOW.getColor()));

        int priority = contents.size();
        Map<String, Integer> doubleCheck = new HashMap<>();
        for(String s : contents){
            priority--;

            if(doubleCheck.containsKey(s)) doubleCheck.put(s, doubleCheck.get(s)+1);
            else doubleCheck.put(s, 0);

            Score score = _objective.getScore(s + " ".repeat(Math.max(0, doubleCheck.get(s))));


            score.setScore(priority);
        }
        _player.setScoreboard(_scoreboard);
    }

    public void showScoreboard(){
        _objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void hideScoreboard(){
        _scoreboard.clearSlot(DisplaySlot.SIDEBAR);
    }

    public static ScoreboardUI getPlayer(Player player){
        return _scoreboardMap.get(player);
    }

}
