import java.util.ArrayList;
import java.util.List;

public class Bai7 {
  public static List<Integer> countingSort(List<Integer> arr) {
    List<Integer> count = new ArrayList<>(100);
    for(int i=0;i<100;i++) {
      count.add(0);
    }
    for(int i=0;i<arr.size();i++) {
      count.set(arr.get(i),count.get(arr.get(i))+1);
    }
    return count;
  }
}
