package br.com.jael.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.jael.cm.modelo.Tabuleiro;

public class PainelTabuleiro extends JPanel {

    public PainelTabuleiro(Tabuleiro tabuleiro) {

        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCada(c -> add(new BotaoCampo(c)));

        tabuleiro.registrarObservador(e -> {

            SwingUtilities.invokeLater(() -> {
                if (Boolean.TRUE.equals(e)) {
                    JOptionPane.showMessageDialog(this, "GANHOU!!");
                } else {
                    JOptionPane.showMessageDialog(this, "PERDEU!!");
                }

                tabuleiro.reiniciar();
            });
        });

    }

}
