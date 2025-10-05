import java.util.List;

public class Bai5 {
  public static void insertionSort2(int n, List<Integer> arr) {
    for (int i = 1; i < n; i++) {
      int key = arr.get(i);
      int j = i - 1;

      while (j >= 0 && arr.get(j) > key) {
        arr.set(j + 1, arr.get(j));
        j--;
      }

      arr.set(j + 1 , key);

      for (int k = 0; k < arr.size(); k++) {
        System.out.print(arr.get(k));

        if (k < arr.size() - 1) {
          System.out.print(" ");
        } else {
          System.out.println();
        }
      }
    }
  }
}

