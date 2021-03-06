package project.rpg.player.job;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.job.thing.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public enum JobType implements ConfigurationSerializable {

    JOBLESS("백수","무직",0),

    ADVENTURER("전사","체력 +150| 모든 물리무기 착용 가능",1),
    SWORDSMAN("검사","체력 +250, 공격력 +10|검",2),
    MAGIC_SWORDSMAN("마검사","체력 +250, 마나 +100|스킬 : 최대 2서클 마법 1개 착용 가능",3),
    MAGE_KNIGHT("메이지 나이트","체력 +400, 마나 +300, 마나 재생력 +1|검,|스킬 : 최대 4서클짜리 마법 2개 착용 가능",4),
    KNIGHT("기사","체력 +400, 방어력 +10|검|",5),
    HOLY_KNIGHT("성기사","체력 +650, 방어력 +30|검, 방패",6),
    KNIGHT_MASTER("기사단장","",7),
    SWORD_EXPERT("검객","체력 +300, 공격력 +15, 방어력 +5|검",8),
    SWORD_MASTER("검성","체력 +450, 공격력 +20, 공속 50%",9),
    WARRIOR("워리어","체력 +300, 방어력 +5|둔기, 검, 창|스킬 : 이용 후 몹 타격 시 몹에게 스턴 1초 부여",10),  //이름 바꿀 예정,
    BERSERK("광전사","체력 +500|도끼,검",11),
    TANKER("탱커","체력 +650, 방어력 +10|방패, 둔기, 창",12),
    SLAYER("학살자","체력 +750, 공격력 +20|둔기, 검",13),
    SPEAR_FIGHTER("창투사","체력 +500, 방어력 +10|창|주변 반경 3블럭 이내의 적에게 넉백과 출혈 4초",14),
    SPEAR_THROWER("투창사","",15),
    LANCER("창기병","",16),
    FIGHTER("파이터","체력 +400, 공격력 +10",17),
    COMBATANT("격투가","체력 +600|건틀렛|스킬 : 몹을 타격 시 몹에게 스턴 0.2초 부여",18),
    THIEF("도적","체력 +150, 공격력 +10|단검|스킬 : 전방 대쉬 후 이속 증가 버프 3초",19),
    ASSASSIN("암살자","체력 +150, 공격력 +30, 신속 1|단검, 검| 스킬 : 전방에 단검을 투척해 적에게 독 효과를 5초 부여",20),
    GHOST("고스트","체력 +300, 공격력 200%, 신속 1|단검",21),
    DANCER("무희","",22),
    PIRATE("해적","체력 +300|단검,검|스킬 : 마법 [아쿠아 스플래쉬] 이용 가능 및 공격력 20% 증가 버프",23),
    CAPTAIN("선장","체력 +450, 공격력 +20|검",24),
    TRACKER("추적자","체력 +100|단검, 검|스킬 : 평타에 출혈 5초가 붙어있다. 만일 무기에 출혈이 붙어있다면 출혈효과 대신에 스턴 1초로 전환한다",25),
    SHADOW("섀도우","체력 +200, 공격력 +40",26),
    STALKER("스토커","",27),

    WIZARD("마법사","(체력 중|방어 중하| 마법 이용|완드, 지팡이, 오브, 책) (마나 총량 : 200|초당 재생 마나 2)",100);

    private final String _name;
    private final String _description;
    private final int _id;

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public int getId() {
        return _id;
    }

    JobType(String name, String description, int id){
        this._name = name;
        this._description = description;
        this._id = id;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", _name);
        map.put("description", _description);
        map.put("id", _id);

        return map;
    }

    public static @Nullable JobBase getJob(int id, Player player){
        for (JobType job : JobType.values()) {
            if (job._id==id) {
                //return
            }
        }
        //return

        switch(id){
            case 0:
                return new Jobless(player);
            case 1:
                return new Adventurer(player);
            case 2:
                return new SwordsMan(player);
            case 3:
                return new MagicSwordsMan(player);
            case 4:
                //return JobType.MAGE_KNIGHT;
                return null;
            case 5:
                //return JobType.KNIGHT;
                return null;
            case 6:
                //return JobType.HOLY_KNIGHT;
                return null;
            case 7:
                //return JobType.KNIGHT_MASTER;
                return null;
            case 8:
                //return JobType.SWORD_EXPERT;
                return null;
            case 9:
                //return JobType.SWORD_MASTER;
                return null;
            case 10:
                return new Warrior(player);
            case 11:
                //return JobType.BERSERK;
                return null;
            case 12:
                //return JobType.TANKER;
                return null;
            case 13:
                //return JobType.SLAYER;
                return null;
            case 14:
                //return JobType.SPEAR_FIGHTER;
                return null;
            case 15:
                //return JobType.SPEAR_THROWER;
                return null;
            case 16:
                //return JobType.LANCER;
                return null;
            case 17:
                //return JobType.FIGHTER;
                return null;
            case 18:
                //return JobType.COMBATANT;
                return null;
            case 19:
                return new Thief(player);
            case 20:
                //return JobType.ASSASSIN;
                return null;
            case 21:
                //return JobType.GHOST;
                return null;
            case 22:
                //return JobType.DANCER;
                return null;
            case 23:
                //return JobType.PIRATE;
                return null;
            case 24:
                //return JobType.CAPTAIN;
                return null;
            case 25:
                //return JobType.TRACKER;
                return null;
            case 26:
                //return JobType.SHADOW;
                return null;
            case 27:
                //return JobType.STALKER;
                return null;
            case 100:
                return new Wizard(player);
            default:
                return null;
                //throw new IllegalStateException("Unexpected Job value: " + id);
        }
    }
}
