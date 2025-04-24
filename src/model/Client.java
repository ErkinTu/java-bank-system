package model;


public class Client {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String inn;
    private String phoneNumber;
    private String bankAccount;
    private double balance;

//    Constructor
    public Client(String firstName, String lastName, String dateOfBirth,
                  String inn, String phoneNumber, String bankAccount, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.inn = inn;
        this.phoneNumber = phoneNumber;
        this.bankAccount = bankAccount;
        this.balance = balance;
    }

//    Getters
    public double getBalance() {
        return balance;
    }
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getDateOfBirth() {return dateOfBirth;}
    public String getInn() {return inn;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getBankAccount() {return bankAccount;}

//    Methods
    public String[] toArray() {
        return new String[]{firstName, lastName, dateOfBirth, inn, phoneNumber, bankAccount, String.valueOf(balance)};
    }

    // Логика обработки баланса находиться в сервисе
    public void sendMoney(double amount) {
        balance -= amount;
    }

    public void receiveMoney(double amount) {
        balance += amount;
    }
}