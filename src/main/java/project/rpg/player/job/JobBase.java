package project.rpg.player.job;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.jetbrains.annotations.NotNull;
import project.rpg.skill.base.SkillBase;

import java.util.*;

public abstract class JobBase implements ConfigurationSerializable {

    protected String _name;
    protected String _description;
    protected int _id;

    private final JobType _jobType;
    protected List<JobType> _nextJobs = new ArrayList<>();
    protected Map<Pose, SkillBase> _jobSkills = new EnumMap<>(Pose.class);

    protected final Player _player;

    public abstract void reload();
    public abstract void setNextJobs();
    public abstract void setJobSkills();

    public Player getPlayer() {
        return _player;
    }

    public String getName() {
        return _name;
    }

    public JobType getJobType(){
        return _jobType;
    }

    public String getDescription() {
        return _description;
    }

    public int getId(){
        return _id;
    }

    public List<JobType> getNextJob() {
        return _nextJobs;
    }

    public Map<Pose, SkillBase> getJobSkills() {
        return _jobSkills;
    }

    public void setNextJobs(List<JobType> nextJobs) {
        _nextJobs = nextJobs;
    }

    protected JobBase(JobType job, Player player) {
        _jobType = job;
        _name = job.getName();
        _description = job.getDescription();
        _id = job.getId();
        _player = player;

        setNextJobs();
        setJobSkills();
        reload();
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", _player.getName());
        map.put("name", _name);
        map.put("description", _description);
        map.put("id", _id);

        Map<String, Object> nextJobsMap = new HashMap<>();
        for(JobType i:_nextJobs){
            nextJobsMap.put("job", i.serialize());
        }
        map.put("nextJobs", nextJobsMap);

        /*
        Map<String, Object>jobSkillsMap = new HashMap<>();
        for(Map.Entry<Pose, SkillBase>entry : _jobSkills.entrySet()){
            jobSkillsMap.put(entry.getKey().name(), entry.getValue().serialize());
        }
        map.put("jobSkills", _jobSkills);
        */
        //?????? ?????? ?????? :  Pose ??? ???????????? ????????? ??????, skill ??? ?????? user ??? ????????? ???????????? ??????
        return map;
    }
    //Todo: ?????? ???????????? ??????
    public static JobBase deserialize(@NotNull Map<String, String> map){
        //????????? ?????? ??????
        JobBase jobBase = JobType.getJob(Integer.parseInt(map.get("id")), Bukkit.getPlayer(map.get("player")));

        //nextJobs deserialize
        List<JobType> nextJobs = new ArrayList<>();
        for(int i=0;i<110;i++){
            JobBase job = JobType.getJob(i, jobBase.getPlayer());
            if(job!=null&&Integer.parseInt(map.get("id"))==i){
                nextJobs.add(job.getJobType());
            }
        }
        jobBase.setNextJobs(nextJobs);

        return jobBase;
    }

}
