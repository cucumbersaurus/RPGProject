package project.rpg.extensions

import project.rpg.player.status.Status
import project.rpg.player.status.base.StatusName

val Status.agility
    get() = this.getStatusValues(StatusName.AGILITY)

val Status.defense
    get() = this.getStatusValues(StatusName.DEFENSE)

val Status.handicraft
    get() = this.getStatusValues(StatusName.HANDICRAFT)

val Status.maxHealth
    get() = this.getStatusValues(StatusName.HEALTH)

val Status.intelligence
    get() = this.getStatusValues(StatusName.INTELLIGENCE)

val Status.luck
    get() = this.getStatusValues(StatusName.LUCK)

val Status.speed
    get() = this.getStatusValues(StatusName.SPEED)

val Status.strength
    get() = this.getStatusValues(StatusName.STRENGTH)

