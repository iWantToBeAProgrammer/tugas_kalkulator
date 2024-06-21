import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private JButton[] digitButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private double firstNumber;
    private String operator;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].addActionListener(this);
            buttonPanel.add(digitButtons[i]);
        }

        operationButtons = new JButton[5];
        String[] operators = {"+", "-", "*", "/", "%"};
        for (int i = 0; i < 5; i++) {
            operationButtons[i] = new JButton(operators[i]);
            operationButtons[i].addActionListener(this);
            buttonPanel.add(operationButtons[i]);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.matches("[0-9]")) {
            String currentText = displayField.getText();
            displayField.setText(currentText + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/") || command.equals("%")) {
            firstNumber = Double.parseDouble(displayField.getText());
            operator = command;
            displayField.setText("");
        } else if (command.equals("=")) {
            double secondNumber = Double.parseDouble(displayField.getText());
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = firstNumber / secondNumber;
                    break;
                case "%":
                    result = firstNumber % secondNumber;
                    break;
            }
            displayField.setText(String.valueOf(result));
        } else if (command.equals("C")) {
            displayField.setText("");
        }
    }

    public static void main(String[] args) {
        // Memulai aplikasi Swing dalam EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}