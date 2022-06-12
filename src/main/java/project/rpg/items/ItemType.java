package project.rpg.items;

public enum ItemType {

    NULL(0),
    WAND(1),
    MANA_REFILLING_POTION(2);

    private final int _value;
    ItemType(int value) {
        _value = value;
    }

    public int getValue() {
        return _value;
    }

}
