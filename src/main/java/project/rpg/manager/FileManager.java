package project.rpg.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import project.rpg.Rpg;
import project.rpg.player.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

public final class FileManager {

    private static final String ROOT_PATH = System.getProperty("user.dir") + "\\plugins\\Rpg";
    private static final String ROOT_DATA_PATH = ROOT_PATH + "\\playerData";

    public static void makeDir() {
        File rpg = new File(ROOT_PATH);
        File root = new File(ROOT_DATA_PATH);

        if(!rpg.exists()){
            rpg.mkdir();
        }
        if(!root.exists()){
            root.mkdir();
        }

    }

    public static boolean makeFile(final String PATH) {
        File f = new File(PATH);

        if (f.exists()) {
            Bukkit.getLogger().info("player data file exists");
            return true;
        } else {
            Bukkit.getLogger().info("player data file dose not exists");
            try {
                if(f.createNewFile()){
                    Bukkit.getLogger().info("player data file has been created");
                    return true;
                }
            } catch (IOException exception) {
                Bukkit.getLogger().info(PATH);
                exception.printStackTrace();
            }
        }
        return false;
    }
    public static void asyncSaveFile(Player player) {
        final String PLAYER_FILE_PATH = MessageFormat.format("{0}\\{1}.json", ROOT_DATA_PATH, player.getUniqueId());
        Rpg plugin = (Rpg) Bukkit.getPluginManager().getPlugin("Rpg");

        if(makeFile(PLAYER_FILE_PATH)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(plugin), () -> {

                try (FileOutputStream out = new FileOutputStream(PLAYER_FILE_PATH)) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonString = gson.toJson(User.getPlayer(player));//.serialize()); 컴파일을 위한 임시 처리
                    byte[] utf8JsonString = jsonString.getBytes(StandardCharsets.UTF_8);

                    out.write(utf8JsonString);
                    out.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void syncedSaveFile(Player player) {
        final String PLAYER_FILE_PATH = MessageFormat.format("{0}\\{1}.json", ROOT_DATA_PATH, player.getUniqueId());

        if(makeFile(PLAYER_FILE_PATH)) {
            try (FileOutputStream out = new FileOutputStream(PLAYER_FILE_PATH)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson.toJson(User.getPlayer(player));//.serialize()); 컴파일을 위한 임시 처리
                byte[] utf8JsonString = jsonString.getBytes(StandardCharsets.UTF_8);

                out.write(utf8JsonString);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void syncedSaveAll(){
        for(Player player:Bukkit.getOnlinePlayers()){
            syncedSaveFile(player);
        }
    }
    public static void asyncSaveAll(){
        for(Player player:Bukkit.getOnlinePlayers()){
            asyncSaveFile(player);
        }
    }

    public static void getFile(Player player) {
        try(Reader reader = Files.newBufferedReader(new File(MessageFormat.format("{0}\\{1}.json", ROOT_DATA_PATH, player.getUniqueId())).toPath())){
            Gson gson = new Gson();
            Map<String, String> map =  (Map<String, String>) gson.fromJson(reader , Map.class);
            //User.deserialize(map); 컴파일을 위한 임시 처리
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    FileManager() {
    }
}