package project.rpg.player.job;

public enum Jobs {

    JOBLESS("백수","무직",0),

    //전사가 1    전사("전사","체력 +150/ 모든 물리무기 착용 가능",1),
    SWORDMAN("검사","체력 +250, 공격력 +10/검",2),
    MAGIC_SWORDMAN("마검사","체력 +250, 마나 +100/스킬 : 최대 2서클 마법 1개 착용 가능",3),
    MAGE_KNIGHT("메이지 나이트","체력 +400, 마나 +300, 마나 재생력 +1/검,/스킬 : 최대 4서클짜리 마법 2개 착용 가능",4),
    KNGHT("기사","체력 +400, 방어력 +10/검/",5),
    HOLY_KNGHT("성기사","체력 +650, 방어력 +30/검, 방패",6),
    KNGHT_MASTER("기사단장","",7),
    SWORDSMAN("검객","체력 +300, 공격력 +15, 방어력 +5/검",8),
    SWORDMASTER("검성","체력 +450, 공격력 +20, 공속 50%",8),
    FIGHTER("파이터","체력 +400, 공격력 +10",10),
    WARRIOR("워리어","체력 +300, 방어력 +5/둔기, 검, 창/스킬 : 이용 후 몹 타격 시 몹에게 스턴 1초 부여",11),  //이름 바꿀 예정
    BERSERKER("광전사","체력 +500/도끼,검",12),
    TANKER("탱커","체력 +650, 방어력 +10/방패, 둔기, 창",13),
    SLAYER("학살자","체력 +750, 공격력 +20/둔기, 검",14),
    SPEAR_FIGHTER("창투사","체력 +500, 방어력 +10/창/주변 반경 3블럭 이내의 적에게 넉백과 출혈 4초",15),
    SPEAR_THROWER("투창사","",16),
    LANCER("창기병","",17),


    WIZARD("마법사","(체력 중/방어 중하/ 마법 이용/완드, 지팡이, 오브, 책) (마나 총량 : 200/초당 재생 마나 2)",100);

    private final String _name;
    private final String _description;
    private final int _id;

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    Jobs(String name, String description, int id){
        this._name = name;
        this._description = description;
        this._id = id;
    }

}
