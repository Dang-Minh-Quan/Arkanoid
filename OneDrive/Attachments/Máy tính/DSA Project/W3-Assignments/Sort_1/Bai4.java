import java.util.Comparator;

public class Bai4 {
  class Student{
    private int id;
    private String fname;
    private double cgpa;
    public Student(int id, String fname, double cgpa) {
      super();
      this.id = id;
      this.fname = fname;
      this.cgpa = cgpa;
    }
    public int getId() {
      return id;
    }
    public String getFname() {
      return fname;
    }
    public double getCgpa() {
      return cgpa;
    }
  }

  class StudentComparator implements Comparator<Student>
  {
    @Override
    public int compare(Student x, Student y) {
      if (x.getCgpa() != y.getCgpa()) {
        return Double.compare(y.getCgpa(), x.getCgpa());
      }

      int nameComparition = y.getFname().compareTo(x.getFname());
      if (nameComparition != 0) {
        return nameComparition;
      }

      return Integer.compare(x.getId(), y.getId());
    }
  }
}
