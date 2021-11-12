package br.com.jael.cm.visao;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;

import br.com.jael.cm.modelo.Campo;
import br.com.jael.cm.modelo.CampoEvento;
import br.com.jael.cm.modelo.CampoObservador;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

    private final Color BG_PADRAO = new Color(150, 150, 150);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);

    private Campo campo;

    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        addMouseListener(this);
        campo.registrarObservadores(this);

    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento) {
        case ABRIR:
            aplicarEstiloAbrir();
            break;
        case MARCAR:
            aplicarEstiloMarcado();
            break;
        case EXPLODIR:
            aplicarEstiloExplodir();
            break;
        default:
            aplicarEstiloPadrao();
        }
    }

    private void aplicarEstiloExplodir() {
        setBackground(BG_EXPLODIR);
        setText("X");
    }

    private void aplicarEstiloPadrao() {
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
    }

    private void aplicarEstiloMarcado() {
        setBackground(BG_MARCAR);
        setText("M");
    }

    private void aplicarEstiloAbrir() {

        if (campo.isMinado() && !campo.isMarcado()) {
            setBackground(BG_EXPLODIR);
            return;
        }

        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        setBackground(BG_PADRAO);

        switch (campo.minasNaVizinhanca()) {
        case 1:
            setForeground(TEXTO_VERDE);
            break;
        case 2:
            setForeground(Color.BLUE);
            break;
        case 3:
            setForeground(Color.YELLOW);
            break;
        case 4:
        case 5:
        case 6:
            setForeground(Color.RED);
            break;
        default:
            setForeground(Color.PINK);
            break;
        }

        String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
        setText(valor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            campo.abrir();
        } else {
            campo.alternarMarcacao();
        }

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
