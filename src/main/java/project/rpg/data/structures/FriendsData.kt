package project.rpg.data.structures

import org.bukkit.entity.Player

class FriendsData(val _player:Player){

    private val linkedPlayers:ArrayList<Player> = ArrayList()

    fun getFriendsList():ArrayList<Player> = linkedPlayers

    private fun linkTo(player:Player){
        linkedPlayers.add(player);
    }

    private fun unlinkFrom(player:Player){
        linkedPlayers.remove(player);
    }

    init {
        playerMap[_player] = this;
    }

    companion object{
        @JvmStatic
        private val playerMap:HashMap<Player, FriendsData> = HashMap()

        @JvmStatic
        fun getPlayer(player:Player): FriendsData {
            return playerMap[player]!!
        }

        @JvmStatic
        fun linkPlayers(playerA:Player, playerB:Player){
            getPlayer(playerA).linkTo(playerB)
            getPlayer(playerB).linkTo(playerA)
        }

        @JvmStatic
        fun unlinkPlayers(playerA:Player, playerB:Player){
            getPlayer(playerA).unlinkFrom(playerB)
            getPlayer(playerB).unlinkFrom(playerA)
        }
    }
}
