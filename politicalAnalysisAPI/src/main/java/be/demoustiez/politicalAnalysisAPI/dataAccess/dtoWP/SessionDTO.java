package main.java.be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;

public class SessionDTO {
    private Session[] sessions;

    public Session[] getSessions() {
        return sessions;
    }

    public void setSessions(Session[] sessions) {
        this.sessions = sessions;
    }

    private class Session{
        private Integer ses_id;
        private String ses_nom;

        public Integer getSes_id() {
            return ses_id;
        }

        public void setSes_id(Integer ses_id) {
            this.ses_id = ses_id;
        }

        public String getSes_nom() {
            return ses_nom;
        }

        public void setSes_nom(String ses_nom) {
            this.ses_nom = ses_nom;
        }
    }
}
