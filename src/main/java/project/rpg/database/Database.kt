package project.rpg.database

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.sql.Connection
import java.sql.DriverManager

object Database {

    private lateinit var connection:Connection
    private const val prefix = "rpg_"

    private fun getConnection(/*force: Boolean, startup: Boolean, onlyCheckTransacting: Boolean, waitTime: Int*/): Connection {
        try {
            val database = "jdbc:sqlite:" + "plugins/RPG/" + "database.db"
            connection = DriverManager.getConnection(database)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Bukkit.getLogger().info("db loaded")
        return connection
    }

    fun createTables(){
        try {
            getConnection(/*true, true, true, 0*/).use { connection ->
                val statement = connection.createStatement()

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + prefix + "player_data("+
                        "id                         INTEGER     PRIMARY KEY AUTOINCREMENT,"+
                        "uuid                      TEXT         NOT NULL, "+
                        "status_db_key        INTEGER     NOT NULL"+
                        ")"
                )
                statement.close()

            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun insert(player: Player){
        connection = getConnection()
        val statement = connection.createStatement()
        statement.executeUpdate("INSERT INTO " + prefix + "player_data ("+
                "uuid, "+
                "status_db_key"+
                ") VALUES ("+
                "\"${player.uniqueId}\", "+
                "0"+
                ")"
        )
        statement.close()
    }

    fun select(){
        connection = getConnection()
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT name FROM " + "rpg_" + "player_data")
        /*
        try {
            // PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(SQL)

            // 조회 데이터 조건 매핑
            pstmt.setObject(1, dataMap.get("BLOG_ID"))
            pstmt.setObject(2, dataMap.get("CATE_ID"))

            // 데이터 조회
            val rs: ResultSet = pstmt.executeQuery()

            // 조회된 데이터의 컬럼명 저장
            meta = pstmt.getMetaData()
            for (i in 1..meta.getColumnCount()) {
                columnNames.add(meta.getColumnName(i))
            }

            // ResultSet -> List<Map> 객체
            var resultMap: MutableMap<String?, Any?>? = null
            while (rs.next()) {
                resultMap = HashMap()
                for (column in columnNames) {
                    resultMap[column] = rs.getObject(column)
                }
                if (resultMap != null) {
                    selected.add(resultMap)
                }
            }
        } catch (e: SQLException) {
            // 오류처리
            println(e.message)
        } finally {
            try {
                // PreparedStatement 종료
                if (pstmt != null) {
                    pstmt.close()
                }

                // Database 연결 종료
                closeConnection()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }*/
    }

}
