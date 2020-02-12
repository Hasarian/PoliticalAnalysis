package main.java.be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;

public class CommissionDTO {
    private CommissionsInfo commissions_infos;
    private Commission[] commissions;

    public CommissionsInfo getCommissions_infos() {
        return commissions_infos;
    }

    public void setCommissions_infos(CommissionsInfo commissions_infos) {
        this.commissions_infos = commissions_infos;
    }

    public Commission[] getCommissions() {
        return commissions;
    }

    public void setCommissions(Commission[] commissions) {
        this.commissions = commissions;
    }

    private class CommissionsInfo{
        private Integer nb_com;

        public Integer getNb_com() {
            return nb_com;
        }

        public void setNb_com(Integer nb_com) {
            this.nb_com = nb_com;
        }
    }
    private class Commission{
        private Integer com_id;
        private String com_nom;
        private String com_nom_leg;
        private String com_type;

        public String getCom_type() {
            return com_type;
        }

        public void setCom_type(String com_type) {
            this.com_type = com_type;
        }

        public String getCom_nom_leg() {
            return com_nom_leg;
        }

        public void setCom_nom_leg(String com_nom_leg) {
            this.com_nom_leg = com_nom_leg;
        }

        public String getCom_nom() {
            return com_nom;
        }

        public void setCom_nom(String com_nom) {
            this.com_nom = com_nom;
        }

        public Integer getCom_id() {
            return com_id;
        }

        public void setCom_id(Integer com_id) {
            this.com_id = com_id;
        }
    }
}
