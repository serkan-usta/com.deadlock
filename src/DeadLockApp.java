import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockApp {

    private Account account1 = new Account();
    private Account account2 = new Account();
    private Random random = new Random();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void LocksControl(Lock a,Lock b){
        boolean a_made = false ;
        boolean b_made = false ;

        while(true){
            // ReEnterantLock class cantain tryLock() and prevent deadlock ! 
            a_made = a.tryLock();
            b_made = b.tryLock();

            if(a_made == true && b_made == true){
                return;
            }
            if (a_made==true){
                a.unlock();
            }
            if (b_made == true){
                b.unlock();
            }
        }
    }

    public void thread1Function(){
        LocksControl(lock1,lock2);

        for (int i=0;i<5000;i++){
            Account.transfer(account1,account2, random.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }
    public void thread2Function(){

        LocksControl(lock2, lock1);

        for (int i = 0; i < 5000 ; i++) {

            Account.transfer(account2, account1, random.nextInt(100));

        }
        lock2.unlock();
        lock1.unlock();


    }
    public void threadOver(){
        System.out.println("Account1 Balance : " + account1.getBalance() + " Account2 Balance : " + account2.getBalance());

        System.out.println("Total Account Balance : " + (account1.getBalance() + account2.getBalance() ));

    }


}
