package mylearning.servicecomb.utils;

import io.netty.buffer.ByteBufUtil;

public class NettyDecode {
  public static void decode() {
    String code1 = "";
    byte[] bytes = ByteBufUtil.decodeHexDump(code1);
    System.out.println(new String(bytes));
  }

  public static void main(String[] args) {
    NettyDecode.decode();
  }
}
