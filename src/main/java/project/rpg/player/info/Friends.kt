package project.rpg.player.info

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import project.rpg.extensions.color
import project.rpg.extensions.friends
import project.rpg.textComponets.color.DefaultTextColors
import project.rpg.textComponets.color.TextColors

data class Friends(
    val player: Player,
    val pendingList: ArrayList<Player> = ArrayList(),
    val friends: ArrayList<Player> = ArrayList()
) {

    //sender 보내는 사람
    //invitee 받는 사람

    /**
     * @param inviteeName 친구 요청을 받는 플레이어의 이름
     * @return 요청 성공 여부
     */
    fun requestFriend(inviteeName: String): Boolean {
        val invitee: Player? = Bukkit.getPlayer(inviteeName)

        if (invitee == null || !Bukkit.getOnlinePlayers().contains(invitee)) {//받는 사람이 없는 플레이어이거나 서버에 없는 경우
            player.sendMessage(noPlayerMessage(inviteeName))
            return true
        } else if (player == invitee) {//받는 플레이어와 보내는 플레이어가 같을때
            player.sendMessage(toMeMessage)
            return true
        } else {
            if (invitee.friends.hasPendingRequest(player)) {//이미 보낸 요청이 있을때
                player.sendMessage(
                    text("이미 보낸 친구 요청이 있습니다.")
                        .color(TextColors.TOMATO)
                )
                return false
            } else if (hasPendingRequest(invitee)) {//상대가 보낸 친구 요청이 있을때

                val hoverText = (text("클릭하여 친구 요청 받기"))
                val message = text().content("이미 $inviteeName(으)로부터 온 친구 요청이 있습니다.")
                    .color(DefaultTextColors.RED.color)
                    .appendNewline()
                    .append(
                        text("여기")
                            .color(DefaultTextColors.GREEN)
                            .hoverEvent(HoverEvent.showText(hoverText))
                            .clickEvent(ClickEvent.runCommand("/friend accept $inviteeName"))
                    )
                    .append(
                        text("를 클릭하여 친구 요청 받기")
                            .color(DefaultTextColors.WHITE)
                    )
                    .build()
                player.sendMessage(message)
                return true
            } else {//나머지 경우는 요청 보내기
                invitee.friends.addPendingRequest(player)

                val hoverText = text()
                hoverText.append(text("여기를 클릭하여 친구 요청 받기"))

                val message = text()
                message.append(
                    text("(이)가 친구 요청을 보냈습니다.")
                        .color(DefaultTextColors.BLUE.color)
                        .hoverEvent(HoverEvent.showText(hoverText.build()))
                        .clickEvent(ClickEvent.runCommand("/friend accept " + player.name))
                )

                invitee.sendMessage(text().build())
                return true
            }
        }
    }

    private fun noPlayerMessage(inviteeName: String) = text("${inviteeName}은(는) 없는 플레이어이거나 서버에 들어와 있지 않습니다.")
        .color(DefaultTextColors.RED)

    /**
     * @param requestSenderName player 에게 요청을 보냈던 플레이어의 이름
     * @return 요청 수락 성공 여부
     */
    fun acceptFriend(requestSenderName: String): Boolean {
        val requestSender = Bukkit.getPlayer(requestSenderName)

        if (requestSender == null) return false//null 처리 (보낸 사람은 무조건 존재(닉 바꾸면..) 아무튼 보낸 사람이 오프라인일때 테스트 필요함
        else if (hasPendingRequest(requestSender)) {//온 친구 요청이 있으면 받기
            removePendingRequest(requestSender)
            addFriendTo(requestSender)

            player.sendMessage(
                text(requestSender.name + "(으)로부터 온 친구 요청을 받았습니다.")
                    .color(DefaultTextColors.BLUE)
            )

            requestSender.sendMessage(
                text(player.name + "(이)가 친구 요청을 받았습니다")
                    .color(DefaultTextColors.BLUE)
            )
            return true
        } else {//없는 경우에는 다시 요청할건지 묻기
            val hoverText = text()
            hoverText.append(text("클릭하여 친구 요청 보내기"))

            val message = text()
            message.append(
                text("아직 $requestSenderName(으)로부터 온 친구 요청이 없습니다.")
                    .color(DefaultTextColors.BLUE)
            )
            message.append(
                text("여기를 클릭하여 친구 요청 보내기")
                    .color(DefaultTextColors.GREEN)
                    .hoverEvent(HoverEvent.showText(hoverText.build()))
                    .clickEvent(ClickEvent.runCommand("/friend add $requestSenderName"))
            )
            player.sendMessage(message.build())
            return false
        }
    }

    /**
     *친구 목록을 플레이어에게 전송
     */
    fun printFriendsList() {
        val message = text()
        if (friends.isNotEmpty()) {
            friends.forEach { message.append(text("${it.name} ")) }
        } else message.append(text("아직 친구가 없습니다. /friend 명령어를 통해 친구를 추가해 보세요."))
        player.sendMessage(message.build())
    }

    private fun addPendingRequest(player: Player) {
        pendingList.add(player)
    }

    private fun hasPendingRequest(player: Player): Boolean {
        return pendingList.contains(player)
    }

    private fun removePendingRequest(player: Player) {
        pendingList.remove(player)
    }

    private fun addFriendTo(sender: Player) {
        friends.add(sender)
        sender.friends.friends.add(this.player)
    }

    private fun unlinkPlayers(other: Player) {
        friends.remove(other)
        other.friends.friends.remove(this.player)
    }

    val toMeMessage = text("나 자신은 영원한 인생의 친구 입니다.").color(DefaultTextColors.BLUE)


}