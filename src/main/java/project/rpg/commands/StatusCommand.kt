package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.AttributeManager;
import project.rpg.player.info.Status;

import java.util.UUID;

public class StatusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            if ("add".equals(args[0])) {
                int num = Integer.parseInt(args[2]);
                switch (args[1]) {
                    case "strength":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addStrength(num);
                        break;
                    case "agility":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addAgility(num);
                        break;
                    case "speed":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addSpeed(num);
                        break;
                    case "health":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addHealth(num);
                        break;
                    case "defense":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addDefense(num);
                        break;
                    case "luck":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addLuck(num);
                        break;
                    case "handicraft":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addHandicraft(num);
                        break;
                    case "attractive":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        Status.getPlayerMap().get(uuid).addAttractive(num);
                        break;
                    default:
                        sender.sendMessage("오타난 커멘드");
                        break;
                }
                //jsonFile_.put(playerName, playerData_.get(playerName).getMap());
                AttributeManager.setAttributes(player, Status.getPlayerMap().get(uuid));
            }
            return true;
        }
        return false;
    }
}
