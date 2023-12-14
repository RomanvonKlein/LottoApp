package lottoapp.data;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lottoapp.App;

public class Storage {
    private static final String BLACKLIST_FILE_PATH = "./blacklist.json";

    private Storage() {
    }

    public static void saveBlacklist() {
        try {
            Gson gson = new Gson();
            File file = new File(BLACKLIST_FILE_PATH);
            if (file.exists()) {
                file.delete();
            }
            String json = gson.toJson(App.BLACKLIST.toArray());
            App.LOGGER.info(String.format("Parsed json from blacklist: '%s'",json));
            FileWriter writer = new FileWriter(BLACKLIST_FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            App.LOGGER.severe(String.format("Failed to write Blacklist json to path : '%s'",BLACKLIST_FILE_PATH));
            App.LOGGER.severe(e.toString());
        }

    }

    public static List<Integer> loadBlacklist() {
        Gson gson = new Gson();
        try {
            JsonReader jsreader = new JsonReader(new FileReader(BLACKLIST_FILE_PATH));

            int[] blacklistNumbers = gson.fromJson(jsreader, int[].class);
            App.LOGGER.info("Read values from blacklist.json: " + Arrays.toString(blacklistNumbers));
            jsreader.close();
            for (int number : blacklistNumbers) {
                App.LOGGER.info(String.format("Loaded number %d for blacklist from filesystem.", number));
                App.BLACKLIST.add(number);
            }
        } catch (FileNotFoundException e) {
            App.LOGGER
                    .info("The blacklist file did not exist. Creating it with next modification to of the Blacklist.");
        }catch (IOException e){
            App.LOGGER.severe("Failed reading the existing blacklist.json file!");
        }

        return new ArrayList<>();
    }
}
