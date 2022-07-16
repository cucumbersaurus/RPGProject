package project.rpg.player.job;

public enum Jobs {

    JOBLESS("백수","무직",0),
    WARRIOR("전사","체력 +150/ 모든 물리무기 착용 가능",1),
    WIZARD("마법사","체력 중/방어 중하/ 마법 이용/완드, 지팡이, 오브, 책) (마나 총량 : 200/초당 재생 마나 2",2);

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
