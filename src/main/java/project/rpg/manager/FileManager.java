package project.rpg.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.rpg.Rpg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static project.rpg.manager.ArrayManager.Players_;
import static project.rpg.manager.ArrayManager.playerData_;

public class FileManager {

    public static final JSONObject jsonFile_ = new JSONObject();
    public static final JSONArray jsonList_ = new JSONArray();
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
        try(FileWriter writer = new FileWriter(FILE_PATH_ + "/playerData/playerDs.json")) {
            gson.toJson(playerData_, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.broadcastMessage(playerData_.toString());

    /*
        JsonObject object = new JsonObject();
        object.addProperty(PLAYER, jsonList_.toJSONString());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerDs_))) {
            writer.write(object.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
     */
    }

    public static JsonObject getFile() {
        JSONParser parser = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new FileReader(playerDs_))) {
            JsonObject obj = (JsonObject) parser.parse(reader);
            return obj.get(PLAYER).getAsJsonObject();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void makeList() {
        if (playerDs_.exists()) {
            Bukkit.getLogger().info("playerList.json exists");
        } else {
            Bukkit.getLogger().info("playerList.json not exist");
            Bukkit.getLogger().info("making playerList.json");
            saveFile();
        }
    }

    public static void saveList() {
        jsonList_.clear();
        jsonList_.addAll(Players_);
    }

    public static List<String> getList() {
        JSONParser parser = new JSONParser();
        try(BufferedReader reader = new BufferedReader(new FileReader(playerDs_))) {
            JsonObject obj = (JsonObject) parser.parse(reader);

            JsonArray jsonArray = obj.get(PLAYER).getAsJsonArray();
            Gson gson = new Gson();
            return gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<String>>(){}.getType());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    FileManager() {
    }
}