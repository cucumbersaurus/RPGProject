package project.rpg.skill.magic

import project.rpg.skill.base.ActiveSkillBase

abstract class MagicSkillBase : ActiveSkillBase() {
    protected var circle: Short = 0
    var needMana = 0
        protected set
}