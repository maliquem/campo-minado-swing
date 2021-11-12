package br.com.jael.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador {

    private final int linhas;
    private final int colunas;
    private final int minas;
    private Random ran = new Random();

    private final List<Campo> campos = new ArrayList<>();

    private final List<Consumer<Boolean>> observadores = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void paraCada(Consumer<Campo> funcao) {
        campos.forEach(funcao);
    }

    public void registrarObservador(Consumer<Boolean> observador) {
        observadores.add(observador);
    }

    private void notificarObservadores(boolean resultado) {
        observadores.stream().forEach(o -> o.accept(resultado));
    }

    public void abrir(int linha, int coluna) {
        campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
                .ifPresent(Campo::abrir);
    }

    private void mostrarMinas() {
        campos.stream().filter(Campo::isMinado).filter(Campo::isMarcado).forEach(c -> c.setAberto(true));
    }

    public void alternarMarcacao(int linha, int coluna) {
        campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
                .ifPresent(Campo::alternarMarcacao);
    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Campo campo = new Campo(linha, coluna);
                campo.registrarObservadores(this);
                campos.add(campo);
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = Campo::isMinado;
        do {
            int aleatorio = ran.nextInt(campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
        } while (minasArmadas < minas);
    }

    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(Campo::objetivoAlcancado);
    }

    public void reiniciar() {
        campos.stream().forEach(Campo::reiniciar);
        sortearMinas();
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        if (evento == CampoEvento.EXPLODIR) {
            mostrarMinas();
            notificarObservadores(false);
        } else if (objetivoAlcancado()) {
            notificarObservadores(true);
        }
    }

    public int getLinhas() {
        return this.linhas;
    }

    public int getColunas() {
        return this.colunas;
    }

    public int getMinas() {
        return this.minas;
    }

}
