package project.rpg.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import project.rpg.Rpg;
import project.rpg.player.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

public final class FileManager {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/plugins/rpg/playerDs.json";
    private static final File _playerDs = new File(FILE_PATH);

    public static void makeFile() {
        File f = new File(FILE_PATH);

        if (f.exists()) {
            Bukkit.getLogger().info("playerDs.json exists");
        } else {
            Bukkit.getLogger().info("playerDs.json not exist");
            try {
                if(f.createNewFile()){
                    Bukkit.getLogger().info("playerDs.json made");
                }
            } catch (IOException e) {
                Bukkit.getLogger().info(FILE_PATH);
                e.printStackTrace();
            }
            saveFile();
        }
    }

    public static void saveFile() {
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