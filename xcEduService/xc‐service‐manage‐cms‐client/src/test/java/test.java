/**
 * @ClassName
 * @Description TODO
 * @Author 王晨
 * @Date
 **/
public class test {

  public static void main(String[] args) {
    String str = "aabccccaaa";
    char[] chars = str.toCharArray();
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 0; i < chars.length - 1; i++) {
      if (chars[i] == chars[i + 1]) {
        count++;
      } else {
        if (count > 1) {
          sb.append(chars[i]).append(count);
        } else {
          sb.append(chars[i]);
        }
        count = 1;
      }

      if (i == chars.length - 2) {
        if (chars[chars.length - 2] == chars[chars.length - 1]) {
          sb.append(chars[i]).append(count);
        } else {
          if (count > 1) {
            sb.append(chars[i]).append(count).append(chars[chars.length - 1]);
          } else {
            sb.append(chars[chars.length - 1]);
          }
        }
      }
    }
    System.out.println(sb.toString());
  }
}
