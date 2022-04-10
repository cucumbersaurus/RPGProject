package project.rpg.manager;

import project.rpg.player.info.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayManager {

    public static final List<String> Players_ = new ArrayList<>();
    public static final Map<String, Status> playerData_ = new HashMap<>();

    public static void putJson() throws NullPointerException {
        for (int i = 0; i<FileManager.getList().toArray().length; i++) {
            String needPut = (String) FileManager.getList().toArray()[i];
            Players_.add(needPut);

            try {

                HashMap<String, Integer> hashMap = new HashMap<>();
                String gFile = FileManager.getFile().get(needPut).toString();
                gFile = gFile.replace("}", "");
                gFile = gFile.replace("{", "");

                String[] arrayS = gFile.split(",");

                for (String a : arrayS) {
                    String[] arrayK = a.split(":", 15);

                    arrayK[0] = arrayK[0].replace("\"", "");
                    arrayK[1] = arrayK[1].replace("\"", "");

                    hashMap.put( arrayK[0], Integer.valueOf(arrayK[1]));
                }

                Status status = new Status(needPut, hashMap);
                playerData_.put(needPut, status);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}