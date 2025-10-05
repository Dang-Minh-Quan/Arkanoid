import java.util.Arrays;

public class Bai4 {

  public class Sort {

    public static void printArray(int[] arr) {
      System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
      int n = arr.length;

      for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < n; j++) {
          if (arr[j] < arr[min_idx]) {
            min_idx = j;
          }
        }

        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
      }
    }
  }

    public static void main(String[] args) {
      int[] Arr = {1, 5, 2, 9, 8};
      long start = System.currentTimeMillis();
      int[] arrSorted = Arrays.copyOf(Arr, Arr.length);
      System.out.println(Arrays.toString(arrSorted));
      Sort.selectionSort(arrSorted);
      System.out.println("->" + Arrays.toString(arrSorted));
      System.out.println("\n");
      long end = System.currentTimeMillis();
      System.out.println(end - start +"ms");
    }
}
