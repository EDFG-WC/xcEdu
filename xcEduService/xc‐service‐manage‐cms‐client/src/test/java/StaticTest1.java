/**
 * @ClassName
 * @Description TODO
 * @Author 王晨
 * @Date
 **/
public class StaticTest1 {

  public static void main(String[] args) {
    int[] arr = {0,1,2};
    System.out.println(arr[0]);
    change(arr);
    System.out.println(arr[0]);
  }

  public static void change(int[] array) {
    array[0]= -100;
  }
}
