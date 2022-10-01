package project.rpg.player.job.thing

import org.bukkit.entity.Player
import project.rpg.player.job.JobBase
import project.rpg.player.job.JobType

class Adventurer(player: Player) : JobBase(player, JobType.ADVENTURER ) {
    override fun reload() {
        //추가 예정
    }

    override fun setNextJobs() {
        nextJobs.add(JobType.SWORDSMAN)
        nextJobs.add(JobType.WARRIOR)
        nextJobs.add(JobType.THIEF)
        //궁수
        //이류무사 dlc
    }

    override fun setJobSkills() {
        //추가 예정
    }
}