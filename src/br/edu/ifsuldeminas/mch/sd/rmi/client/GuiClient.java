package br.edu.ifsuldeminas.mch.sd.rmi.client;

import br.edu.ifsuldeminas.mch.sd.rmi.remote.Operations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class GuiClient extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
	private Operations calc;
    private JTextField display;
    
    private double num1 = 0;
    private String operacaoAtual = "";
    private boolean limparTelaProximoNumero = false;

    public GuiClient() {
        super("Calculadora RMI");

        try {
            calc = (Operations) Naming.lookup("rmi://localhost/CalculatorService");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar no RMI!");
            System.exit(0);
        }

        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 50));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(6, 4, 5, 5));
        painelBotoes.setBackground(Color.BLACK);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Color corNumero = new Color(51, 51, 51);
        Color corAcao = new Color(165, 165, 165);
        Color corOp = new Color(255, 149, 0);

        String[] textosBotoes = {
                "C", "n!", "Mod", "÷",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "%", "=",
                "√", "^", "Bin", "Hex"
        };

        for (String texto : textosBotoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.setFocusPainted(false);
            btn.addActionListener(this);

            if (texto.matches("[0-9]") || texto.equals(".")) {
                btn.setBackground(corNumero);
                btn.setForeground(Color.WHITE);
            } else if (texto.equals("+") || texto.equals("-") || texto.equals("x") || texto.equals("÷") || texto.equals("=")) {
                btn.setBackground(corOp);
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(corAcao);
                btn.setForeground(Color.BLACK);
            }
            painelBotoes.add(btn);
        }

        add(painelBotoes, BorderLayout.CENTER);

        setSize(360, 580);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.matches("[0-9]") || comando.equals(".")) {
                if (limparTelaProximoNumero) {
                    display.setText("");
                    limparTelaProximoNumero = false;
                }
                
                if (display.getText().equals("0") || display.getText().equals("Erro")) {
                    display.setText(comando);
                } else {
                    display.setText(display.getText() + comando);
                }
            } else if (comando.equals("C")) {
                display.setText("0");
                num1 = 0;
                operacaoAtual = "";
                limparTelaProximoNumero = false;
            } else if (comando.equals("n!") || comando.equals("Bin") || comando.equals("Hex")) {
                double valor = Double.parseDouble(display.getText());
                String resultado = "";
                
                if (comando.equals("n!")) resultado = String.valueOf(calc.factorial((int) valor));
                if (comando.equals("Bin")) resultado = calc.decToBin((int) valor);
                if (comando.equals("Hex")) resultado = calc.decToHex((int) valor);
                
                display.setText(resultado);
                limparTelaProximoNumero = true;
            } else if (comando.equals("=")) {
                double num2 = Double.parseDouble(display.getText());
                double res = 0;
                
                switch (operacaoAtual) {
                    case "+": res = (double) calc.sum(num1, num2); break;
                    case "-": res = (double) calc.sub(num1, num2); break;
                    case "x": res = (double) calc.mul(num1, num2); break;
                    case "÷": res = (double) calc.div(num1, num2); break;
                    case "Mod": res = (double) calc.modulo(num1, num2); break;
                    case "√": res = (double) calc.root(num1, num2); break; 
                    case "^": res = (double) calc.power(num1, num2); break;
                    case "%": res = (double) calc.percentage(num1, num2); break; 
                }
                
                if (Double.isNaN(res) || Double.isInfinite(res)) {
                    display.setText("Erro");
                } else if (res % 1 == 0) {
                    display.setText(String.valueOf((int) res));
                } else {
                    display.setText(String.valueOf(res));
                }
                
                operacaoAtual = "";
                limparTelaProximoNumero = true;
                
            } else {
                num1 = Double.parseDouble(display.getText());
                operacaoAtual = comando;
                limparTelaProximoNumero = true;
            }
        } catch (Exception ex) {
            display.setText("Erro");
            limparTelaProximoNumero = true;
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GuiClient();
    }
}