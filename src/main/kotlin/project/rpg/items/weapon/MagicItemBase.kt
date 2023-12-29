package project.rpg.items.weapon

import project.rpg.items.ItemBase
import project.rpg.skills.base.Operable
import project.rpg.skills.base.SkillBase

abstract class MagicItemBase : ItemBase(), Operable {
    protected lateinit var skill: SkillBase
}
