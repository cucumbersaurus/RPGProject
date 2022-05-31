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

    protected static final Map<Player, ScoreboardUI> _playerQuestMap = new HashMap<>();
    private final ArrayList<Score> _scoreArray = new ArrayList<>();
    private final Player _player;
    private Scoreboard _scoreboard;


    public ScoreboardUI(Player player){
        _playerQuestMap.put(player, this);
        _player = player;
    }

    public void setContents(List<String> contents){//contents 의 문자열을 각각의 줄로 위부터 아래로 스코어보드에 표시
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        _scoreboard = manager.getNewScoreboard();

        Objective objective = _scoreboard.registerNewObjective("quest", "dummy");
        objective.setDisplayName(ChatColor.YELLOW +"퀘스트 목록");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int len = contents.size();
        _scoreArray.clear();

        for(String s : contents){
            len--;
            Score score = objective.getScore(s);
            score.setScore(len);
            _scoreArray.add(score);
        }
        _player.setScoreboard(_scoreboard);
    }

    public static void showScoreboard(Player player){//특정 플레이어에게 스코어보드 ui 표시
        ScoreboardUI._playerQuestMap.get(player).showScoreboard();
    }
    public static void hideScoreboard(Player player){
        ScoreboardUI._playerQuestMap.get(player).hideScoreboard();//특정 플레이어의 스코어보드 ui 숨기기
    }
    public static ScoreboardUI getScoreboard(Player player){//툭정 플레이어의 ScoreboardUI 오브젝트 가져오기
        return ScoreboardUI._playerQuestMap.getOrDefault(player, null);
    }
    private void showScoreboard(){//스코어보드 보여주기 내부 함수
        for(Score score : _scoreArray) {
            score.getObjective().setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }
    private void hideScoreboard(){//스코어보드 숨기기 내부함수
        _scoreboard.clearSlot(DisplaySlot.SIDEBAR);
    }

}
