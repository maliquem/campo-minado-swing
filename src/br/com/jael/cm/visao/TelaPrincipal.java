package br.com.jael.cm.visao;

import javax.swing.JFrame;

import br.com.jael.cm.modelo.Tabuleiro;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(int linhas, int colunas, int bombas) {
        Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, bombas);
        add(new PainelTabuleiro(tabuleiro));

        setTitle("CAMPO MINADO");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
