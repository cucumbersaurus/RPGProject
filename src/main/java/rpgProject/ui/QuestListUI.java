package rpgProject.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestListUI {

    public static HashMap<Player, QuestListUI> playerQuestMap_ = new HashMap<>();

    private ArrayList<Score> scoreArray_ = new ArrayList<>();

    private Scoreboard scoreboard_;

    public QuestListUI(Player p){
        playerQuestMap_.put(p, this);
        //init(p);
        //setScoreboard(p, Arrays.asList("아마도","일단은", "아직", "퀘스트", "없는듯", "히히", "자", "테스트는", "여기까지" ));
    }

    public void init(Player p){
        Objective objective;
        Score score;

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard_ = manager.getNewScoreboard();

        objective = scoreboard_.registerNewObjective("quest", "dummy");
        score = objective.getScore("아직 퀘스트 없음");
        Score score1 = objective.getScore("adsf");
        objective.setDisplayName(ChatColor.YELLOW + "퀘스트 목록");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        score.setScore(0);
        score1.setScore(1);

        p.setScoreboard(scoreboard_);

        scoreArray_.add(score);
    }

    public void setScoreboard(Player p, List<String> contents){
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
        p.setScoreboard(scoreboard_);

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
