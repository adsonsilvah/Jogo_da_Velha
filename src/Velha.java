import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Velha {
    public static class JogoDaVelha extends JFrame implements ActionListener {
        private char jogadorAtual = 'X';
        private JButton[][] butoes = new JButton[3][3];
        private JLabel statusLabel = new JLabel("Vez do jogador: X");

        public JogoDaVelha() {
            setTitle("Jogo da Velha");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(300, 300);
            setResizable(false);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            for (int linhas = 0; linhas < 3; linhas++) {
                for (int colunas = 0; colunas < 3; colunas++) {
                    JButton button = new JButton(" ");
                    button.setFont(new Font("Arial", Font.PLAIN, 40));
                    button.addActionListener(this);
                    butoes[linhas][colunas] = button;

                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.gridx = colunas;
                    constraints.gridy = linhas;
                    constraints.fill = GridBagConstraints.BOTH;
                    constraints.insets = new Insets(0, 0, 0, 0);
                    panel.add(button, constraints);}}

            statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            statusLabel.setHorizontalAlignment(JLabel.CENTER);

            add(panel, BorderLayout.CENTER);
            add(statusLabel, BorderLayout.SOUTH);

            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            JButton butao = (JButton) e.getSource();
            int row = -1, col = -1;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (butoes[i][j] == butao) {
                        row = i;
                        col = j;
                        break;
                    }
                }
            }

            if (!butao.getText().equals(" ")) {
                return;
            }

            butao.setText(Character.toString(jogadorAtual));

            if (checarVitoria()) {
                statusLabel.setText("Jogador " + jogadorAtual + " venceu!");
                desativarButoes();
            }

            else if (checarEmpate()) {
                statusLabel.setText("Empate!");
                desativarButoes();
            }
            else {
                trocarJogador();
                statusLabel.setText("Vez do jogador: " + jogadorAtual);
            }
        }

        private boolean checarVitoria() {
            for (int Linhas = 0; Linhas < 3; Linhas++) {
                if (butoes[Linhas][0].getText().equals(butoes[Linhas][1].getText())
                        && butoes[Linhas][1].getText().equals(butoes[Linhas][2].getText())
                        && !butoes[Linhas][0].getText().equals(" ")) {
                    return true;
                }
            }

            for (int colunas = 0; colunas < 3; colunas++) {
                if (butoes[0][colunas].getText().equals(butoes[1][colunas].getText())
                        && butoes[1][colunas].getText().equals(butoes[2][colunas].getText())
                        && !butoes[0][colunas].getText().equals(" ")) {
                    return true;
                }
            }
            if (butoes[0][0].getText().equals(butoes[1][1].getText())
                    && butoes[1][1].getText().equals(butoes[2][2].getText())
                    && !butoes[0][0].getText().equals(" ")) {
                return true;
            }
            if (butoes[0][2].getText().equals(butoes[1][1].getText())
                    && butoes[1][1].getText().equals(butoes[2][0].getText())
                    && !butoes[0][2].getText().equals(" ")) {
                return true;
            }

            return false;
        }

        private boolean checarEmpate() {
            for (int linha = 0; linha < 3; linha++) {
                for (int coluna = 0; coluna < 3; coluna++) {
                    if (butoes[linha][coluna].getText().equals(" ")) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void desativarButoes() {
            for (int linha = 0; linha < 3; linha++) {
                for (int coluna = 0; coluna < 3; coluna++) {
                    butoes[linha][coluna].setEnabled(false);
                }
            }
        }

        private void trocarJogador() {
            if (jogadorAtual == 'X') {
                jogadorAtual = 'O';
            } else {
                jogadorAtual = 'X';
            }
        }
    }
}
