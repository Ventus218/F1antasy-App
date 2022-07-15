package main.components;

import main.dto.UtenteInClassifica;

import java.util.Objects;

public class UtenteInClassificaConPosizionamento {

    private UtenteInClassifica utenteInClassifica;
    private Integer posizionamento;

    public UtenteInClassificaConPosizionamento(UtenteInClassifica utenteInClassifica, Integer posizionamento) {
        this.utenteInClassifica = utenteInClassifica;
        this.posizionamento = posizionamento;
    }

    public UtenteInClassifica getUtenteInClassifica() {
        return utenteInClassifica;
    }

    public Integer getPosizionamento() {
        return posizionamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtenteInClassificaConPosizionamento that = (UtenteInClassificaConPosizionamento) o;
        return Objects.equals(utenteInClassifica, that.utenteInClassifica) && Objects.equals(posizionamento, that.posizionamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utenteInClassifica, posizionamento);
    }
}
