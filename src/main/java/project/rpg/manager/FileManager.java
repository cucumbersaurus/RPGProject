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
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

public final class FileManager {

    @Deprecated
    private static final String FILE_PATH = System.getProperty("user.dir") + "/plugins/Rpg/playerDs.json";
    private static final String ROOT_PATH = System.getProperty("user.dir") + "\\plugins\\Rpg";
    private static final String ROOT_DATA_PATH = ROOT_PATH + "\\playerData";
    private static final File _playerDs = new File(FILE_PATH);

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

    @Deprecated
    public static void asyncSaveFile() {
        Rpg plugin = (Rpg) Bukkit.getPluginManager().getPlugin("Rpg");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(plugin), ()->{

            try(FileOutputStream out = new FileOutputStream(_playerDs)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson.toJson(User.serializeAll());

                byte[] utf8JsonString = jsonString.getBytes(StandardCharsets.UTF_8);

                out.write(utf8JsonString);
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void asyncSaveFile(Player player) {
        final String PLAYER_FILE_PATH = MessageFormat.format("{0}\\{1}.json", ROOT_DATA_PATH, player.getUniqueId());
        Rpg plugin = (Rpg) Bukkit.getPluginManager().getPlugin("Rpg");

        if(makeFile(PLAYER_FILE_PATH)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(plugin), () -> {

                try (FileOutputStream out = new FileOutputStream(PLAYER_FILE_PATH)) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonString = gson.toJson(User.getPlayer(player).serialize());
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
                String jsonString = gson.toJson(User.getPlayer(player).serialize());
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

    @Deprecated
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