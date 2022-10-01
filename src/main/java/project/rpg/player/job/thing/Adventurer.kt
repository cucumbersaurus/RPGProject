package project.rpg.player.job.thing;

import org.bukkit.entity.Player;
import project.rpg.player.job.JobBase;
import project.rpg.player.job.JobType;

public class Adventurer extends JobBase {

    @Override
    public void reload() {
        //추가 예정
    }

    @Override
    public void setNextJobs() {
        this._nextJobs.add(JobType.SWORDSMAN);
        this._nextJobs.add(JobType.WARRIOR);
        this._nextJobs.add(JobType.THIEF);
        //궁수
        //이류무사 dlc
    }

    @Override
    public void setJobSkills() {
        //추가 예정
    }

    public Adventurer(Player player) {
        super(JobType.ADVENTURER, player);
    }

}
