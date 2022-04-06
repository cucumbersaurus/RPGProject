package project.rpg.player.info;

public class Levels {

    private int needForNextLev;
    private int currentLevel;
    private int exp;

    public int getNeedForNextLev() {
        return needForNextLev;
    }

    public void setNeedForNextLev(int needForNextLev) {
        this.needForNextLev = needForNextLev;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void addCurrentLevel(int level) {
        this.currentLevel += level;
    }

    public Levels(int level, int exp) {
        this.currentLevel = level;
        this.exp = exp;
        this.needForNextLev = level*10;
    }

    public void levelUp() {
        this.currentLevel += 1;
        this.exp = this.exp - this.needForNextLev;
        this.needForNextLev = this.currentLevel *10;
    }

}
