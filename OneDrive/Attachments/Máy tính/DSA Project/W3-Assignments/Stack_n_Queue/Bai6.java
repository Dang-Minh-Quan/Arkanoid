import java.util.List;

public class Bai6 {
  public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
    // Write your code here
    int t1=0, t2=0, t3=0;
    for(int t : h1){
      t1 += t;
    }
    for(int t : h2){
      t2 += t;
    }
    for(int t : h3){
      t3 += t;
    }
    while(t1 != t2 || t2 !=t3) {
      if(h1.isEmpty() || h2.isEmpty() || h3.isEmpty()) {
        return 0;
      }

      if(t1>=t2 && t1>=t3) {
        t1 = t1 - h1.get(0);
        h1.remove(0);
      }

      else if(t2>=t1 && t2>=t3) {
        t2 = t2 - h2.get(0);
        h2.remove(0);
      }

      else if(t3>=t2 && t3>=t1) {
        t3 = t3 - h3.get(0);
        h3.remove(0);
      }
    }
    return t1;

  }
}

}
