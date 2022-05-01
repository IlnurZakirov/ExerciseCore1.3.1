import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Installer {

    public static void install(String installDirectory) {
        privateInstall(installDirectory);
    }

    private static void privateInstall(String installDirectory) {

        StringBuilder log = new StringBuilder();
        String[] config = {
                "src",
                "src/main",
                "src/main/Main.java",
                "src/main/Util.java",
                "src/test",
                "res",
                "res/drawables",
                "res/vectors",
                "res/icons",
                "savegames",
                "temp",
                "temp/temp.txt"
        };

        for (String element : config) {
            String installElement = installDirectory + "/" + element;
            File file = new File(installElement);
            if (installElement.contains(".")) {
                try {
                    log
                            .append("file: ")
                            .append(installElement)
                            .append(file.createNewFile() ? " created successfully\n" : " not created\n");
                } catch (IOException ex) {
                    log.append(ex.getMessage());
                }
            } else log
                    .append("dir: ")
                    .append(installElement)
                    .append(file.mkdir() ? " created successfully\n" : " not created\n");
        }
        try (FileWriter writer = new FileWriter((installDirectory + "/temp/temp.txt"), true)) {
            writer.write(String.valueOf(log));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
