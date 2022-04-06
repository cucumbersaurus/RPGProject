package project.rpg.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import project.rpg.Rpg;

import java.io.*;
import java.util.List;

import static project.rpg.manager.ArrayManager.Players;

public class FileManager {

    public static final JSONObject jsonFile = new JSONObject();
    public static final JSONArray jsonList = new JSONArray();
    private static final String FILE_PATH = JavaPlugin.getPlugin(Rpg.class).getDataFolder().toString();
    private static final File playerDs = new File(FILE_PATH + "playerData/playerDs.json");

    public static void makeFile() {
        File f = new File(FILE_PATH + "playerData/playerDs.json");
        if (f.exists()) {
            Bukkit.getLogger().info("playerDs.json exists");
        } else {
            Bukkit.getLogger().info("playerDs.json not exist");
            Bukkit.getLogger().info("making playerDs.json");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerDs))) {
                writer.write(jsonFile.toJSONString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerDs))) {
            writer.write(jsonFile.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getFile() {
        JSONParser parser = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new FileReader(playerDs))) {
            Object obj = parser.parse(reader);
            return (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void makeList() {
        if (playerDs.exists()) {
            Bukkit.getLogger().info("playerList.json exists");
        } else {
            Bukkit.getLogger().info("playerList.json not exist");
            Bukkit.getLogger().info("making playerList.json");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerDs))) {
                writer.write(jsonList.toJSONString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveList() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(playerDs))) {
            for(Object obj : Players.toArray()){
                if (!jsonList.contains(obj)) {
                    jsonList.add(obj);
                }
            }
            writer.write(jsonList.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getList() {
        JSONParser parser = new JSONParser();
        try(BufferedReader reader = new BufferedReader(new FileReader(playerDs))) {
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    FileManager() {
    }
}