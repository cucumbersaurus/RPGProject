package project.rpg.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import project.rpg.Rpg;
import project.rpg.player.info.Status;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileManager {

    public static final JSONObject jsonFile_ = new JSONObject();
    private static final String FILE_PATH_ = JavaPlugin.getPlugin(Rpg.class).getDataFolder().toString();
    private static final File playerDs_ = new File(FILE_PATH_ + "/playerData/playerDs.json");
    private static final String PLAYER = "players";

    public static void makeFile() {
        File f = new File(FILE_PATH_ + "/playerData/playerDs.json");
        if (f.exists()) {
            Bukkit.getLogger().info("playerDs.json exists");
        } else {
            Bukkit.getLogger().info("playerDs.json not exist");
            try {
                if(f.createNewFile()){
                    Bukkit.getLogger().info(" playerDs.json made");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveFile();
        }
    }

    public static void saveFile() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(playerDs_)) {
            gson.toJson(Status.getPlayerMap(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.broadcastMessage(Status.getPlayerMap().toString());
    }

    public static void getFile() {
        try(Reader reader = Files.newBufferedReader(Paths.get(playerDs_.getAbsolutePath()))){
            Gson gson = new Gson();
            Map<?, ?> map =  gson.fromJson(reader, Map.class);
            //여기를 어떻게 처리하지?
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    FileManager() {
    }
}