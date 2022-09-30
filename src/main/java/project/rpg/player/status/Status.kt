package project.rpg.player.status

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName
import project.rpg.player.status.objects.*
import java.util.*

@Serializable
data class Status(//실제로는 10으로 할 예정, 지금은 테스트 용으로 많이 둠
    private val player: Player,
    private val agility: Agility = Agility(),
    private val defense: Defense = Defense(),
    private val handicraft: Handicraft = Handicraft(),
    private val health: Health = Health(),
    private val intelligence: Intelligence = Intelligence(),
    private val luck: Luck = Luck(),
    private val speed: Speed = Speed(),
    private val strength: Strength = Strength(),
    ) {

    private val statusMap: MutableMap<StatusName, StatusBase> = EnumMap(StatusName::class.java)


    var additionalStatusPoint = 0
        private set

    init {
        additionalStatusPoint = 1000
        saveMap()
    }

    private fun saveMap() {  //전체 저장
        statusMap[StatusName.AGILITY] = agility
        statusMap[StatusName.DEFENSE] = defense
        statusMap[StatusName.HANDICRAFT] = handicraft
        statusMap[StatusName.HEALTH] = health
        statusMap[StatusName.INTELLIGENCE] = intelligence
        statusMap[StatusName.LUCK] = luck
        statusMap[StatusName.SPEED] = speed
        statusMap[StatusName.STRENGTH] = strength
    }

    fun reloadMap() {  //적용 Attribute 매니져 없애
        for (status in statusMap.values) {
            status.effect(this.player)
        }
    }

    fun addStatus(name: StatusName, amount: Int): Boolean {  //스텟 늘리기
        val status = statusMap[name]
        return status?.addValue(amount, this, this.player) ?: throw StringIndexOutOfBoundsException("아 제대로 하세요 스텟 이름도 모르나")
    }

    fun getStatusValues(name: StatusName): Int {
        val status = statusMap[name]
        return status?.value ?: throw StringIndexOutOfBoundsException("아 제대로 하세요 스텟 이름도 모르나")
    }

    fun minAdditionalStatusPoint(amount: Int) {  //잔여 스텟 쓰기
        additionalStatusPoint -= amount
    }

    fun addAdditionalStatusPoint(amount: Int) {  //늘리기
        additionalStatusPoint += amount
    }

    init{
        saveMap()
    }
}