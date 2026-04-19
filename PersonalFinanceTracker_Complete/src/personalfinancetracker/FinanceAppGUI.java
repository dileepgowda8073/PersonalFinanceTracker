package personalfinancetracker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.YearMonth;
import java.util.Map;

public class FinanceAppGUI extends JFrame {

    private final FinanceManager manager = new FinanceManager();
    private JTextField txtAmount, txtCategory;
    private JRadioButton rbIncome, rbExpense;
    private JTextArea txtOutput;
    private JLabel lblBalance;

    // Static block to set a nicer Look & Feel (Nimbus if available)
    static {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
            // If Nimbus is not available, default LAF will be used
        }
    }

    public FinanceAppGUI() {
        setTitle("💰 Personal Finance Tracker");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        initUI();
    }

    private void initUI() {
        // Main content panel with padding
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(new EmptyBorder(10, 10, 10, 10));
        main.setBackground(new Color(245, 248, 255));
        setContentPane(main);

        // ---------- TOP: TITLE + BALANCE ----------
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Personal Finance Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        lblBalance = new JLabel("Current Balance: 0.00");
        lblBalance.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBalance.setForeground(new Color(34, 139, 34)); // green

        header.add(title, BorderLayout.WEST);
        header.add(lblBalance, BorderLayout.EAST);

        main.add(header, BorderLayout.NORTH);

        // ---------- CENTER: LEFT (FORM) + RIGHT (OUTPUT) ----------
        JPanel center = new JPanel(new GridLayout(1, 2, 10, 0));
        center.setOpaque(false);
        main.add(center, BorderLayout.CENTER);

        // LEFT PANEL – ADD TRANSACTION
        JPanel formPanel = createFormPanel();
        center.add(formPanel);

        // RIGHT PANEL – REPORT / SUMMARY
        JPanel outputPanel = createOutputPanel();
        center.add(outputPanel);

        // ---------- BOTTOM: ACTION BUTTONS ----------
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        bottom.setOpaque(false);

        JButton btnSimulate = new JButton("Simulate Random Transactions");
        btnSimulate.addActionListener(this::simulate);
        JButton btnMonthly = new JButton("Show This Month Summary");
        btnMonthly.addActionListener(this::monthly);

        bottom.add(btnSimulate);
        bottom.add(btnMonthly);

        main.add(bottom, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 230)),
                "Add Transaction",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(40, 70, 110)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Amount label + field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Amount (₹):"), gbc);

        gbc.gridx = 1;
        txtAmount = new JTextField();
        panel.add(txtAmount, gbc);

        // Category label + field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Category:"), gbc);

        gbc.gridx = 1;
        txtCategory = new JTextField();
        panel.add(txtCategory, gbc);

        // Income / Expense radio buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Type:"), gbc);

        gbc.gridx = 1;
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        typePanel.setOpaque(false);
        rbIncome = new JRadioButton("Income", true);
        rbExpense = new JRadioButton("Expense");
        ButtonGroup group = new ButtonGroup();
        group.add(rbIncome);
        group.add(rbExpense);
        typePanel.add(rbIncome);
        typePanel.add(rbExpense);
        panel.add(typePanel, gbc);

        // Buttons row
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.setOpaque(false);
        JButton btnAdd = new JButton("Add Transaction");
        btnAdd.addActionListener(this::addTrans);
        JButton btnReport = new JButton("Show Full Report");
        btnReport.addActionListener(this::showReport);
        btnPanel.add(btnAdd);
        btnPanel.add(btnReport);

        panel.add(btnPanel, gbc);

        return panel;
    }

    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 230)),
                "Report / Summary",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(40, 70, 110)
        ));

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtOutput.setMargin(new Insets(5, 5, 5, 5));

        JScrollPane scroll = new JScrollPane(txtOutput);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // ===================== EVENT HANDLERS =====================

    private void addTrans(ActionEvent e) {
        try {
            double amt = Double.parseDouble(txtAmount.getText().trim());
            String cat = txtCategory.getText().trim();
            if (cat.isEmpty()) cat = "General";

            if (rbIncome.isSelected()) {
                manager.addIncome(amt, cat);
            } else {
                manager.addExpense(amt, cat);
            }

            updateBalanceLabel();

            JOptionPane.showMessageDialog(this, "Transaction added successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            txtAmount.setText("");
            txtCategory.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number for amount.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidAmountException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Invalid Amount", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReport(ActionEvent e) {
        txtOutput.setText(manager.generateReport(manager.getTransactions()));
    }

    private void simulate(ActionEvent e) {
        new Thread(new AccountUpdateTask(manager, true, "Salary")).start();
        new Thread(new AccountUpdateTask(manager, false, "Food")).start();
        new Thread(new AccountUpdateTask(manager, false, "Shopping")).start();

        updateBalanceLabel();

        JOptionPane.showMessageDialog(this,
                "Random transactions are being added in background.",
                "Simulation Started", JOptionPane.INFORMATION_MESSAGE);
    }

    private void monthly(ActionEvent e) {
        YearMonth m = YearMonth.now();
        Map<String, Double> s = manager.getMonthlySummary(m);

        double income = s.get("income");
        double expense = s.get("expense");

        StringBuilder sb = new StringBuilder();
        sb.append("Summary for ").append(m).append("\n");
        sb.append("-----------------------------\n");
        sb.append(String.format("Total Income : %.2f%n", income));
        sb.append(String.format("Total Expense: %.2f%n", expense));
        sb.append(String.format("Net Balance  : %.2f%n", income - expense));

        txtOutput.setText(sb.toString());
    }

    private void updateBalanceLabel() {
        double bal = manager.getBalance();
        lblBalance.setText(String.format("Current Balance: %.2f", bal));
        lblBalance.setForeground(bal >= 0 ? new Color(34, 139, 34)
                                          : new Color(178, 34, 34));
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinanceAppGUI gui = new FinanceAppGUI();
            gui.setVisible(true);
        });
    }
}
