package project.rpg.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestListUI {

    public static Map<Player, QuestListUI> playerQuestMap_ = new HashMap<>();

    private final ArrayList<Score> scoreArray_ = new ArrayList<>();

    private Scoreboard scoreboard_;
    private final Player p_;

    public QuestListUI(Player p){
        playerQuestMap_.put(p, this);
        p_= p;
    }

    public void setContents(List<String> contents){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard_ = manager.getNewScoreboard();

        Objective objective = scoreboard_.registerNewObjective("quest", "dummy");
        objective.setDisplayName(ChatColor.YELLOW +"퀘스트 목록");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int len = contents.size();
        scoreArray_.clear();

        for(String s : contents){
            len--;
            Score score = objective.getScore(s);
            score.setScore(len);
            scoreArray_.add(score);
        }
        p_.setScoreboard(scoreboard_);
    }

    public void showScoreboard(){
        for(Score score : scoreArray_) {
            score.getObjective().setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }

    public void hideScoreboard(){
        scoreboard_.clearSlot(DisplaySlot.SIDEBAR);
    }

}
