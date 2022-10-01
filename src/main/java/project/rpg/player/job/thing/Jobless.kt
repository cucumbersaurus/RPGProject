package project.rpg.player.job.thing

import org.bukkit.entity.Player
import project.rpg.player.job.JobBase
import project.rpg.player.job.JobType

class Jobless(player: Player) : JobBase(player, JobType.JOBLESS) {
    override fun reload() {
        //응 없어
    }

    override fun setNextJobs() {
        nextJobs.add(JobType.WARRIOR)
        nextJobs.add(JobType.WIZARD)
    }

    override fun setJobSkills() {
        //응 직업 스킬 없어
    }
}