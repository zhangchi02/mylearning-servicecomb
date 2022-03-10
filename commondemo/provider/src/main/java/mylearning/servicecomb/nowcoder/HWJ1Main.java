package mylearning.servicecomb.nowcoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/*
描述
计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。
（注：字符串末尾不以空格为结尾）
输入描述：
输入一行，代表要计算的字符串，非空，长度小于5000。

输出描述：
输出一个整数，表示输入字符串最后一个单词的长度。

示例1
输入：
hello nowcoder
输出：
8
说明：
最后一个单词为nowcoder，长度为8
 */
public class HWJ1Main {
  public static void main(String[] args) {
    String[] strings = new Scanner(System.in).nextLine().split(" ");
    int length = strings[(strings.length - 1)].length();
    System.out.println(length);
  }

  public void best() throws IOException {
    InputStream inputStream = System.in;
    char str = (char) inputStream.read();
    int len = 0;
    while (str != '\n') {
      if (str == ' ') {
        len = 0;
      } else {
        len++;
        str = (char) inputStream.read();
      }
    }
    System.out.println(len);
  }
}
