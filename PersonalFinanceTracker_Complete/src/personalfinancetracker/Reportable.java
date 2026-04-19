package personalfinancetracker;
import java.util.List;
public interface Reportable { String generateReport(List<Transaction> transactions); }
