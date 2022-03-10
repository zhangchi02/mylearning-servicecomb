package mylearning.servicecomb2_1.utils;

import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.SSLServerSocketFactory;

public class SecurityListings {
  public static void main(String[] args) {
    sslCipher();
  }

  public static void sslCipher() {
    SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

    TreeMap<String, Boolean> ciphers = new TreeMap<>();
    for (String cipher : ssf.getSupportedCipherSuites()) {
      ciphers.put(cipher, Boolean.FALSE);
    }
    for (String cipher : ssf.getDefaultCipherSuites()) {
      ciphers.put(cipher, Boolean.TRUE);
    }

    System.out.println("Default Cipher");
    for (Map.Entry<String, Boolean> cipher : ciphers.entrySet()) {
      System.out.printf("   %-5s%s%n", (cipher.getValue() ? '*' : ' '), cipher.getKey());
    }
  }
}