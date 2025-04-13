import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Return {

    public static void deserialization(String pathToFile) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(pathToFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            GameProgress savedGame = (GameProgress) ois.readObject();
            System.out.println(savedGame);

        } catch (EOFException e) {
            System.err.println("Ошибка: достигнут конец файла - " + e.getMessage());
        }
    }
    // Method to unzip files
    public static void unzip(String zipFile, String rootPath) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                FileOutputStream fout = new FileOutputStream(rootPath + "/" + entry.getName());
                for (int c = zis.read(); c !=-1 ; c= zis.read()) {
                    fout.write(c);
                }
                fout.flush();
                zis.closeEntry();
                fout.close();
            }
        }
    }
}
