package project.rpg.player.job.thing;

import org.bukkit.entity.Player;
import project.rpg.player.job.JobBase;
import project.rpg.player.job.JobType;

public class Wizard extends JobBase {

    @Override
    public void reload() {

    }

    @Override
    public void setNextJobs() {

    }

    @Override
    public void setJobSkills() {

    }

    public Wizard(Player player) {
        super(JobType.WIZARD, player);
    }

}
