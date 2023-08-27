package project.rpg

import project.rpg.database.tables.JobData
import project.rpg.database.tables.LevelData
import project.rpg.database.tables.StatusData
import project.rpg.database.tables.UserData
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