package com.zhc.service;

public class MockLocalServiceCenter {
  public static void main(String[] args) {
    System.setProperty("local.registry.file", "notExistJustForceLocal");

  }
}
