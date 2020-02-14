package com.company.samsara.box;

import org.apache.commons.lang3.StringUtils;

class Valuable {
  int id;
  String name;
  int size;

  public Valuable(int id, String name, int size) {
    if (StringUtils.isBlank(name)) {
      throw new IllegalArgumentException("Valuabe cannot be blank");
    }
    if (size <= 0) {
      throw new IllegalArgumentException("Valuabe size cannot be smaller than or equal to 0");
    }
    this.id = id;
    this.name = name;
    this.size = size;
  }

  @Override
  public String toString() {
    return "[id = " + id + ", name = " + name + ", size = " + size + "]";
  }
}
