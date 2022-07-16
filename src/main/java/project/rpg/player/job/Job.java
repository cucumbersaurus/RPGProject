package project.rpg.player.job;

import org.bukkit.entity.Player;
import project.rpg.player.job.thing.Jobless;

public class Job {

    private JobBase _job;
    private final Player _player;

    public void changeJob(JobBase job) {
        this._job = job;
    }

    public JobBase getJob() {
        return _job;
    }

    public Job(Player player) {
        this._player = player;
        this._job = new Jobless(player);
    }

}
