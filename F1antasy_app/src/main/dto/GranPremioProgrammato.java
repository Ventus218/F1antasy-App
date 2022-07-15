package main.dto;

import main.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class GranPremioProgrammato {

    private Campionato campionato;
    private Date dataGranPremio;
    private GranPremio granPremio;
    private Boolean concluso;

    public GranPremioProgrammato(Campionato campionato, Date dataGranPremio, GranPremio granPremio, Boolean concluso) {
        this.campionato = campionato;
        this.dataGranPremio = dataGranPremio;
        this.granPremio = granPremio;
        this.concluso = concluso;
    }

    public Campionato getCampionato() {
        return campionato;
    }

    public Date getDataGranPremio() {
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

        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), Utils.dateFromStringDDMMYYYY("21/08/2021"), GranPremio.getSample().get(0), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), Utils.dateFromStringDDMMYYYY("28/08/2021"), GranPremio.getSample().get(1), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(0), Utils.dateFromStringDDMMYYYY("03/09/2021"), GranPremio.getSample().get(2), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), Utils.dateFromStringDDMMYYYY("21/08/2022"), GranPremio.getSample().get(0), true));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), Utils.dateFromStringDDMMYYYY("28/08/2022"), GranPremio.getSample().get(1), false));
        list.add(new GranPremioProgrammato(Campionato.getSample().get(1), Utils.dateFromStringDDMMYYYY("03/09/2022"), GranPremio.getSample().get(2), false));

        return list;
    }
}
