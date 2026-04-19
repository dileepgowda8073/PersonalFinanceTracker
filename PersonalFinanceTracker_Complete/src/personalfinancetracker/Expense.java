package personalfinancetracker;
import java.time.LocalDate;
public class Expense extends Transaction{
 public Expense(double a,LocalDate d,String c)throws InvalidAmountException{ super(a,d,c);}
 public double calculateTax(){ return 0.0;}
 public String getTransactionDetails(){ return String.format("EXPENSE | %s | %s | %.2f",date,category,amount);}
}
