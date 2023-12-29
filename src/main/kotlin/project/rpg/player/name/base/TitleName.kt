package project.rpg.player.name.base

import org.bukkit.entity.Player
import project.rpg.player.name.title.MvpPP

enum class TitleName(
    val title: String, //이름
    val description: String?, //설명
    val acquisitionConditions: String?//획득 조건
) {
    //칭호 관련
    NEWBIE("기여운 뉴비에여"),
    MVPPP("[MVP++]", "어..? 어디서 많이 봤는데..", "“2스테이지 이스터 에그” 보상"),
    HOGU("HOGU", "닌 호구여~ 보상도 없는걸", "인게임 5만원 이상의 무기를 무료로 판매하기");

    constructor(v: String) : this(v, null, null)

    companion object {
        fun getTitle(player: Player, name: String): TitleBase? {
            if (name == MVPPP.title) {
                return MvpPP(player)
            }
            return null
        }
    }
}
