package project.rpg.player.mana;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import project.rpg.Rpg;
import project.rpg.player.User;
import project.rpg.player.status.Status;
import project.rpg.player.status.base.StatusName;

import java.util.HashMap;
import java.util.Map;

public class Mana implements ConfigurationSerializable {  //마나

    private int _mana;  //현재 마나
    private int _maxMana;  //최대 마나

    public static void startManaRefilling(Rpg plugin){  //어짜피 인스턴스 변수 보다 이게 나을 듯
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()->{
            for(User user : User.getPlayerList()){
                if(user.getMana()._mana < user.getMana()._maxMana)  user.getMana().addMana(1);
            }
        }, 10, 10);
    }

    public boolean useMana(int amount){  //마나 사용 마법이나 스킬에 넣기
        if(amount<= _mana){
            _mana -= amount;
            return true;
        }
        return false;
    }

    public boolean addMana(int amount){  //마나 충전할 때 쓰는거 포션이나 자연재생
        if(amount+ _mana <= _maxMana){
            _mana += amount;
            return true;
        }
        return false;
    }

    public void reloadMaxMana() {  //레벨업할때 스텟 늘렸을때는 빼는게 나을 듯
        _mana = _maxMana;
    }

    public void setMaxMana(int amount) {
        _maxMana = amount;
    }

    public void setMana(int amount) {
        _mana = amount;
    }

    public int getMana() {
        return _mana;
    }

    public int getMaxMana() {
        return _maxMana;
    }

    public Mana(Status status) {
        int mana= status.getStatusValues(StatusName.INTELLIGENCE);
        this._mana = mana*10;
        this._maxMana = mana*10;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("mana", _mana);
        map.put("maxMana", _maxMana);
        return map;
    }

    public static Mana deserialize(Map<String, String> map, Status status) {
        Mana mana = new Mana(status);
        mana.setMaxMana(Integer.parseInt(map.get("maxMana")));
        mana.setMana(Integer.parseInt(map.get("mana")));
        return mana;
    }
}
