package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.ui.QuestListUI;

import java.util.ArrayList;

public class QuestToggle implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length>0&&args.length<17){
                if(args[0].equals("true")){

                    if(args.length==1){
                        QuestListUI.playerQuestMap_.get(p).showScoreboard();
                        p.sendMessage("shown");
                        return true;
                    }

                    QuestListUI scoreboardUI;
                    if(QuestListUI.playerQuestMap_.getOrDefault(p, null) != null){
                        scoreboardUI=QuestListUI.playerQuestMap_.get(p);
                        p.sendMessage("from map");
                    }
                    else{
                        scoreboardUI = new QuestListUI(p);
                        p.sendMessage("new");
                    }

                    ArrayList<String>  arrayList = new ArrayList<>();
                    for(int i=1;i< args.length;i++){
                        arrayList.add(args[i]);
                    }

                    scoreboardUI.setContents(arrayList);
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
