package br.com.jael.cm.modelo;

@FunctionalInterface
public interface CampoObservador {

    public void eventoOcorreu(Campo campo, CampoEvento evento);

}
