package rpgProject.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestListUI {

    public static HashMap<Player, QuestListUI> map = new HashMap<>();

    private ArrayList<Score> scoreArray = new ArrayList<>();

    private Scoreboard scoreboard;

    public QuestListUI(Player p){
        map.put(p, this);
        init(p);
    }

    public void init(Player p){
        Objective objective;
        Score score;
        Objective objective1;
        Score score1;

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();

        objective = scoreboard.registerNewObjective("quest", "dummy");
        score = objective.getScore("아직 퀘스트 없음");
        objective.setDisplayName(ChatColor.YELLOW + "퀘스트 목록");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        score.setScore(0);

        objective1 = scoreboard.registerNewObjective("quest1", "dummy");
        score1 = objective1.getScore("아직 퀘스트 없음1");
        objective1.setDisplayName(ChatColor.YELLOW + "퀘스트 목록1");
        objective1.setDisplaySlot(DisplaySlot.SIDEBAR);
        score1.setScore(1);

        p.setScoreboard(scoreboard);

        scoreArray.add(score);
    }

    public void setContents(ArrayList<String> contents){
        scoreArray.clear();
        for(String s : contents){
            Objective objective = scoreboard.registerNewObjective("quest", "dummy");
            Score score = objective.getScore(s);

        }
    }

    public void showScoreboard(){
        for(Score score : scoreArray) {
            score.getObjective().setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }

    public void hideScoreboard(){
        scoreboard.clearSlot(DisplaySlot.SIDEBAR);
    }

}
