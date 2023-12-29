package project.rpg.skills.magic

import project.rpg.skills.base.ActiveSkillBase

abstract class MagicSkillBase : ActiveSkillBase() {
    protected var circle: Short = 0
    var needMana = 0
        protected set
}