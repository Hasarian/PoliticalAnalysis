package main.java.be.demoustiez.politicalAnalysisAPI.model.dto.WP;

public class AgendaWrapper {
    private AgendaInfo agenda_infos;
    private Agenda agenda;
    public AgendaInfo getAgendaInfo() {
        return agenda_infos;
    }

    public void setAgendaInfo(AgendaInfo agendaInfo) {
        this.agenda_infos = agendaInfo;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    private class AgendaInfo{
        private Integer nb_event;
        private Integer nb_pub;

        public Integer getNb_event() {
            return nb_event;
        }

        public AgendaInfo setNb_event(Integer nb_event) {
            this.nb_event = nb_event;
            return this;
        }

        public Integer getNb_pub() {
            return nb_pub;
        }

        public AgendaInfo setNb_pub(Integer nb_pub) {
            this.nb_pub = nb_pub;
            return this;
        }
    }
}
