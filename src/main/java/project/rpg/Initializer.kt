package project.rpg

import project.rpg.database.JobData
import project.rpg.database.LevelData
import project.rpg.database.StatusData
import project.rpg.database.UserData
import project.rpg.items.ItemDictionary
import project.rpg.items.ItemManager
import project.rpg.skill.SkillDic

object Initializer {

    fun initializeAll() {
        ItemManager.makeItems()
        ItemDictionary.initialize()
        SkillDic.addAll()
        UserData.initialize()
        StatusData.initialize()
        LevelData.initialize()
        JobData.initialize()
    }

}