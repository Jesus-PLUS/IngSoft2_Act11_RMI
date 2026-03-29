import java.io.Serial;
import java.io.Serializable;
public class Cuenta implements Serializable {
    private String userName;
    private int userCount;
    private double balance;

    public Cuenta(String userName, int userCount, double balance){
        this.userName = userName;
        this.userCount = userCount;
        this.balance = balance;
    }
    // get user
    public String getUserName(){
        return userName;
    }

    // get balance
    public double getBalance(){
        return balance;
    }
    // set balance
    public void setBalance(double balance){
        this.balance = balance;
    }

    // get userCount
    public int getUserCount(){
        return userCount;
    }

}
