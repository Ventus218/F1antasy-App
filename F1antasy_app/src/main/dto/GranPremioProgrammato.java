package main.dto;

import main.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GranPremioProgrammato {

    private Campionato campionato;
    private LocalDate dataGranPremio;
    private GranPremio granPremio;
    private Boolean concluso;

    public GranPremioProgrammato(Campionato campionato, LocalDate dataGranPremio, GranPremio granPremio, Boolean concluso) {
        this.campionato = campionato;
        this.dataGranPremio = dataGranPremio;
        this.granPremio = granPremio;
        this.concluso = concluso;
    }

    public Campionato getCampionato() {
        return campionato;
    }

    public LocalDate getDataGranPremio() {
        return dataGranPremio;
    }

    public GranPremio getGranPremio() {
        return granPremio;
    }

    public Boolean getConcluso() {
        return concluso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GranPremioProgrammato that = (GranPremioProgrammato) o;
        return Objects.equals(campionato, that.campionato) && Objects.equals(dataGranPremio, that.dataGranPremio) && Objects.equals(granPremio, that.granPremio) && Objects.equals(concluso, that.concluso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campionato, dataGranPremio, granPremio, concluso);
    }

    public static List<GranPremioProgrammato> getSample() {
        List<GranPremioProgrammato> list = new ArrayList();

        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), LocalDate.of(2021, 8, 21), GranPremio.getSample().get(0), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), LocalDate.of(2021, 8, 28), GranPremio.getSample().get(1), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), LocalDate.of(2021, 9, 5), GranPremio.getSample().get(2), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), LocalDate.of(2022, 8, 21), GranPremio.getSample().get(0), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), LocalDate.of(2022, 8, 28), GranPremio.getSample().get(1), false));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), LocalDate.of(2022, 9, 5), GranPremio.getSample().get(2), false));

        return list;
    }
}
