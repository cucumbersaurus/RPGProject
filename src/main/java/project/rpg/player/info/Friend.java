package project.rpg.player.info;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import project.rpg.material.ConnectList;

public class Friend {

    private static final ConnectList<Player> _friends = new ConnectList<>();

    public static ConnectList<Player> getFriends() {
        return _friends;
    }

    public static boolean addFriend(Player sender, Player invitee) {
        if (!_friends.contain(sender, invitee)) {
            if (Bukkit.getOnlinePlayers().contains(invitee)) {
                sender.sendMessage(ChatColor.BLUE + invitee.getName() + "에게 친구 요청을 보냈습니다");
                invitee.sendMessage(ChatColor.BLUE + sender.getName() + "(이)가 친구 요청을 보냈습니다");
            }
            else {
                sender.sendMessage(ChatColor.RED + invitee.getName() + "은(는) 없는 플레이어이거나 서버에 들어와 있지 않습니다");
            }
            return true;
        } else if (_friends.contain(invitee, sender)) {
            acceptFriend(sender, invitee);
            return true;
        }

        if (_friends.connected(sender, invitee)) {
            sender.sendMessage(ChatColor.RED + "이미 " + invitee.getName() + "와 친구입니다");
        }
        sender.sendMessage(ChatColor.RED + "전에 " + invitee.getName() + "에게 친구 요청을 보낸 적이 있습니다");
        return true;
    }

    public static boolean addFriend(Player sender, String inv) {
        Player invitee = Bukkit.getPlayer(inv);
        return addFriend(sender, invitee);
    }

    public static boolean acceptFriend(Player sender, Player invitee) {
        if (_friends.contain(sender, invitee)) {
            invitee.sendMessage(ChatColor.BLUE + sender.getName() + "에게 온 친구 요청을 받았습니다");
            sender.sendMessage(ChatColor.BLUE + invitee.getName() + "(이)가 친구 요청을 받았습니다");

            _friends.connect(invitee);
            return true;
        }

        invitee.sendMessage(ChatColor.RED + "전에 " + sender.getName() + "(이)가 친구 요청을 보낸 적이 없습니다");
        return false;
    }

    public static boolean acceptFriend(Player invitee, String send) {
        Player sender = Bukkit.getPlayer(send);
        return acceptFriend(sender, invitee);
    }
}
