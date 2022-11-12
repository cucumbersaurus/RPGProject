package project.rpg.database

import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import project.rpg.extensions.job
import project.rpg.player.job.JobType

object JobData:Table() {

    val id = integer("id").autoIncrement().uniqueIndex()

    val jobType = text("jobType")

    override val primaryKey by lazy {
        super.primaryKey ?: PrimaryKey(id)
    }

    fun initialize(){
        Database.tableList.add(this)
    }

    fun insertData(player: Player):Int{
        return transaction {
            insert{
                it[jobType] = player.job.job.jobType.name
            }get JobData.id
        }
    }

    fun updateData(player: Player, id:Int){
        transaction{
            update({JobData.id eq id}){
                it[jobType] = player.job.job.jobType.name
            }
        }
    }

    fun read(player:Player, id:Int){
        val jobData = transaction {
            JobData.select { JobData.id eq id }.toList()[0]
        }

        val jobType = JobType.valueOf(jobData[jobType])
        player.job.job = JobType.getJob(jobType.id, player)!!
    }
}