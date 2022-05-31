package project.rpg.enums;

public enum ItemType {

    NULL(0),
    WAND(1);

    private final int _value;
    private ItemType(int vlaue) {
        this._value = vlaue;
    }

    public int getValue() {
        return _value;
    }

}
