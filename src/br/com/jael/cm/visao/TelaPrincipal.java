package br.com.jael.cm.visao;

import javax.swing.JFrame;

import br.com.jael.cm.modelo.Tabuleiro;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        Tabuleiro tabuleiro = new Tabuleiro(16, 30, 5);
        add(new PainelTabuleiro(tabuleiro));

        setTitle("CAMPO MINADO");
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }

}
