package project.rpg.effect;

import org.bukkit.entity.LivingEntity;
import project.rpg.Rpg;

public abstract class EffectBase {

    protected LivingEntity _entity;
    protected int _second;
    protected int _amplifier;
    protected Rpg _plugin;

    public abstract void effect();

    public EffectBase(LivingEntity entity, int second, Rpg plugin, int _amplifier) {
        this._entity = entity;
        this._second = second;
        this._amplifier = _amplifier;
        this._plugin = plugin;
        this.effect();
    }

    public EffectBase(LivingEntity entity, int second, Rpg plugin) {
        this(entity, second, plugin, 1);
    }

    public EffectBase(LivingEntity entity, int second, int amplifier) {
        this(entity, second, null, amplifier);
    }

    public EffectBase(LivingEntity entity, long tick) {
        this(entity,(int) tick/20);
    }

    public EffectBase(LivingEntity entity, int second) {
        this(entity,second,null, 1);
    }
}
