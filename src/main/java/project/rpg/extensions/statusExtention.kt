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

fun Status.addAgility(amount: Int = 1) = this.addStatus(StatusName.AGILITY, amount)

fun Status.addDefense(amount: Int = 1) = this.addStatus(StatusName.DEFENSE, amount)

fun Status.addHandicraft(amount: Int = 1) = this.addStatus(StatusName.HANDICRAFT, amount)

fun Status.addMaxHealth(amount: Int = 1) = this.addStatus(StatusName.HEALTH, amount)

fun Status.addIntelligence(amount: Int = 1) = this.addStatus(StatusName.INTELLIGENCE, amount)

fun Status.addLuck(amount: Int = 1) = this.addStatus(StatusName.LUCK, amount)

fun Status.addSpeed(amount: Int = 1) = this.addStatus(StatusName.SPEED, amount)

fun Status.addStrength(amount: Int = 1) = this.addStatus(StatusName.STRENGTH, amount)
