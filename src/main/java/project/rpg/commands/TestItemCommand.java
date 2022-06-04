package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.items.Wand;

public class TestItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player)sender;
            if(args.length > 0){
                switch (args[0]) {
                    case "wand":
                        player.getInventory().addItem(Wand.getItem().clone());
                        break;
                }
            }
        }
        return false;
    }
}
