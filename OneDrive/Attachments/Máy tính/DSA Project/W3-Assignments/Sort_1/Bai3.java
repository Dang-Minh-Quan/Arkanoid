import java.util.List;
import java.util.Arrays;
public class Bai3 {
  public static void insertionSort1(int n, List<Integer> arr) {
      int key = arr.get(n-1);
      int i = n - 2;

      while (i >= 0 && arr.get(i) > key) {
        arr.set(i + 1, arr.get(i));

        for (int j = 0; j < arr.size(); j++) {
          System.out.print(arr.get(j));

          if (j < arr.size() - 1) {
            System.out.print(" ");
          } else {
            System.out.println();
          }
        }

        i--;
      }

      arr.set(i + 1 , key);

      for (int j = 0; j < arr.size(); j++) {
        System.out.print(arr.get(j));

        if (j < arr.size() - 1) {
          System.out.print(" ");
        } else {
          System.out.println();
        }
      }
    }
  }
}
