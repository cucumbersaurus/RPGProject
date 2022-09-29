package project.rpg.player.status.base

import org.bukkit.entity.Player
import project.rpg.player.status.Status

abstract class StatusBase protected constructor(status: StatusName) {
    //스텟 베이스
    private val statusName: String
    var value = 10 //스텟 수

    abstract fun effect(player: Player?) //스텟 효과
    open fun addValue(amount: Int, status: Status, player: Player?): Boolean {  //스텟 더하기
        if (status.additionalStatusPoint >= amount) {
            value += amount
            status.minAdditionalStatusPoint(amount)
            return true
        }
        return false
    }

    init {
        statusName = status.status
    }
}