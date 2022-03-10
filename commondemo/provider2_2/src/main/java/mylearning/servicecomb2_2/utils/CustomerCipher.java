package mylearning.servicecomb2_2.utils;

import org.apache.servicecomb.foundation.auth.Cipher;


public class CustomerCipher implements Cipher {
  @Override
  public String name() {
    return "custom";
  }

  @Override
  public char[] decrypt(char[] encrypted) {
    System.out.println("-----custom cipher------");
    return encrypted;
  }
}
