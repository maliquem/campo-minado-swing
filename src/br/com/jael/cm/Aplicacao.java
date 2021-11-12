package br.com.jael.cm;

import br.com.jael.cm.visao.TelaPrincipal;
import java.awt.*;

public class Aplicacao {

    public static void main(String[] args) {

        int linhas = 20;
        int colunas = 40;
        int bombas = 100;

        EventQueue.invokeLater(() -> new TelaPrincipal(linhas, colunas, bombas));

    }

}
