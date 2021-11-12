package br.com.jael.cm.visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.*;

import br.com.jael.cm.modelo.Tabuleiro;

public class TelaPrincipal extends JFrame {

    private final JSplitPane splitPane; // split the window in top and bottom
    private final JPanel topPanel; // container panel for the top
    private final JPanel bottomPanel; // container panel for the bottom

    public TelaPrincipal(int linhas, int colunas, int bombas) {
        Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, bombas);
        splitPane = new JSplitPane();
        bottomPanel = new PainelTabuleiro(tabuleiro);
        topPanel = new TempoJogo();

        splitPane.setDividerLocation(50);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        setLayout(new GridLayout());
        add(splitPane);

        setTitle("CAMPO MINADO");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
