package personalfinancetracker;
public class AccountUpdateTask implements Runnable{
 private final FinanceManager m; private final boolean inc; private final String cat;
 public AccountUpdateTask(FinanceManager m,boolean inc,String cat){this.m=m;this.inc=inc;this.cat=cat;}
 public void run(){ for(int i=0;i<5;i++){ try{
   double amt=100+Math.random()*900;
   if(inc)m.addIncome(amt,cat); else m.addExpense(amt,cat);
   Thread.sleep(200);} catch(Exception e){e.printStackTrace();}}}
}
