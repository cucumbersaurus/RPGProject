package project.rpg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import project.rpg.player.info.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendCommand implements CommandExecutor, TabCompleter {
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
                    case "list":
                        Friend.printFriend(player);
                        break;
                    default:
                        //sender.sendMessage(command.getUsage());
                }
            } else if(args.length == 1 && args[0].equals("list")) {
                sender.sendMessage(Friend.getFriends().toString());
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<>();

        if(args.length == 1){
            result.add("add");
            result.add("accept");
            result.add("list");
        }

        if(args.length == 2 && args[0].equals("add")){
            for(Player player : Bukkit.getOnlinePlayers()){
                result.add(player.getName());
            }
        }

        return result;
    }
}
