package personalfinancetracker;
import java.time.LocalDate;
public class Income extends Transaction{
 public Income(double a,LocalDate d,String c)throws InvalidAmountException{ super(a,d,c);}
 public double calculateTax(){ return amount*0.10;}
 public String getTransactionDetails(){ return String.format("INCOME | %s | %s | %.2f | Tax: %.2f",date,category,amount,calculateTax());}
}
