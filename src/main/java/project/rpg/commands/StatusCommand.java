package project.rpg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.AttributeManager;

import static project.rpg.manager.ArrayManager.playerData_;
import static project.rpg.manager.FileManager.jsonFile_;

public class StatusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            String playername;
            if(player.getPlayer()!=null) {
                playername = player.getPlayer().getName();
            }
            else{
                Bukkit.getLogger().warning("Player is null");
                return false;
            }

            if ("add".equals(args[0])) {
                int num = Integer.parseInt(args[2]);
                switch (args[1]) {
                    case "strength":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addStrength(num);
                        break;
                    case "agility":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addAgility(num);
                        break;
                    case "speed":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addSpeed(num);
                        break;
                    case "health":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addHealth(num);
                        break;
                    case "defense":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addDefense(num);
                        break;
                    case "luck":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addLuck(num);
                        break;
                    case "handicraft":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addHandicraft(num);
                        break;
                    case "attractive":
                        player.sendMessage(args[1] + " is added " + args[2]);
                        playerData_.get(playername).addAttractive(num);
                        break;
                    default:
                        sender.sendMessage("오타난 커멘드");
                        break;
                }
                jsonFile_.put(playername, playerData_.get(playername).getMap());
                AttributeManager.setAttributes(player, playerData_.get(playername));
            }
            return true;
        }
        return false;
    }
}
