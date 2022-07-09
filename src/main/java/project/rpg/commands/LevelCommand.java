package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static project.rpg.player.Human._playerMap;

public class LevelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 2) {
                switch (args[0]) {
                    case "add":
                        _playerMap.get(player.getUniqueId()).getLevel().addExp(Integer.parseInt(args[1]));
                        break;
                }
            }
        }
        return false;
    }
}
