import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static final String ROOT_PATH = "/tmp"; // У меня Linux
    public static StringBuilder builderLogFile = new StringBuilder();

    public static void main(String[] args) throws IOException {

        ListDirs structureDirs = new ListDirs("Games",
                List.of(new ListDirs("src",
                                List.of(
                                        new ListDirs("main",
                                                List.of(
                                                        new ListDirs("Main.java", null, false),
                                                        new ListDirs("Utils.java", null, false)
                                                ), true),
                                        new ListDirs("test", List.of(), true)
                                ), true),
                        new ListDirs("res",
                                List.of(
                                        new ListDirs("drawables", List.of(), true),
                                        new ListDirs("vectors", List.of(), true),
                                        new ListDirs("icons", List.of(), true)
                                ), true),
                        new ListDirs("savegames", List.of(), true),
                        new ListDirs("temp",
                                List.of(
                                        new ListDirs("temp.txt", null, false)
                                ), true)
                ), true);


        createDirectory(structureDirs, ROOT_PATH);
        try {
            FileWriter temp = new FileWriter(ROOT_PATH + "/" + "Games/temp/temp.txt");
            temp.write(builderLogFile.toString());
            temp.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void createDirectory(ListDirs structure, String rootPath) throws IOException {
        if (structure != null) {
            String path = rootPath + "/" + structure.getName();
            if (structure.isDirectory()) {
                new File(path).mkdir();
                builderLogFile.append(LocalDateTime.now()).
                        append("\t").
                        append("Время создания директории").
                        append(path).
                        append("\n");
                for (ListDirs subentry : structure.getSubentries()) {
                    createDirectory(subentry, path);
                }
            } else {
                new File(path).createNewFile();
                builderLogFile.append(LocalDateTime.now())
                        .append("\t")
                        .append("Время создания файла ")
                        .append(path).
                        append("\n");
            }
        }
    }

}