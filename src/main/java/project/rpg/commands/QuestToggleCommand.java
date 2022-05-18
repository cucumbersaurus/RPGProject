package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import project.rpg.ui.ScoreboardUI;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestToggleCommand implements CommandExecutor {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length>0&&args.length<17){
                if(args[0].equals("true")){

                    if(args.length==1){
                        ScoreboardUI.showScoreboard(player);
                        player.sendMessage("shown");
                        return true;
                    }

                    ScoreboardUI scoreboardUI;
                    if(ScoreboardUI.getScoreboard(player) != null){
                        scoreboardUI= ScoreboardUI.getScoreboard(player);
                        player.sendMessage("from map");
                    }
                    else{
                        scoreboardUI = new ScoreboardUI(player);
                        player.sendMessage("new");
                    }

                    ArrayList<String>  arrayList = new ArrayList<>();
                    arrayList.toArray(Arrays.stream(args).toArray());

                    scoreboardUI.setContents(arrayList);
                }
                else if(args[0].equals("false")){
                    ScoreboardUI.hideScoreboard(player);
                    player.sendMessage("hidden");
                }
            }
            else if(args.length==0){
                player.sendMessage("/quests <true|false>");
            }
            else{
                player.sendMessage("too short or too long");
                return true;
            }
        }
        return false;
    }
}
