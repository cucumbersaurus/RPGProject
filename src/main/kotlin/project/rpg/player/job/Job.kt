package project.rpg.player.job

import org.bukkit.entity.Player
import project.rpg.player.job.thing.Jobless

data class Job(
    private val player: Player,
    var job: JobBase = Jobless(player)
) {
    fun changeJob(job: JobBase) {
        this.job = job
    }
}