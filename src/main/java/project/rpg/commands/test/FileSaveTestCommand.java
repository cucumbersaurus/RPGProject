package project.rpg.commands.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import project.rpg.manager.FileManager;

public class FileSaveTestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileManager.makeFile();
        FileManager.saveFile();
        FileManager.getFile();

        return false;
    }
}
