import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public void saveGame(String installDirectory, Object obj) {
        File saveFile = new File(installDirectory + "/savegames/" + this + ".dat");
        try (ObjectOutputStream outputObj = new ObjectOutputStream(new FileOutputStream(saveFile, false))) {
            outputObj.writeObject(obj.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void zipFiles(String installDirectory, String[] saveGameList) {
        File zipFile = new File(installDirectory + "/savegames/" + this.getClass().getName() + ".zip");
        try (ZipOutputStream outputObj = new ZipOutputStream(new FileOutputStream(zipFile, false))) {
            for (String element : saveGameList) {
                try (FileInputStream fis = new FileInputStream(installDirectory + "/savegames/" + element + ".dat")) {
                    ZipEntry entry = new ZipEntry(element + ".dat");
                    outputObj.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    outputObj.write(buffer);
                    outputObj.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            File dir = new File(installDirectory + "/savegames");
            for (File item : dir.listFiles()) {
                if (!item.getName().contains(".zip")) item.delete();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}