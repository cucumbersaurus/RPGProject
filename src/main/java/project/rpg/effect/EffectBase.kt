package project.rpg.effect

import org.bukkit.entity.LivingEntity

abstract class EffectBase protected constructor(
    protected var entity: LivingEntity,
    protected var tick: Long,
    protected var amplifier: Int = 1
) {
    protected var second = 0
    abstract fun effect(durationMillis: Long)

    protected constructor(entity: LivingEntity, second: Int) : this(entity, second * 20L, 1)
    protected constructor(entity: LivingEntity, second: Int, amplifier: Int) : this(entity, second * 20L, amplifier)

    /**
     * @param entity    효과를 줄 엔티티
     * @param tick      효과 지속 시간 틱 단위
     * @param amplifier 효과 정도
     */
    init {
        effect(tick * 50)
    }
}
