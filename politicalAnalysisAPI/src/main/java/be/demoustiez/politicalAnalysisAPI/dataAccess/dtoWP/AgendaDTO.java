package main.java.be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;

public class AgendaDTO {
    private AgendaInfo agenda_infos;
    private Event[] agenda;
    public AgendaInfo getAgendaInfo() {
        return agenda_infos;
    }

    public void setAgendaInfo(AgendaInfo agendaInfo) {
        this.agenda_infos = agendaInfo;
    }

    public Event[] getAgenda() {
        return agenda;
    }

    public void setAgenda(Event[] agenda) {
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
    private class Event{
        private Integer event_id;
        private Integer event_id_com;
        private String event_objet;
        private String event_sem;
        private String event_nomjour;
        private String event_date;
        private String event_heure_minute;
        private String event_podcast;
        private String event_count_pub_event;
        private Publication[] event_publications;

        public Publication[] getEvent_publications() {
            return event_publications;
        }

        public void setEvent_publications(Publication[] event_publications) {
            this.event_publications = event_publications;
        }

        public String getEvent_count_pub_event() {
            return event_count_pub_event;
        }

        public void setEvent_count_pub_event(String event_count_pub_event) {
            this.event_count_pub_event = event_count_pub_event;
        }

        public String getEvent_podcast() {
            return event_podcast;
        }

        public void setEvent_podcast(String event_podcast) {
            this.event_podcast = event_podcast;
        }

        public String getEvent_heure_minute() {
            return event_heure_minute;
        }

        public void setEvent_heure_minute(String event_heure_minute) {
            this.event_heure_minute = event_heure_minute;
        }

        public String getEvent_date() {
            return event_date;
        }

        public void setEvent_date(String event_date) {
            this.event_date = event_date;
        }

        public String getEvent_nomjour() {
            return event_nomjour;
        }

        public void setEvent_nomjour(String event_nomjour) {
            this.event_nomjour = event_nomjour;
        }

        public String getEvent_sem() {
            return event_sem;
        }

        public void setEvent_sem(String event_sem) {
            this.event_sem = event_sem;
        }

        public String getEvent_objet() {
            return event_objet;
        }

        public void setEvent_objet(String event_objet) {
            this.event_objet = event_objet;
        }

        public Integer getEvent_id_com() {
            return event_id_com;
        }

        public void setEvent_id_com(Integer event_id_com) {
            this.event_id_com = event_id_com;
        }

        public Integer getEvent_id() {
            return event_id;
        }

        public void setEvent_id(Integer event_id) {
            this.event_id = event_id;
        }

        private class Publication{
            private Integer pub_id;
            private String pub_type;
            private String pub_reference;
            private Integer pub_annee;
            private String pub_date;
            private String pub_fichier;
            private String pub_lien;

            public String getPub_lien() {
                return pub_lien;
            }

            public void setPub_lien(String pub_lien) {
                this.pub_lien = pub_lien;
            }

            public String getPub_fichier() {
                return pub_fichier;
            }

            public void setPub_fichier(String pub_fichier) {
                this.pub_fichier = pub_fichier;
            }

            public String getPub_date() {
                return pub_date;
            }

            public void setPub_date(String pub_date) {
                this.pub_date = pub_date;
            }

            public Integer getPub_annee() {
                return pub_annee;
            }

            public void setPub_annee(Integer pub_annee) {
                this.pub_annee = pub_annee;
            }

            public String getPub_reference() {
                return pub_reference;
            }

            public void setPub_reference(String pub_reference) {
                this.pub_reference = pub_reference;
            }

            public String getPub_type() {
                return pub_type;
            }

            public void setPub_type(String pub_type) {
                this.pub_type = pub_type;
            }

            public Integer getPub_id() {
                return pub_id;
            }

            public void setPub_id(Integer pub_id) {
                this.pub_id = pub_id;
            }
        }
    }
}
