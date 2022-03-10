package mylearning.servicecomb.nowcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.jruby.util.IOInputStream;

/**
 * 描述
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
 *
 * 数据范围： 0<=n<=1000 ，输入的数据有可能包含大小写字母、数字和空格
 * 输入描述：
 * 第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字符。
 *
 * 输出描述：
 * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
 */
public class HWJ2Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str1 = scanner.nextLine();
    String str2 = scanner.nextLine();
    int len1 = str1.length();
    int len2 = str2.length();
    if (len1 < 1 || len1 > 1000 || len2 < 1 || len2 > 1000) {
      return;
    }
    char[] chars1 = str1.toLowerCase().toCharArray();
    char[] chars2 = str2.toLowerCase().toCharArray();

    int i = chars1.length;
    int num = 0;
    while (i > 0) {
      if (chars1[--i] == chars2[0]) {
        num++;
      }
    }
    System.out.println(num);
  }

  public void best() throws IOException {
    BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader buf2 = new BufferedReader(new InputStreamReader(System.in));
    char[] chars1 = buf1.readLine().toLowerCase().toCharArray();
    char[] chars2 = buf2.readLine().toLowerCase().toCharArray();
    int count = 0;
    for (int i = 0; i < 1001; i++) {
      if ((chars1[i] >= 65 || chars1[i] <= 90) && (chars2[0] == chars1[i])) {
        count++;
      }
    }
    System.out.println(count);
  }
}
