package project.rpg.enums;

public enum ItemType {

    NULL(0),
    WAND(1);

    private final int _value;
    private ItemType(int value) {
        this._value = value;
    }

    public int getValue() {
        return _value;
    }

}
