package project.rpg.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreboardUI {

    protected static final Map<Player, ScoreboardUI> playerQuestMap_ = new HashMap<>();
    private final ArrayList<Score> scoreArray_ = new ArrayList<>();
    private final Player player_;
    private Scoreboard scoreboard_;


    public ScoreboardUI(Player player){
        playerQuestMap_.put(player, this);
        player_ = player;
    }

    public void setContents(List<String> contents){//contents 의 문자열을 각각의 줄로 위부터 아래로 스코어보드에 표시
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
        player_.setScoreboard(scoreboard_);
    }

    public static void showScoreboard(Player player){//특정 플레이어에게 스코어보드 ui 표시
        ScoreboardUI.playerQuestMap_.get(player).showScoreboard();
    }
    public static void hideScoreboard(Player player){
        ScoreboardUI.playerQuestMap_.get(player).hideScoreboard();//특정 플레이어의 스코어보드 ui 숨기기
    }
    public static ScoreboardUI getScoreboard(Player player){//툭정 플레이어의 ScoreboardUI 오브젝트 가져오기
        return ScoreboardUI.playerQuestMap_.getOrDefault(player, null);
    }
    private void showScoreboard(){//스코어보드 보여주기 내부 함수
        for(Score score : scoreArray_) {
            score.getObjective().setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }
    private void hideScoreboard(){//스코어보드 숨기기 내부함수
        scoreboard_.clearSlot(DisplaySlot.SIDEBAR);
    }

}
