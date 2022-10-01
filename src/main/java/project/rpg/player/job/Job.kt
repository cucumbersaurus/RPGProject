package project.rpg.player.job;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
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

    public void setJob(JobBase job) {
        _job = job;
    }

    public Job(Player player) {
        this._player = player;
        this._job = new Jobless(player);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());
        map.put("jobBase", _job.serialize());
        return map;
    }

    public static Job deserialize(@NotNull Map<String, String> map) {
        Gson gson = new Gson();
        Job job = new Job(Bukkit.getPlayer(map.get("player")));
        job.setJob(JobBase.deserialize(gson.fromJson( map.get("jobBase"), Map.class)));
        return job;
    }

}
