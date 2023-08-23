package project.rpg.player.mana

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.monun.heartbeat.coroutines.Suspension
import kotlinx.coroutines.launch
import project.rpg.player.User
import project.rpg.player.status.Status
import project.rpg.player.status.base.StatusName

data class Mana(
    val status: Status,
    var mana: Int = status.getStatusValues(StatusName.INTELLIGENCE) * 10,
    var maxMana: Int = status.getStatusValues(StatusName.INTELLIGENCE) * 10,
) {

    fun useMana(amount: Int): Boolean {  //마나 사용 마법이나 스킬에 넣기
        if (amount <= mana) {
            mana -= amount
            return true
        }
        return false
    }

    fun addMana(amount: Int): Boolean {  //마나 충전할 때 쓰는거 포션이나 자연재생
        if (amount + mana <= maxMana) {
            mana += amount
            return true
        }
        return false
    }

    fun reloadMaxMana() {  //레벨업할때 스텟 늘렸을때는 빼는게 나을 듯
        mana = maxMana
    }

    companion object {
        fun startManaRefilling() {
            HeartbeatScope().launch {
                val suspension = Suspension()
                while (true) {
                    suspension.delay(50 * 10L)
                    for (user in User.playerList) {
                        if (user.mana.mana < user.mana.maxMana) user.mana.addMana(1)
                    }
                }
            }
        }
    }
}