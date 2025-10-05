import java.util.Scanner;
import java.util.Stack;

public class Bai5 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    if (!scanner.hasNextInt()) return;
    int Q = scanner.nextInt();
    scanner.nextLine();

    String S = "";
    Stack<String> history = new Stack<>();

    for (int q = 0; q < Q; q++) {

      String line = scanner.nextLine();
      String[] parts = line.split(" ");
      int type = Integer.parseInt(parts[0]);

      switch (type) {

        // append
        case 1:
          history.push(S);

          String W = parts[1];
          S = S + W;

          break;

        // delete
        case 2:
          history.push(S);

          int k_delete = Integer.parseInt(parts[1]);
          if (S.length() >= k_delete) {
            S = S.substring(0, S.length() - k_delete);
          } else {
            S = "";
          }
          break;

        // print
        case 3:
          int k_print = Integer.parseInt(parts[1]);
          if (k_print > 0 && k_print <= S.length()) {
            System.out.println(S.charAt(k_print - 1));
          }
          break;

        // undo
        case 4:
          if (!history.isEmpty()) {
            S = history.pop();
          }
          break;
      }
    }
  }

}
