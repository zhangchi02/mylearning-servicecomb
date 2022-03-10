package mylearning.servicecomb.nowcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
描述
•连续输入字符串，请按长度为8拆分每个输入字符串并进行输出；
•长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。

输入描述：
连续输入字符串(输入多次,每个字符串长度小于等于100)

输出描述：
依次输出所有分割后的长度为8的新字符串
 */
public class HWJ4Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List list = new ArrayList();
    List list2 = new ArrayList();
    while (scanner.hasNext()) {
      int num = 5;
      while (num > 0) {
        list.add(scanner.nextLine());
        num--;
      }
//      for (int i = 0; i < list.size(); i++) {
//        String str = list.get(i).toString();
//        int length = str.length();
//        while (8 - length % 8 > 0) {
//          list2.add(str.substring(0,8));
//        }
//        if (length % 8 == 0) {
//          for (int j = 0; j < length; j += 8) {
//            String strs = str.substring(j, j + 7);
//            list2.add(strs);
//          }
//        } else {
//          int space = 8 - (length % 8);
//          for () {
//
//          }
//        }
//      }
    }
  }
}
