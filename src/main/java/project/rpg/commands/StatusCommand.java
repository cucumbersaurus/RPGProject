package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.AttributeManager;

import static project.rpg.manager.ArrayManager.playerData;
import static project.rpg.manager.FileManager.jsonFile;

public class StatusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String playername = player.getPlayer().getName();

            if ("add".equals(args[0])) {
                int num = Integer.valueOf(args[2]);
                switch (args[1]) {
                    case "strength":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addStrength(num);
                        break;
                    case "agility":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addAgility(num);
                        break;
                    case "speed":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addSpeed(num);
                        break;
                    case "health":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addHealth(num);
                        break;
                    case "defense":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addDefense(num);
                        break;
                    case "luck":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addLuck(num);
                        break;
                    case "handicraft":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addHandicraft(num);
                        break;
                    case "attractive":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData.get(playername).addAttractive(num);
                        break;
                    default:
                        sender.sendMessage("오타난 커멘드");
                        break;
                }
                jsonFile.put(playername, playerData.get(playername).getMap());
                AttributeManager.setAttributes(player, playerData.get(playername));
            }
            return true;
        }
        return false;
    }
}
