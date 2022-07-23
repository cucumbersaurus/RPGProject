package project.rpg.player.status.base;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.status.Status;

import java.util.HashMap;
import java.util.Map;

public abstract class StatusBase implements ConfigurationSerializable {  //스텟 베이스

    protected final String _name;  //스텟 이름
    protected int _value;  //스텟 얼마나 있는지

    public abstract void effect(Player player);  //스텟 효과

    public boolean addValue(int amount, Status status, Player player){  //스텟 더하기
        if(status.getAdditionalStatusPoint() >= amount) {
            this._value += amount;
            status.minAdditionalStatusPoint(amount);
            return true;
        }
        return false;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", _name);
        map.put("value", _value);
        return map;
    }

    public String getName() {
        return _name;
    }

    public int getValue() {
        return _value;
    }

    public void setValue(int value) {
        _value = value;
    }

    protected StatusBase(StatusName status) {
        this._value = 10;
        this._name = status.getStatus();
    }

}
