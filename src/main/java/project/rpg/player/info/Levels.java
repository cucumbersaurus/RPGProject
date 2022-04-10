package project.rpg.player.info;

public class Levels {

    private int needForNextLev_;
    private int currentLevel_;
    private int exp_;

    public int getNeedForNextLev() {
        return needForNextLev_;
    }

    public void setNeedForNextLev(int needForNextLev) {
        this.needForNextLev_ = needForNextLev;
    }

    public int getExp() {
        return exp_;
    }

    public void setExp(int exp) {
        this.exp_ = exp;
    }

    public int getCurrentLevel() {
        return currentLevel_;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel_ = currentLevel;
    }

    public void addCurrentLevel(int level) {
        this.currentLevel_ += level;
    }

    public Levels(int level, int exp) {
        this.currentLevel_ = level;
        this.exp_ = exp;
        this.needForNextLev_ = level*10;
    }

    public void levelUp() {
        this.currentLevel_ += 1;
        this.exp_ = this.exp_ - this.needForNextLev_;
        this.needForNextLev_ = this.currentLevel_ *10;
    }

}
