package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.player.User;

public class LevelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 2) {
                switch (args[0]) {
                    case "add":
                        User.getPlayer(player).getLevel().addExp(Long.parseLong(args[1]));
                        break;
                    case "remove":
                        User.getPlayer(player).getLevel().addExp(-Long.parseLong(args[1]));
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }
}
