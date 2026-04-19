package personalfinancetracker;
import java.time.YearMonth; import java.util.*;
public class FinanceManager implements Reportable{
 private final List<Transaction> transactions=Collections.synchronizedList(new ArrayList<>());
 public void addIncome(double a,String c)throws InvalidAmountException{ transactions.add(new Income(a,java.time.LocalDate.now(),c));}
 public void addExpense(double a,String c)throws InvalidAmountException{ transactions.add(new Expense(a,java.time.LocalDate.now(),c));}
 public List<Transaction> getTransactions(){return transactions;}
 public double getBalance(){ double inc=0,exp=0;
  synchronized(transactions){ for(Transaction t:transactions){ if(t instanceof Income)inc+=t.getAmount(); else exp+=t.getAmount();}}
  return inc-exp;}
 public HashMap<String,Double> getMonthlySummary(YearMonth m){
  HashMap<String,Double>s=new HashMap<>(); s.put("income",0.0); s.put("expense",0.0);
  synchronized(transactions){ for(Transaction t:transactions){ if(YearMonth.from(t.getDate()).equals(m)){
    if(t instanceof Income)s.put("income",s.get("income")+t.getAmount());
    else s.put("expense",s.get("expense")+t.getAmount()); }}} return s;}
 public String generateReport(List<Transaction> list){
  StringBuilder sb=new StringBuilder("==== REPORT ====\n");
  synchronized(list){ for(Transaction t:list) sb.append(t.getTransactionDetails()).append("\n");}
  sb.append("Balance: ").append(getBalance());
  return sb.toString();}
}
