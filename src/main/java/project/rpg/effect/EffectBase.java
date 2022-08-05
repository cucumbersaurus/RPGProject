package project.rpg.effect;

import org.bukkit.entity.LivingEntity;
import project.rpg.Rpg;

public abstract class EffectBase {

    protected LivingEntity _entity;
    protected int _second;
    protected int _amplifier;

    public abstract void effect();

    protected EffectBase(LivingEntity entity, int second,int amplifier) {
        this._entity = entity;
        this._second = second;
        this._amplifier = amplifier;
        this.effect();
    }

    protected EffectBase(LivingEntity entity, long tick) {
        this(entity,(int) tick/20);
    }

    protected EffectBase(LivingEntity entity, int second) {
        this(entity,second, 1);
    }
}
