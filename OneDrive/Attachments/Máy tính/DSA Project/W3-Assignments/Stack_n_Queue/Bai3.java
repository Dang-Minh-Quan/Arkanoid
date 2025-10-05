public class Bai3 {
  public class Queue {

    private int[] arr;
    private int front;
    private int back;
    private int capacity;

    public Queue(int size) {
      this.capacity = size;
      this.arr = new int[size];
      this.front = -1;
      this.back = -1;
    }

    public void enqueue(int element) {
      if (isFull()) {
        System.out.println("Error: Queue is full");
        return;
      }
      if (isEmpty()) {
        front = 0;
      }
      back = (back + 1) % capacity;
      arr[back] = element;
      System.out.println("Push: " + element);
    }

    public int dequeue() {
      if (isEmpty()) {
        System.out.println("Error: Queue is empty");
        return -1;
      }
      int element = arr[front];

      // Chỉ còn 1 phần tử
      if (front == back) {
        front = -1;
        back = -1;
      } else {
        front = (front + 1) % capacity;
      }
      System.out.println("Pop: " + element);
      return element;
    }

    public boolean isEmpty() {
      return front == -1;
    }

    public boolean isFull() {
      return (back + 1) % capacity == front;
    }

    public void print() {
      if (isEmpty()) {
        System.out.println("Queue is Empty");
        return;
      }

      System.out.print("Queue:");
      int i = front;

      while (true) {
        System.out.print(arr[i]);
        if (i == back) {
          break;
        }

        System.out.print(", ");

        i = (i + 1) % capacity;
      }
    }
  }
}
