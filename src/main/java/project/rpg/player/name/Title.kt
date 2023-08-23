package project.rpg.player.name

import org.bukkit.entity.Player
import project.rpg.player.name.base.TitleBase
import project.rpg.player.name.base.TitleName

data class Title(    //이름과 칭호
    private val player: Player,
    var name: String = TitleName.NEWBIE.name, //이름
    private var titles: MutableList<TitleBase> = ArrayList(), //칭호

    //현재 칭호 보여줄 때
    private var selectedTitle: TitleBase? = null //선택된 칭호
) {


    fun addTitle(name: String): Boolean {  //칭호 추가
        val title = TitleName.getTitle(player, name)
        if (title != null) {
            titles.add(title)
            if (titles.size == 1) {
                changeTitle(name)
            }
        }
        return false
    }

    fun addTitle(title: TitleName): Boolean {//칭호 추가
        titles.add(TitleName.getTitle(player, title.name))
        return false
    }

    fun changeTitle(name: String): Boolean {  //칭호 변경
        for (t in titles) {
            if (t.name == name) {
                if (selectedTitle != null) {
                    selectedTitle!!.onDisable()
                }
                selectedTitle = t
                t.onEnable()
                return true
            }
        }
        return false
    }

    fun setTitles(titles: MutableList<TitleBase>) {
        this.titles = titles
    }
}