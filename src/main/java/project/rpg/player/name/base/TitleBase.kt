package project.rpg.player.name.base

import org.bukkit.entity.Player

abstract class TitleBase protected constructor(//칭호 베이스
    protected val player: Player, //onEnable 이나 disable 에서 쓸때
    val name: String, //칭호 이름

    protected val description: String? = null, //칭호 설명
    protected val acquisitionConditions: String? = null//획득 조건
)  {

    abstract fun onEnable() //착용시 효과    -> 교감 신경

    abstract fun onDisable() //착용 해제시   -> 부교감 신경 //???? //ㅋㅋㅋㅋ

    abstract fun eared() //획득시 발동
}