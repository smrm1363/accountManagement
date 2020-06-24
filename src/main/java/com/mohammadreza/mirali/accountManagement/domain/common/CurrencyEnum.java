package com.mohammadreza.mirali.accountManagement.domain.common;

public enum CurrencyEnum {
  EUR("EUR"),
  USD("USD"),
  SEK("SEK"),
  CAD("CAD"),
  GBP("GBP");
  private final String name;
  private CurrencyEnum(String s) {
    name=s;
  }
  public boolean equalsName(String otherName) {
    return name.equals(otherName);
  }
  public String toString() {
    return this.name;
  }
}
