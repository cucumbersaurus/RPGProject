package rpgProject.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import rpgProject.ui.QuestListUI;

import java.util.ArrayList;


public class QuestToggle implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length>0&&args.length<17){
                if(args[0].equals("true")){

                    QuestListUI scoreboardUI = new QuestListUI(p);
                    ArrayList  arrayList = new ArrayList();
                    for(int i=1;i< args.length;i++){
                        arrayList.add(args[i]);
                    }

                    scoreboardUI.setScoreboard(p, arrayList);
                    scoreboardUI.showScoreboard();
                    QuestListUI.playerQuestMap_.put(p, scoreboardUI);
                }
                else if(args[0].equals("false")){
                    QuestListUI scoreboardUI =  QuestListUI.playerQuestMap_.get(p);
                    scoreboardUI.hideScoreboard();
                }
            }
            else if(args.length==0){
                p.sendMessage("/quests <true|false>");
            }
            else{
                p.sendMessage("too short or too long");
                return true;
            }
        }
        return false;
    }
}
