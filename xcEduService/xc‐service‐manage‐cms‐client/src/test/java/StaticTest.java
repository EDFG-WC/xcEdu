/**
 * @ClassName
 * @Description TODO
 * @Author 王晨
 * @Date
 **/
public class StaticTest {

  public StaticTest() {
    System.out.println("执行构造方法");
  }

  {
    System.out.println("非静态代码块！--");
  }

  static {
    System.out.println("执行静态代码块");
  }

  public static void test() {
    System.out.print("静态方法中的内容! --");
    {
      System.out.print("静态方法中的代码块！--");
    }
  }

}
