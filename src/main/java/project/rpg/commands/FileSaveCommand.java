package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.FileManager;

public class FileSaveCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //FileManager.makeFile();
        //FileManager.saveFile();
        FileManager.makeDir();
        FileManager.asyncSaveAll();
        //FileManager.getFile();

        return false;
    }
}
