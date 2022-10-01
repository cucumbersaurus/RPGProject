package project.rpg.player.job.thing;

import org.bukkit.entity.Player;
import project.rpg.player.job.JobBase;
import project.rpg.player.job.JobType;

public class Jobless extends JobBase {

    @Override
    public void reload() {
        //응 없어
    }

    @Override
    public void setNextJobs() {
        this._nextJobs.add(JobType.WARRIOR);
        this._nextJobs.add(JobType.WIZARD);
    }

    @Override
    public void setJobSkills() {
        //응 직업 스킬 없어
    }

    public Jobless(Player player) {
        super(JobType.JOBLESS, player);
    }

}
