import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        String installDirectory = "Games";
        Consumer<String> installer = Installer::install;
        installer.accept(installDirectory);
        GameProgress game1 = new GameProgress(100, 50, 30, 89);
        GameProgress game2 = new GameProgress(90, 38, 32, 105);
        GameProgress game3 = new GameProgress(40, 10, 45, 240);
        String[] saveGameList = {game1.toString(), game2.toString(), game3.toString()};
        game1.saveGame(installDirectory, game1);
        game2.saveGame(installDirectory, game2);
        game3.saveGame(installDirectory, game3);
        game1.zipFiles(installDirectory, saveGameList);
    }
}

