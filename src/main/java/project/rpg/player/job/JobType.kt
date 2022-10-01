package project.rpg.player.job

import org.bukkit.entity.Player
import project.rpg.player.job.thing.*

enum class JobType(val jobName: String, val description: String, val id: Int){
    JOBLESS("백수", "무직", 0), ADVENTURER("전사", "체력 +150| 모든 물리무기 착용 가능", 1), SWORDSMAN(
        "검사",
        "체력 +250, 공격력 +10|검",
        2
    ),
    MAGIC_SWORDSMAN("마검사", "체력 +250, 마나 +100|스킬 : 최대 2서클 마법 1개 착용 가능", 3), MAGE_KNIGHT(
        "메이지 나이트",
        "체력 +400, 마나 +300, 마나 재생력 +1|검,|스킬 : 최대 4서클짜리 마법 2개 착용 가능",
        4
    ),
    KNIGHT("기사", "체력 +400, 방어력 +10|검|", 5), HOLY_KNIGHT("성기사", "체력 +650, 방어력 +30|검, 방패", 6), KNIGHT_MASTER(
        "기사단장",
        "",
        7
    ),
    SWORD_EXPERT("검객", "체력 +300, 공격력 +15, 방어력 +5|검", 8), SWORD_MASTER(
        "검성",
        "체력 +450, 공격력 +20, 공속 50%",
        9
    ),
    WARRIOR("워리어", "체력 +300, 방어력 +5|둔기, 검, 창|스킬 : 이용 후 몹 타격 시 몹에게 스턴 1초 부여", 10),  //이름 바꿀 예정,
    BERSERK("광전사", "체력 +500|도끼,검", 11), TANKER("탱커", "체력 +650, 방어력 +10|방패, 둔기, 창", 12), SLAYER(
        "학살자",
        "체력 +750, 공격력 +20|둔기, 검",
        13
    ),
    SPEAR_FIGHTER("창투사", "체력 +500, 방어력 +10|창|주변 반경 3블럭 이내의 적에게 넉백과 출혈 4초", 14), SPEAR_THROWER(
        "투창사",
        "",
        15
    ),
    LANCER("창기병", "", 16), FIGHTER("파이터", "체력 +400, 공격력 +10", 17), COMBATANT(
        "격투가",
        "체력 +600|건틀렛|스킬 : 몹을 타격 시 몹에게 스턴 0.2초 부여",
        18
    ),
    THIEF("도적", "체력 +150, 공격력 +10|단검|스킬 : 전방 대쉬 후 이속 증가 버프 3초", 19), ASSASSIN(
        "암살자",
        "체력 +150, 공격력 +30, 신속 1|단검, 검| 스킬 : 전방에 단검을 투척해 적에게 독 효과를 5초 부여",
        20
    ),
    GHOST("고스트", "체력 +300, 공격력 200%, 신속 1|단검", 21), DANCER("무희", "", 22), PIRATE(
        "해적",
        "체력 +300|단검,검|스킬 : 마법 [아쿠아 스플래쉬] 이용 가능 및 공격력 20% 증가 버프",
        23
    ),
    CAPTAIN("선장", "체력 +450, 공격력 +20|검", 24), TRACKER(
        "추적자",
        "체력 +100|단검, 검|스킬 : 평타에 출혈 5초가 붙어있다. 만일 무기에 출혈이 붙어있다면 출혈효과 대신에 스턴 1초로 전환한다",
        25
    ),
    SHADOW("섀도우", "체력 +200, 공격력 +40", 26), STALKER("스토커", "", 27), WIZARD(
        "마법사",
        "(체력 중|방어 중하| 마법 이용|완드, 지팡이, 오브, 책) (마나 총량 : 200|초당 재생 마나 2)",
        100
    );

    companion object {
        fun getJob(id: Int, player: Player): JobBase? {
            for (job in values()) {
                if (job.id == id) {
                    //return
                }
            }
            return when (id) {
                0 -> Jobless(player)
                1 -> Adventurer(player)
                2 -> SwordsMan(player)
                3 -> MagicSwordsMan(player)
                4 ->                 //return JobType.MAGE_KNIGHT;
                    null

                5 ->                 //return JobType.KNIGHT;
                    null

                6 ->                 //return JobType.HOLY_KNIGHT;
                    null

                7 ->                 //return JobType.KNIGHT_MASTER;
                    null

                8 ->                 //return JobType.SWORD_EXPERT;
                    null

                9 ->                 //return JobType.SWORD_MASTER;
                    null

                10 -> Warrior(player)
                11 ->                 //return JobType.BERSERK;
                    null

                12 ->                 //return JobType.TANKER;
                    null

                13 ->                 //return JobType.SLAYER;
                    null

                14 ->                 //return JobType.SPEAR_FIGHTER;
                    null

                15 ->                 //return JobType.SPEAR_THROWER;
                    null

                16 ->                 //return JobType.LANCER;
                    null

                17 ->                 //return JobType.FIGHTER;
                    null

                18 ->                 //return JobType.COMBATANT;
                    null

                19 -> Thief(player)
                20 ->                 //return JobType.ASSASSIN;
                    null

                21 ->                 //return JobType.GHOST;
                    null

                22 ->                 //return JobType.DANCER;
                    null

                23 ->                 //return JobType.PIRATE;
                    null

                24 ->                 //return JobType.CAPTAIN;
                    null

                25 ->                 //return JobType.TRACKER;
                    null

                26 ->                 //return JobType.SHADOW;
                    null

                27 ->                 //return JobType.STALKER;
                    null

                100 -> Wizard(player)
                else -> null
            }
        }
    }
}