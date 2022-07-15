package main.dto;

import java.util.Objects;

public class UtenteInClassifica {
    private String username;
    private Integer punteggio;

    public UtenteInClassifica(String username, Integer punteggio) {
        this.username = username;
        this.punteggio = punteggio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtenteInClassifica that = (UtenteInClassifica) o;
        return Objects.equals(username, that.username) && Objects.equals(punteggio, that.punteggio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, punteggio);
    }
}
