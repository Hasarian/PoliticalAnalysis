package main.java.be.demoustiez.politicalAnalysisAPI.model;

import java.util.List;

public class Agenda {
    private List<Event> agenda;

    public List<Event> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Event> agenda) {
        this.agenda = agenda;
    }
}
