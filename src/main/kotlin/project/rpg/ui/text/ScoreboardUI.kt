package project.rpg.ui.text

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot.SIDEBAR
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import project.rpg.textComponets.color.DefaultTextColors.YELLOW
import kotlin.math.max

class ScoreboardUI(private val _player: Player) {
    private val _scoreboard: Scoreboard
    private var _objective: Objective

    init {
        _scoreboard = Bukkit.getScoreboardManager().newScoreboard
        _objective = _scoreboard.registerNewObjective("quest", "dummy", Component.text("quest"))
        _objective.displayName(Component.text("퀘스트 목록").color(YELLOW.color))
        _scoreboardMap[_player] = this
    }

    fun setContents(contents: List<String>) {
        _objective.unregister()
        _objective = _scoreboard.registerNewObjective("quest", "dummy", Component.text("quest"))
        _objective.displayName(Component.text("퀘스트 목록").color(YELLOW.color))
        var priority = contents.size
        val doubleCheck: MutableMap<String, Int> = HashMap()
        for (s in contents) {
            priority--
            if (doubleCheck.containsKey(s)) doubleCheck[s] = doubleCheck[s]!! + 1 else doubleCheck[s] = 0
            val score = _objective.getScore(
                s + " ".repeat(
                    max(0.0, doubleCheck[s]!!.toDouble()).toInt()
                )
            )
            score.score = priority
        }
        _player.scoreboard = _scoreboard
    }

    fun showScoreboard() {
        _objective.displaySlot = SIDEBAR
    }

    fun hideScoreboard() {
        _scoreboard.clearSlot(SIDEBAR)
    }

    companion object {
        protected val _scoreboardMap: MutableMap<Player, ScoreboardUI> = HashMap()
        fun getPlayer(player: Player): ScoreboardUI? {
            return _scoreboardMap[player]
        }
    }
}
