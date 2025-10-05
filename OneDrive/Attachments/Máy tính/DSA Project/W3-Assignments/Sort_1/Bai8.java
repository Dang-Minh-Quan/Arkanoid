public class Bai8 {
//  Lý do không nên dùng LinkedList cho Insertion Sort:
  //
  //  Insertion Sort hoạt động bằng cách lặp lại việc so sánh và dịch chuyển các phần tử liền kề trong phần đã sắp xếp của danh sách.
  //  Truy cập phần tử: Để so sánh A[i] với A[i−1], trong LinkedList, bạn phải duyệt từ đầu đến vị trí i và i−1, làm cho thao tác so sánh cơ bản tốn O(n) thời gian.
  //  Tổng độ phức tạp: Độ phức tạp thời gian của Insertion Sort là O(n
  //  Nếu dùng LinkedList, thao tác truy cập ngẫu nhiên chậm sẽ đẩy độ phức tạp lên tới O(n) khiến thuật toán trở nên cực kỳ kém hiệu quả.

//  Lý do không nên dùng LinkedList cho Selection Sort:
  //
  //  Selection Sort dựa vào việc tìm phần tử nhỏ nhất/lớn nhất trong phần còn lại của danh sách.
  //  Tìm phần tử nhỏ nhất: Thao tác này là O(n) bất kể dùng Mảng hay LinkedList (vì bạn vẫn phải duyệt qua tất cả các phần tử).
  //  Hoán đổi: Sau khi tìm thấy, Selection Sort cần truy cập ngẫu nhiên để hoán đổi phần tử nhỏ nhất về vị trí i.
  //  Trong Mảng/ArrayList, hoán đổi là O(1).
  //  Trong LinkedList, việc truy cập đến vị trí i tốn O(n), làm cho mỗi lần hoán đổi tốn O(n).
  //  Tổng độ phức tạp: Độ phức tạp thời gian của Selection Sort là O(n)
  //  Nếu dùng LinkedList, thao tác hoán đổi chậm sẽ giữ nguyên độ phức tạp là O(n) nhưng với hệ số hằng số lớn hơn nhiều, khiến nó chậm hơn đáng kể so với việc dùng Mảng/ArrayList.
}
