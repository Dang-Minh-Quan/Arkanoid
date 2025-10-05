import java.util.Stack;

public class Bai2 {
  public static String isBalanced(String s) {
    Stack<Character> st = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char current = s.charAt(i);

      if (current == '(' || current == '[' || current == '{') {
        st.push(current);
      }

      else if (current == ')' || current == ']' || current == '}') {

        if (st.isEmpty()) {
          return "NO";
        }

        char top = st.peek();

        if (current == ')' && top == '(') {
          st.pop();
        } else if (current == ']' && top == '[') {
          st.pop();
        } else if (current == '}' && top == '{') {
          st.pop();
        } else {
          return "NO";
        }
      }
    }
    if(st.isEmpty()) {
      return "YES";
    } else {
      return "NO";
    }
  }
}
