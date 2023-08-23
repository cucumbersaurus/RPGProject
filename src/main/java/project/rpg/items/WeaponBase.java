package project.rpg.items;

import org.bukkit.entity.Player;

public abstract class WeaponBase extends ItemBase {

    protected int _damage;
    protected double _attackSpeed;
    protected ItemGrade grade;

    protected String description;

    //TODO : 장착 시 스텟 증가

    public abstract boolean isLiftAble(); //조건 없으면 구현 할 때 return true
    //TODO : 강화

    public void onHit(Player player) {
    } //모든 무기가 다 있는 게 아님
    //나중에 특정 무기가 우클릭시 발생하는게 있으면 걔 혼자 implement Operable

}
