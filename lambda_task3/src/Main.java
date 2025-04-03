import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> sortList = new ArrayList<>();

        for (Integer i : list) {
            if (i > 0 && i % 2 ==0 ){
                sortList.add(i);
            }
        }
        Collections.sort(sortList);
        System.out.println(sortList);

    }
}