package project.rpg

import project.rpg.items.ItemDictionary
import project.rpg.items.ItemManager
import project.rpg.skill.SkillDic

object Initializer {

    fun initializeAll(){
        ItemManager.makeItems()
        ItemDictionary.initialize()
        SkillDic.addAll()
    }

}