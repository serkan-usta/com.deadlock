public class Account {
    public int balance = 10000 ;

    public void withDraw (int amount) {
        balance -= amount ;
    }
    public void deposit(int amount){
        balance += amount ;
    }
    public static void transfer(Account account1,Account account2, int amount ){
        account1.withDraw(amount);
        account2.deposit(amount);
    }

    public int getBalance(){
        return balance;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }


}
