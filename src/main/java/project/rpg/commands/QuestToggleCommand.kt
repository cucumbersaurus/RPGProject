package project.rpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import project.rpg.ui.text.ScoreboardUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestToggleCommand implements CommandExecutor, TabCompleter {



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length>0&&args.length<17){
                if(args[0].equals("true")){



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

                    if(args.length>1){
                        ScoreboardUI.showScoreboard(player);
                        player.sendMessage("shown");
                        return true;
                    }
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

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length==1){
            List<String> recommendation = new ArrayList<>();
            recommendation.add("true");
            recommendation.add("false");
            return recommendation;
        }
        return null;
    }
}
