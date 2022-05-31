package project.rpg.player.info;

public class Levels {

    private int _needForNextLev;
    private int _currentLevel;
    private int _exp;

    public int getNeedForNextLev() {
        return _needForNextLev;
    }

    public void setNeedForNextLev(int needForNextLev) {
        this._needForNextLev = needForNextLev;
    }

    public int getExp() {
        return _exp;
    }

    public void setExp(int exp) {
        this._exp = exp;
    }

    public int getCurrentLevel() {
        return _currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this._currentLevel = currentLevel;
    }

    public void addCurrentLevel(int level) {
        this._currentLevel += level;
    }

    public Levels(int level, int exp) {
        this._currentLevel = level;
        this._exp = exp;
        this._needForNextLev = level*10;
    }

    public void levelUp() {
        this._currentLevel += 1;
        this._exp = this._exp - this._needForNextLev;
        this._needForNextLev = this._currentLevel *10;
    }

}
