package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class TelaGuiBilhete extends JFrame {
    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
    private VooMediator vooMediator = VooMediator.obterInstancia();

    public TelaGuiBilhete() {
        setTitle("Tela GUI Bilhete");
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        JPanel panel = new JPanel();

        // Radio buttons bilhete normal (marcado por padrão) e bilhete vip
        JRadioButton radioButton1 = new JRadioButton("Bilhete Normal");
        JRadioButton radioButton2 = new JRadioButton("Bilhete Vip");
        radioButton1.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);

        // Caixa de texto para CPF
        JLabel labelCPF = new JLabel("CPF:");
        JTextField textFieldCPF = new JTextField(11);

        // Dropdown companhias aéreas
        JLabel labelCompanhia = new JLabel("Companhia Aérea:");
        String[] companhiasAereas = {"AB", "CD", "EF", "GH"};
        JComboBox<String> comboBoxCompanhia = new JComboBox<>(companhiasAereas);

        // Dropdown número do voo
        JLabel labelNumeroVoo = new JLabel("Número do voo:");
        String[] numeroVoos = {"0001", "0002", "0003", "0004", "0005", "0006", "0007", "0008", "0009", "0010"};
        JComboBox<String> comboBoxNumeroVoo = new JComboBox<>(numeroVoos);

        // Caixa de texto para preço
        JLabel labelPreco = new JLabel("Preço R$:");
        JTextField textFieldPreco = new JTextField(11);

        // Caixa de texto para pogamento em ponto
        JLabel labelPagamentoEmPonto = new JLabel("Pagamento em pontos:");
        JTextField textFieldPagamentoEmPonto = new JTextField(11);

        // Caixa de texto para data e hora
        JLabel labelDataHora = new JLabel("Data e Hora (DD/MM/YYYY HH:MM):");
        JTextField textFieldDataHora = new JTextField(16); // Tamanho para acomodar o formato completo
        textFieldDataHora.setToolTipText("Formato: DD/MM/YYYY HH:MM");
        panel.add(labelDataHora);
        panel.add(textFieldDataHora);

        // Caixa de texto para bônus pontuação
        JLabel labelBonusPontuacao = new JLabel("Bônus Pontuação:");
        JTextField textFieldBonusPontuacao = new JTextField(11);
        textFieldBonusPontuacao.setEnabled(false);
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldBonusPontuacao.setEnabled(false); // Desabilitar se Bilhete Normal estiver selecionado
                textFieldBonusPontuacao.setText(""); // Limpar o texto
            }
        });

        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldBonusPontuacao.setEnabled(true); // Habilitar se Bilhete VIP estiver selecionado
            }
        });

        // Botão para gerar o bilhete
        JButton buttonGerarBilhete = new JButton("Gerar Bilhete");
        buttonGerarBilhete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter os valores dos componentes da GUI
                String cpf = textFieldCPF.getText();
                String companhiaAerea = (String) comboBoxCompanhia.getSelectedItem();
                int numeroVoo = Integer.parseInt((String) Objects.requireNonNull(comboBoxNumeroVoo.getSelectedItem()));
                double preco = Double.parseDouble(textFieldPreco.getText());
                double pagamentoEmPonto = Double.parseDouble(textFieldPagamentoEmPonto.getText());
                String dataHoraStr = textFieldDataHora.getText();
                int bonusPontuacao = Integer.parseInt(textFieldBonusPontuacao.getText()); // Valor padrão, caso não seja informado

                // Verificar se o bilhete é VIP para obter o valor do bônus de pontuação
                if (radioButton2.isSelected()) {
                    bonusPontuacao = Integer.parseInt(textFieldBonusPontuacao.getText());
                }

                // Converter a string dataHora para LocalDateTime
                LocalDateTime dataHora = null;
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    dataHora = LocalDateTime.parse(dataHoraStr, formatter);
                } catch (DateTimeParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao converter data e hora.");
                    return; // Retorna caso ocorra um erro na conversão
                }

                // Chamada da função para gerar o bilhete, passando os valores coletados
                if (radioButton1.isSelected()) {
                    // Lógica para gerar bilhete normal
                    BilheteMediator bilheteMediator = BilheteMediator.obterInstancia();
                    String mensagem = String.valueOf(bilheteMediator.gerarBilhete(cpf, companhiaAerea, numeroVoo, preco, pagamentoEmPonto, dataHora));

                    if (mensagem == null) {
                        JOptionPane.showMessageDialog(null, "Bilhete gerado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, mensagem);
                    }

                } else if (radioButton2.isSelected()) {
                    // Lógica para gerar bilhete VIP
                    BilheteMediator bilheteMediator = BilheteMediator.obterInstancia();
                    String mensagem = String.valueOf(bilheteMediator.gerarBilheteVip(cpf, companhiaAerea, numeroVoo, preco, pagamentoEmPonto, dataHora, bonusPontuacao));

                    if (mensagem == null) {
                        JOptionPane.showMessageDialog(null, "Bilhete gerado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, mensagem);
                    }
                }
            }
        });

        // Botão para limpar os campos da tela
        JButton buttonLimpar = new JButton("Limpar");
        buttonLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpar todos os campos e redefinir para os valores iniciais
                textFieldCPF.setText("");
                comboBoxCompanhia.setSelectedIndex(0); // Selecionar o primeiro item por padrão
                comboBoxNumeroVoo.setSelectedIndex(0); // Selecionar o primeiro item por padrão
                textFieldPreco.setText("");
                textFieldPagamentoEmPonto.setText("");
                textFieldDataHora.setText("");
                textFieldBonusPontuacao.setText("");
                radioButton1.setSelected(true); // Selecionar Bilhete Normal por padrão
                textFieldBonusPontuacao.setEnabled(false); // Desabilitar a caixa de texto de bônus de pontuação
            }
        });

        // Adicionando os elementos no panel
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(labelCPF);
        panel.add(textFieldCPF);
        panel.add(labelCompanhia);
        panel.add(comboBoxCompanhia);
        panel.add(labelNumeroVoo);
        panel.add(comboBoxNumeroVoo);
        panel.add(labelPreco);
        panel.add(textFieldPreco);
        panel.add(labelPagamentoEmPonto);
        panel.add(textFieldPagamentoEmPonto);
        panel.add(labelDataHora);
        panel.add(textFieldDataHora);
        panel.add(labelBonusPontuacao);
        panel.add(textFieldBonusPontuacao);
        panel.add(buttonGerarBilhete);
        panel.add(buttonLimpar);

        // Adicionando o panel na janela
        add(panel);
    }

    public static void main(String[] args) {
        // Criando a tela GUI e tornando-a visível
        SwingUtilities.invokeLater(TelaGuiBilhete::new);
    }
}