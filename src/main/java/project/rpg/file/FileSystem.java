package project.rpg.file;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import project.rpg.Rpg;
import project.rpg.player.info.PlayerStatus;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
            String str = PlayerStatus.map_.toString();
            outputStream.write(str.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
