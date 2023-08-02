import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {

    // Componentes
    private JTextField display;
    private double num1, num2, resultado;
    private String operador;

    public App() {
        // Configurações da janela principal
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        // Configuração do painel da calculadora
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Criação do display para mostrar os números e resultado
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 60));
        display.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(display, BorderLayout.NORTH);

        // Criação dos botões numéricos
        JPanel numericPanel = new JPanel();
        numericPanel.setLayout(new GridLayout(4, 3, 5, 5));
        String[] numeros = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "="};
        for (String numero : numeros) {
            JButton btn = new JButton(numero);
            numericPanel.add(btn);
            btn.addActionListener(new ButtonClickListener());
        }
        panel.add(numericPanel, BorderLayout.CENTER);

        // Criação dos botões de operações
        JPanel operatorPanel = new JPanel();
        operatorPanel.setLayout(new GridLayout(4, 1, 5, 5));
        String[] operacoes = {"/", "*", "-", "+"};
        for (String op : operacoes) {
            JButton btn = new JButton(op);
            operatorPanel.add(btn);
            btn.addActionListener(new ButtonClickListener());
        }
        panel.add(operatorPanel, BorderLayout.EAST);

        // Adiciona o painel à janela
        add(panel, BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
    }

    // Classe interna para tratar os eventos dos botões
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            // Verifica se o botão pressionado é um número ou um operador
            if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
                display.setText(display.getText() + command);
            } else if (command.charAt(0) == 'C') {
                // Limpa o display
                display.setText("");
            } else if (command.charAt(0) == '=') {
                // Calcula o resultado da operação
                num2 = Double.parseDouble(display.getText());
                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        resultado = num1 / num2;
                        break;
                }
                display.setText(Double.toString(resultado));
            } else {
                // O botão pressionado é um operador
                num1 = Double.parseDouble(display.getText());
                operador = command;
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new App();
        });
    }
}
