package project.rpg.file;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import project.rpg.Rpg;
import project.rpg.player.info.PlayerStatus;

import java.io.*;

public class FileSystem {

    private static final File statusFile = new File(JavaPlugin.getPlugin(Rpg.class).getDataFolder(), "/data/status.json");

    public static void mkFile(){
        try {
            if(statusFile.createNewFile()){
                Bukkit.getLogger().info("Status file dose not exist. Making new file.");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void saveStatus() {

        try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(statusFile))) {
            String str = PlayerStatus.toJsonFile();
            outputStream.write(str.getBytes());
            outputStream.flush();
            Bukkit.broadcastMessage("saved");
            Bukkit.broadcastMessage(PlayerStatus.toJsonFile());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String loadStatus(){
        String str = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(statusFile))) {
            str = reader.readLine();
            Bukkit.broadcastMessage(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

}
