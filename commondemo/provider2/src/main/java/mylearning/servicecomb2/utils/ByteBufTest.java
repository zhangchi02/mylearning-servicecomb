package mylearning.servicecomb2.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {
  public static void main(String[] args) {
    ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(12, 100);
    System.out.println("buffer.hashCode():" + buffer.hashCode());
    System.out.println("buffer:" + buffer);
    // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写
    buffer.writeBytes(new byte[] {1, 2, 3, 4, 5, 6, 7});
    print("writeBytes(1,2,3,4,5,6,7)", buffer);

    ByteBuf buffer2 = buffer.slice();
    System.out.println("buffer2.hashCode():" + buffer2.hashCode());
    System.out.println("buffer2:" + buffer2);
    print("buffer.slice()", buffer2);

    ByteBuf buffer3 = buffer.duplicate();
    System.out.println("buffer3.hashCode():" + buffer3.hashCode());
    System.out.println("buffer3:" + buffer3);
    print("buffer.duplicate()", buffer3);

    System.out.println("buffer.equals(buffer2):" + buffer.equals(buffer2));
    System.out.println("buffer.equals(buffer3):" + buffer.equals(buffer3));
    System.out.println("buffer2.equals(buffer3):" + buffer2.equals(buffer3));

//    buffer2.writeBytes(new byte[]{8});
//    print("buffer2.writeBytes(new byte[]{8})", buffer);
//    print("buffer2.writeBytes(new byte[]{8})", buffer2);
//    print("buffer2.writeBytes(new byte[]{8})", buffer3);

    buffer3.writeBytes(new byte[] {9});
    print("buffer3.writeBytes(new byte[]{9})", buffer);
    print("buffer3.writeBytes(new byte[]{9})", buffer2);
    print("buffer3.writeBytes(new byte[]{9})", buffer3);
  }

  private static void print(String action, ByteBuf buffer) {
    System.out.println("after ===========" + action + "============");
    System.out.println("capacity(): " + buffer.capacity());
    System.out.println("maxCapacity(): " + buffer.maxCapacity());
    System.out.println("readerIndex(): " + buffer.readerIndex());
    System.out.println("readableBytes(): " + buffer.readableBytes());
    System.out.println("isReadable(): " + buffer.isReadable());
    System.out.println("writerIndex(): " + buffer.writerIndex());
    System.out.println("writableBytes(): " + buffer.writableBytes());
    System.out.println("isWritable(): " + buffer.isWritable());
    System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
    System.out.println();
  }
}
