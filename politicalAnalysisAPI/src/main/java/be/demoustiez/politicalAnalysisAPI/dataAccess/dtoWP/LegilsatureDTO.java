package be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;

public class LegilsatureDTO {
    private Legislature[] legislatures;

    public Legislature[] getLegislatures() {
        return legislatures;
    }

    public void setLegislatures(Legislature[] legislatures) {
        this.legislatures = legislatures;
    }

    public class Legislature{
    private Integer leg_id;
    private String leg_nom;

        public Integer getLeg_id() {
            return leg_id;
        }

        public void setLeg_id(Integer leg_id) {
            this.leg_id = leg_id;
        }

        public String getLeg_nom() {
            return leg_nom;
        }

        public void setLeg_nom(String leg_nom) {
            this.leg_nom = leg_nom;
        }
    }
}
