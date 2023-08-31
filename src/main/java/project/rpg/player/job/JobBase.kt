package project.rpg.player.job

import org.bukkit.entity.Player
import org.bukkit.entity.Pose
import project.rpg.skills.base.SkillBase
import java.util.*

abstract class JobBase protected constructor(
    val player: Player,
    val jobType: JobType,
    var name: String = jobType.jobName,
    var description: String = jobType.description,
    var id: Int = jobType.id,
    var nextJobs: MutableList<JobType> = ArrayList(),
    var jobSkills: Map<Pose, SkillBase> = EnumMap(Pose::class.java),
) {

    abstract fun reload()

    abstract fun setNextJobs()

    abstract fun setJobSkills()

    init {
        setNextJobs()
        setJobSkills()
        reload()
    }
}