package rpgProject.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import rpgProject.ui.QuestListUI;


public class QuestToggle implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length>0){
                if(args[0].equals("true")){
                    QuestListUI scoreboardUI = new QuestListUI(p);
                    scoreboardUI.showScoreboard();
                    QuestListUI.map.put(p, scoreboardUI);
                }
                else{
                    QuestListUI scoreboardUI =  QuestListUI.map.get(p);
                    scoreboardUI.hideScoreboard();
                }
            }
            else return false;

        }
        return false;
    }
}
