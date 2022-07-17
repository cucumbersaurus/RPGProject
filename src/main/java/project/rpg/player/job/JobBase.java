package project.rpg.player.job;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.jetbrains.annotations.NotNull;
import project.rpg.skill.base.SkillBase;

import java.util.*;

public abstract class JobBase implements ConfigurationSerializable {

    protected String _name;
    protected String _description;
    protected List<Jobs> _nextJobs = new ArrayList<>();
    protected Map<Pose, SkillBase> _jobSkills = new EnumMap<>(Pose.class);

    protected final Player _player;

    public abstract void reload();
    public abstract void setNextJobs();
    public abstract void setJobSkills();

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public List<Jobs> getNextJob() {
        return _nextJobs;
    }

    public Map<Pose, SkillBase> getJobSkills() {
        return _jobSkills;
    }

    protected JobBase(String name, String description, Player player) {
        this._name = name;
        this._description = description;
        this._player = player;

        this.setNextJobs();
        this.setJobSkills();
        this.reload();
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());
        map.put("name", _name);
        map.put("description", _description);

        Map<String, Object> nextJobsMap = new HashMap<>();
        for(Jobs i:_nextJobs){
            nextJobsMap.put("name", i.serialize());
        }
        map.put("jobs", nextJobsMap);
        map.put("jobSkills", _jobSkills);

        return map;
    }

}
