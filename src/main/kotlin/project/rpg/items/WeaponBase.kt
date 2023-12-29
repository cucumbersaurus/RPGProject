package project.rpg.items

import org.bukkit.entity.Player

abstract class WeaponBase : ItemBase() {
    protected var _damage: Int = 0
    protected var _attackSpeed: Double = 0.0
    protected var grade: ItemGrade? = null

    protected var description: String? = null

    //TODO : 장착 시 스텟 증가
    abstract //조건 없으면 구현 할 때 return true
    val isLiftAble: Boolean

    //TODO : 강화
    fun onHit(player: Player?) {
    } //모든 무기가 다 있는 게 아님
    //나중에 특정 무기가 우클릭시 발생하는게 있으면 걔 혼자 implement Operable
}
