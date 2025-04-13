import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        final String PATH_TO_SAVE = "/tmp/Games/savegames";

        final String PATH_TO_ZIP = PATH_TO_SAVE + "/zip.zip";

        GameProgress gamerOne = new GameProgress(100, 45, 33, 55.5);
        GameProgress gamerTwo = new GameProgress(25, 65, 5, 17.8);
        GameProgress gamerThree = new GameProgress(80, 33, 13, 4.5);
        List<GameProgress> listAllGames = new ArrayList<>();
        listAllGames.add(gamerOne);
        listAllGames.add(gamerTwo);
        listAllGames.add(gamerThree);

        AtomicInteger count = new AtomicInteger();
        List<String> listSaveFiles = new ArrayList<>();
        if (new File(PATH_TO_SAVE).isDirectory()) {
            listAllGames.stream().forEach(gameProgress -> {
                        count.getAndIncrement();
                        saveGame(PATH_TO_SAVE, gameProgress, count);
                        listSaveFiles.add(PATH_TO_SAVE + "/" + "save" + count + ".data");
                    }

            );
            try {
                zipFiles(PATH_TO_ZIP, listSaveFiles);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            listSaveFiles.forEach(deleteFile -> {
                if (new File(deleteFile).delete()) {
                    System.out.println("Файл " + deleteFile + "Удален");
                } else {
                    System.out.println("Файле не удалился");
                }
            });
        } else {
            System.out.println("Не найдена необходимая директория");
        }
        listSaveFiles.forEach(file ->
                {
                    try {
                        Return.unzip(PATH_TO_ZIP, PATH_TO_SAVE );
                        Return.deserialization(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

    }

    private static void zipFiles(String pathToZip, List<String> listSaveFiles) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(pathToZip); ZipOutputStream zos = new ZipOutputStream(fos)) {
            byte[] buffer = new byte[1024];
            listSaveFiles.forEach(file -> {
                File fileToZip = new File(file);
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    zos.putNextEntry(new ZipEntry(fileToZip.getName()));
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


    }

    private static void saveGame(String pathToSave, GameProgress gameProgress, AtomicInteger count) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToSave + "/" + "save" + count + ".data"))) {

            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println("Сохранить не удалос, трейс ошибки: " + ex.getMessage());
        }
    }
}