package model;

public class Bank {
    private String name;
    private String code;
    private String address;
    private Double balance;

//    Constructor
    public Bank(String name, String code, String address, Double balance) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.balance = balance;
    }

//    Getters
    public String getName() {return name;}
    public String getCode() {
        return code;
    }
    public String getAddress() {
        return address;
    }
    public Double getBalance() {
        return balance;
    }

//    Setters
    public void setBalance(Double balance) {
        this.balance = balance;
    }

//    Methods
    public Object[] toArray() {
        return new Object[] { name, code, address, balance };
    }
}