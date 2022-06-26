package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.info.Friend;

public class FriendCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length==2) {
                switch (args[0]) {
                    case "add":
                        Friend.addFriend(player,args[1]);
                        break;
                    case "accept":
                        Friend.acceptFriend(player,args[1]);
                        break;
                    default:
                        sender.sendMessage(command.getUsage());
                }
            } else if(args.length == 1 && args[0].equals("list")) {
                sender.sendMessage(Friend.get_friends().toString());
            }
        }
        return false;
    }
}
