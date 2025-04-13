import java.util.List;

public class ListDirs {
    private String name;
    private List<ListDirs> subentries;
    private boolean isDirectory;

    public ListDirs(String name, List<ListDirs> subentries, boolean isDirectory) {
       this.name = name;
       this.subentries = subentries;
       this.isDirectory = isDirectory;
    }

    public String getName() {
        return name;
    }

    public List<ListDirs> getSubentries() {
        return subentries;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}
