package main.dto;

public class UtenteInClassifica {
    private String username;
    private Integer posizionamento;
    private Integer punteggio;

    public UtenteInClassifica(String username, Integer posizionamento, Integer punteggio) {
        this.username = username;
        this.posizionamento = posizionamento;
        this.punteggio = punteggio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPosizionamento() {
        return posizionamento;
    }

    public void setPosizionamento(Integer posizionamento) {
        this.posizionamento = posizionamento;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }
}
