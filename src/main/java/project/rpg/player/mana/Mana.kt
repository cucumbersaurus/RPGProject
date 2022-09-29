package project.rpg.player.mana

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.monun.heartbeat.coroutines.Suspension
import kotlinx.coroutines.launch
import org.bukkit.configuration.serialization.ConfigurationSerializable
import project.rpg.player.User
import project.rpg.player.status.Status
import project.rpg.player.status.base.StatusName

class Mana(status: Status) : ConfigurationSerializable {

    //마나
    var mana : Int//현재 마나
    var maxMana : Int //최대 마나

    init {
        val mana = status.getStatusValues(StatusName.INTELLIGENCE)
        this.mana = mana * 10
        maxMana = mana * 10
    }

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

    override fun serialize(): Map<String, Any> {
        val map: MutableMap<String, Any> = HashMap()
        map["mana"] = mana
        map["maxMana"] = maxMana
        return map
    }

    companion object {
        fun startManaRefilling() {  //어짜피 인스턴스 변수 보다 이게 나을 듯 //이제 필요 없음 ㅅㄱ
            HeartbeatScope().launch{
                val suspension = Suspension()
                while(true) {
                    suspension.delay(50*10L)
                    for (user in User.playerList) {
                        if (user.mana.mana < user.mana.maxMana) user.mana.addMana(1)
                    }
                }
            }
        }

        @JvmStatic
        fun deserialize(map: Map<String?, String>, status: Status): Mana {
            val mana = Mana(status)
            mana.maxMana = map["maxMana"]!!.toInt()
            mana.mana = map["mana"]!!.toInt()
            return mana
        }
    }
}