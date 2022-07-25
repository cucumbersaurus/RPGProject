package project.rpg.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import project.rpg.manager.FileManager

class FileSaveCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        //FileManager.makeFile();
        //FileManager.saveFile();
        FileManager.makeDir()
        FileManager.asyncSaveAll()
        //FileManager.getFile();
        return false
    }
}