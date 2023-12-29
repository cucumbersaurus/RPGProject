package project.rpg.player.job.thing

import org.bukkit.entity.Player
import project.rpg.player.job.JobBase
import project.rpg.player.job.JobType

class Wizard(player: Player) : JobBase(player, JobType.WIZARD) {
    override fun reload() {}
    override fun setNextJobs() {}
    override fun setJobSkills() {}
}