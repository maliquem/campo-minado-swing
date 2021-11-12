package br.com.jael.cm;

import br.com.jael.cm.visao.TelaPrincipal;
import br.com.jael.cm.visao.TelaSelecao;

public class Aplicacao {

    public static void main(String[] args) {

        int linhas = 20;
        int colunas = 40;
        int bombas = 100;

        new TelaSelecao();

        new TelaPrincipal(linhas, colunas, bombas);

    }

}
