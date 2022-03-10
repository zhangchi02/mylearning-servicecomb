package mylearning.servicecomb2_1.utils;

import java.util.ArrayList;

/**
 * 此方法的作用是证明 java.lang.Object的hashcode 不是代表 对象所在内存地址。
 * 我产生了10000个对象，这10000个对象在内存中是不同的地址，但是实际上这10000个对象
 * 的hashcode的是完全可能相同的
 */
public class HashCodeMeaning {
  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    int numberExist = 0;

    //证明hashcode的值不是内存地址
    for (int i = 0; i < 10000; i++) {
      Object obj = new Object();
      if (list.contains(obj.toString())) {
        System.out.println(obj.toString() + "  exists in the list. " + i);
        numberExist++;
      } else {
        list.add(obj.toString());
      }
    }

    System.out.println("repetition number:" + numberExist);
    System.out.println("list size:" + list.size());

    //证明内存地址是不同的。
    numberExist = 0;
    list.clear();
    for (int i = 0; i < 10000; i++) {
      Object obj = new Object();
      if (list.contains(obj)) {
        System.out.println(obj + "  exists in the list. " + i);
        numberExist++;
      } else {
        list.add(obj);
      }
    }

    System.out.println("repetition number:" + numberExist);
    System.out.println("list size:" + list.size());
  }
}
