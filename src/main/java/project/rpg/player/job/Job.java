package project.rpg.player.job;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.job.thing.Jobless;

import java.util.HashMap;
import java.util.Map;

public class Job implements ConfigurationSerializable {

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

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());
        map.put("job", _job.serialize());
        return map;
    }
}
