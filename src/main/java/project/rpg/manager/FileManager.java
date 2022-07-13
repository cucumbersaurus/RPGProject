package project.rpg.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileManager {

    public static final JSONObject _jsonFile = new JSONObject();
    private static final String FILE_PATH = System.getProperty("user.dir") + "/plugins/rpg/playerDs.json";//JavaPlugin.getPlugin(Rpg.class).getDataFolder().toString();
    private static final File _playerDs = new File(FILE_PATH);
    private static final String _player = "players";

    public static void makeFile() {
        File f = new File(FILE_PATH);

        if (f.exists()) {
            Bukkit.getLogger().info("playerDs.json exists");
        } else {
            Bukkit.getLogger().info("playerDs.json not exist");
            try {
                if(f.createNewFile()){
                    Bukkit.getLogger().info(" playerDs.json made");
                }
            } catch (IOException e) {
                Bukkit.getLogger().info(FILE_PATH);
                e.printStackTrace();
            }
            saveFile();
        }
    }

    public static void saveFile() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(_playerDs)) {
            //gson.toJson(Status.getPlayerMap(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFile() {
        try(Reader reader = Files.newBufferedReader(Paths.get(_playerDs.getAbsolutePath()))){
            Gson gson = new Gson();
            Map<?, ?> map =  gson.fromJson(reader, Map.class);
            //여기를 어떻게 처리하지?
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    FileManager() {
    }
}