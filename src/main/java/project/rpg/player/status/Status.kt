package project.rpg.player.status

import org.bukkit.entity.Player
import project.rpg.player.status.base.StatusBase
import project.rpg.player.status.base.StatusName
import project.rpg.player.status.objects.*
import java.util.*

class Status//실제로는 10으로 할 예정, 지금은 테스트 용으로 많이 둠
    (player: Player) {
    //스텟
    private val _player: Player = player
    private val _agility: Agility = Agility()
    private val _defense: Defense = Defense()
    private val _handicraft: Handicraft = Handicraft()
    private val _health: Health = Health()
    private val _intelligence: Intelligence = Intelligence()
    private val _luck: Luck = Luck()
    private val _speed: Speed = Speed()
    private val _strength: Strength = Strength()
    private val _status: MutableMap<StatusName, StatusBase> = EnumMap(
        StatusName::class.java
    )

    //가져오기
    var additionalStatusPoint = 0
        private set

    init {
        additionalStatusPoint = 1000
        saveMap()
    }

    private fun saveMap() {  //전체 저장
        _status[StatusName.AGILITY] = _agility
        _status[StatusName.DEFENSE] = _defense
        _status[StatusName.HANDICRAFT] = _handicraft
        _status[StatusName.HEALTH] = _health
        _status[StatusName.INTELLIGENCE] = _intelligence
        _status[StatusName.LUCK] = _luck
        _status[StatusName.SPEED] = _speed
        _status[StatusName.STRENGTH] = _strength
    }

    fun reloadMap() {  //적용 Attribute 매니져 없애
        for (status in _status.values) {
            status.effect(_player)
        }
    }

    fun addStatus(name: StatusName, amount: Int): Boolean {  //스텟 늘리기
        val status = _status[name]
        return status?.addValue(amount, this, _player) ?: throw StringIndexOutOfBoundsException("아 제대로 하세요 스텟 이름도 모르나")
    }

    fun getStatusValues(name: StatusName): Int {
        val status = _status[name]
        return status?.value ?: throw StringIndexOutOfBoundsException("아 제대로 하세요 스텟 이름도 모르나")
    }

    fun minAdditionalStatusPoint(amount: Int) {  //잔여 스텟 쓰기
        additionalStatusPoint -= amount
    }

    fun addAdditionalStatusPoint(amount: Int) {  //늘리기
        additionalStatusPoint += amount
    }
}