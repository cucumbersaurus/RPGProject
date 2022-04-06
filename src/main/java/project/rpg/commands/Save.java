package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import project.rpg.file.FileSystem;
import project.rpg.player.info.PlayerStatus;

public class Save implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length>=1&&args[0].equals("status")){
            FileSystem.saveStatus();
        }
        else if(args.length>=1&&args[0].equals("load")){
            PlayerStatus.fromJsonFile(FileSystem.loadStatus());
        }
        return  true;
    }
}
