package project.rpg.player.level

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import project.rpg.player.User.Companion.getPlayer

data class Levels(
    private val _player: Player, //레벨 업 채팅 보낼 때 필요

    var level: Long = 0, //레벨
    var exp: Long = 0, //경험치

) {

    //다음까지 남은 경험치
    val needForNextLev: Long
        get() =//다음까지 남은 경험치
            5 * (level * level + level)

    fun hasEnoughExp(): Boolean {  //경험치 충분한지 확인
        return exp >= needForNextLev
    }

    fun levelUp() {   //레벨업!
        exp = exp - needForNextLev
        getPlayer(_player!!)!!.status.addAdditionalStatusPoint(5)
        getPlayer(_player)!!.mana.reloadMaxMana()
        level++
        _player.sendMessage(ChatColor.YELLOW.toString() + "Levels Up!")
    }

    fun addExp(amount: Long) {  //경험치 늘리기 나중에 몬스너나 퀘스트에서 이거 쓰면 될듯
        exp += amount
        while (hasEnoughExp()) {
            levelUp()
        }
    }
}