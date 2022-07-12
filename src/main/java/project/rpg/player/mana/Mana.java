package project.rpg.player.mana;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;
import project.rpg.player.User;
import project.rpg.player.status.base.StatusName;

import static project.rpg.player.User._playerMap;

public class Mana {  //마나

    private int _mana;  //현재 마나
    private int _maxMana;  //최대 마나

    public static void startManaRefilling(Rpg plugin){  //어짜피 인스턴스 변수 보다 이게 나을 듯
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()->{
            for(User user : _playerMap.values()){
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
        this._maxMana = amount;
    }

    public int getMana() {
        return _mana;
    }

    public int getMaxMana() {
        return _maxMana;
    }

    public Mana(Player player) {
        int mana= User.getPlayer(player).getStatus().getStatus(StatusName.INTELLIGENCE);
        this._mana = mana;
        this._maxMana =mana;
    }
}
