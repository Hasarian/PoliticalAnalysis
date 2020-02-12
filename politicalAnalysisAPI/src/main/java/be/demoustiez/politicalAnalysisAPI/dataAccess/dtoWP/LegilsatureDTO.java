package main.java.be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;

public class LegilsatureDTO {
    private Legislature[] legislatures;

    public Legislature[] getLegislatures() {
        return legislatures;
    }

    public void setLegislatures(Legislature[] legislatures) {
        this.legislatures = legislatures;
    }

    private class Legislature{
    private Integer leg_id;
    private Integer leg_nom;

        public Integer getLeg_id() {
            return leg_id;
        }

        public void setLeg_id(Integer leg_id) {
            this.leg_id = leg_id;
        }

        public Integer getLeg_nom() {
            return leg_nom;
        }

        public void setLeg_nom(Integer leg_nom) {
            this.leg_nom = leg_nom;
        }
    }
}
