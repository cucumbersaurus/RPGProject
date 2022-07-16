package project.rpg.player.job;

import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import project.rpg.skill.base.SkillBase;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class JobBase {

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

}
