package personalfinancetracker;
import java.time.LocalDate;
public abstract class Transaction{
 protected double amount; protected LocalDate date; protected String category;
 public Transaction(double amount,LocalDate date,String category)throws InvalidAmountException{
  if(amount<=0) throw new InvalidAmountException("Amount must be positive.");
  this.amount=amount; this.date=date; this.category=category;}
 public double getAmount(){ return amount;} public LocalDate getDate(){ return date;}
 public String getCategory(){ return category;}
 public abstract double calculateTax(); public abstract String getTransactionDetails();
}
