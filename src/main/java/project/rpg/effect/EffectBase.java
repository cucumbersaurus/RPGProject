package project.rpg.effect;

import org.bukkit.entity.LivingEntity;

public abstract class EffectBase {

    protected LivingEntity _entity;
    protected int _second;
    protected int _amplifier;
    protected long _tick;

    public abstract void effect();

    protected EffectBase(LivingEntity entity, int second) {
        this(entity,second*20L, 1);
    }

    protected EffectBase(LivingEntity entity, long tick) {
        this(entity,tick,1);
    }

    protected EffectBase(LivingEntity entity, int second,int amplifier) {
        this(entity, second* 20L, amplifier);
    }

    /**
     *
     * @param entity 효과를 줄 엔티티
     * @param tick 효과 지속 시간 틱 단위
     * @param amplifier 효과 정도
     *
     */
    protected EffectBase(LivingEntity entity, long tick, int amplifier) {
        this._entity = entity;
        this._tick = tick;
        this._amplifier = amplifier;
        this.effect();
    }
}
