package project.rpg.player.job.thing;

import org.bukkit.entity.Player;
import project.rpg.player.job.JobBase;
import project.rpg.player.job.JobType;

public class Warrior extends JobBase {

    @Override
    public void reload() {
        //추가 예정
    }

    @Override
    public void setNextJobs() {
        //추가 예정
    }

    @Override
    public void setJobSkills() {
        //추가 예정
    }

    public Warrior(Player player) {
        super(JobType.WARRIOR, player);
    }

}
