package com.mohammadreza.mirali.accountManagement.domain.account;

public enum TransactionTypeEnum {
  DEPOSIT("DEPOSIT"),
  WITHDRAWAL("WITHDRAWAL");
  private final String name;
  private TransactionTypeEnum(String s) {
    name=s;
  }
  public boolean equalsName(String otherName) {
    return name.equals(otherName);
  }
  public String toString() {
    return this.name;
  }
}
