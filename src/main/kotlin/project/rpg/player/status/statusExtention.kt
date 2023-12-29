package project.rpg.player.status

import project.rpg.player.status.base.StatusName

var Status.agility
    get() = this.getStatusValues(StatusName.AGILITY)
    set(amount) = this.setStatusValues(StatusName.AGILITY, amount)

var Status.defense
    get() = this.getStatusValues(StatusName.DEFENSE)
    set(amount) = this.setStatusValues(StatusName.DEFENSE, amount)

var Status.handicraft
    get() = this.getStatusValues(StatusName.HANDICRAFT)
    set(amount) = this.setStatusValues(StatusName.HANDICRAFT, amount)

var Status.maxHealth
    get() = this.getStatusValues(StatusName.HEALTH)
    set(amount) = this.setStatusValues(StatusName.HEALTH, amount)

var Status.intelligence
    get() = this.getStatusValues(StatusName.INTELLIGENCE)
    set(amount) = this.setStatusValues(StatusName.INTELLIGENCE, amount)

var Status.luck
    get() = this.getStatusValues(StatusName.LUCK)
    set(amount) = this.setStatusValues(StatusName.LUCK, amount)

var Status.speed
    get() = this.getStatusValues(StatusName.SPEED)
    set(amount) = this.setStatusValues(StatusName.SPEED, amount)

var Status.strength
    get() = this.getStatusValues(StatusName.STRENGTH)
    set(amount) = this.setStatusValues(StatusName.STRENGTH, amount)

fun Status.addAgility(amount: Int = 1) = this.addStatus(StatusName.AGILITY, amount)

fun Status.addDefense(amount: Int = 1) = this.addStatus(StatusName.DEFENSE, amount)

fun Status.addHandicraft(amount: Int = 1) = this.addStatus(StatusName.HANDICRAFT, amount)

fun Status.addMaxHealth(amount: Int = 1) = this.addStatus(StatusName.HEALTH, amount)

fun Status.addIntelligence(amount: Int = 1) = this.addStatus(StatusName.INTELLIGENCE, amount)

fun Status.addLuck(amount: Int = 1) = this.addStatus(StatusName.LUCK, amount)

fun Status.addSpeed(amount: Int = 1) = this.addStatus(StatusName.SPEED, amount)

fun Status.addStrength(amount: Int = 1) = this.addStatus(StatusName.STRENGTH, amount)
