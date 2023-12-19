package lottoapp.logging;

import lottoapp.App;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    public static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static final String LOG_DIR = "./logs/";
    private static  FileHandler fh;
    private Logging(){}
    public static void setupLogger(){
        //Remove logging output from console
        LOGGER.removeHandler(LOGGER.getHandlers()[0]);

        Calendar now = Calendar.getInstance();

        String logPath = String.format("%s%4d-%02d-%02d_%02d-%02d-%02d.log", LOG_DIR, now.get(Calendar.YEAR),
                now.get(Calendar.MONTH) + 1, now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
        try {
            new File(LOG_DIR).mkdirs();
            new File(logPath).createNewFile();
            fh = new FileHandler(logPath);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            System.err.println("Failed to open log file at '" + logPath + "'.");
        }
        LOGGER.info("Logger set up. App initialized.");
    }
    public static void closeLogger(){
        fh.close();
    }
}
