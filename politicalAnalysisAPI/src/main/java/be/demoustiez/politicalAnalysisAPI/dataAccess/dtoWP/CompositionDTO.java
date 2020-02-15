package be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;

public class CompositionDTO {
    private Commission commission;
    private Member[] membres;

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public Member[] getMembres() {
        return membres;
    }

    public void setMembres(Member[] membres) {
        this.membres = membres;
    }

    public class Commission{
        private Integer nb_commissions;
        private Integer com_id;
        private String com_nom;
        private String com_nom_leg;
        private String com_leg;
        private Integer nb_effectifs;
        private Integer nb_suppleants;
        private boolean active;

        public boolean isActive() {
            return active;
        }
        public void setActive(Integer active){
            this.active= active==1;

        }

        public Integer getNb_commissions() {
            return nb_commissions;
        }

        public void setNb_commissions(Integer nb_commissions) {
            this.nb_commissions = nb_commissions;
        }

        public Integer getCom_id() {
            return com_id;
        }

        public void setCom_id(Integer com_id) {
            this.com_id = com_id;
        }

        public String getCom_nom() {
            return com_nom;
        }

        public void setCom_nom(String com_nom) {
            this.com_nom = com_nom;
        }

        public String getCom_nom_leg() {
            return com_nom_leg;
        }

        public void setCom_nom_leg(String com_nom_leg) {
            this.com_nom_leg = com_nom_leg;
        }

        public String getCom_leg() {
            return com_leg;
        }

        public void setCom_leg(String com_leg) {
            this.com_leg = com_leg;
        }

        public Integer getNb_effectifs() {
            return nb_effectifs;
        }

        public void setNb_effectifs(Integer nb_effectifs) {
            this.nb_effectifs = nb_effectifs;
        }

        public Integer getNb_suppleants() {
            return nb_suppleants;
        }

        public void setNb_suppleants(Integer nb_suppleants) {
            this.nb_suppleants = nb_suppleants;
        }
    }
    public class Member{
        private Integer dep_id;
        private String dep_nom;
        private String dep_prenom;
        private String dep_civ;
        private String dep_parti;
        private String dep_circonscription;
        private String dep_province;
        private String dep_emailb;
        private String dep_url;
        private SocialNetworkInfo dep_social_nt;
        private String dep_mandat;
        private boolean dep_membre_effectif;
        private boolean dep_membre_suppleant;
        private String dep_photo;


        public boolean isDep_membre_effectif() {
            return dep_membre_effectif;
        }
        public void setDep_membre_effectif(Integer membre_effectif){
            this.dep_membre_effectif = membre_effectif==1;
        }

        public boolean isDep_membre_suppleant() {
            return dep_membre_suppleant;
        }

        public void setDep_membre_suppleant(Integer dep_membre_suppleant) {
            this.dep_membre_suppleant = dep_membre_suppleant==1;
        }

        public String getDep_photo() {
            return dep_photo;
        }

        public void setDep_photo(String dep_photo) {
            this.dep_photo = dep_photo;
        }

        public String getDep_mandat() {
            return dep_mandat;
        }

        public void setDep_mandat(String dep_mandat) {
            this.dep_mandat = dep_mandat;
        }

        public SocialNetworkInfo getDep_social_nt() {
            return dep_social_nt;
        }

        public void setDep_social_nt(SocialNetworkInfo dep_social_nt) {
            this.dep_social_nt = dep_social_nt;
        }

        public String getDep_url() {
            return dep_url;
        }

        public void setDep_url(String dep_url) {
            this.dep_url = dep_url;
        }

        public String getDep_emailb() {
            return dep_emailb;
        }

        public void setDep_emailb(String dep_emailb) {
            this.dep_emailb = dep_emailb;
        }

        public String getDep_province() {
            return dep_province;
        }

        public void setDep_province(String dep_province) {
            this.dep_province = dep_province;
        }

        public String getDep_circonscription() {
            return dep_circonscription;
        }

        public void setDep_circonscription(String dep_circonscription) {
            this.dep_circonscription = dep_circonscription;
        }

        public String getDep_parti() {
            return dep_parti;
        }

        public void setDep_parti(String dep_parti) {
            this.dep_parti = dep_parti;
        }

        public String getDep_civ() {
            return dep_civ;
        }

        public void setDep_civ(String dep_civ) {
            this.dep_civ = dep_civ;
        }

        public String getDep_prenom() {
            return dep_prenom;
        }

        public void setDep_prenom(String dep_prenom) {
            this.dep_prenom = dep_prenom;
        }

        public String getDep_nom() {
            return dep_nom;
        }

        public void setDep_nom(String dep_nom) {
            this.dep_nom = dep_nom;
        }

        public Integer getDep_id() {
            return dep_id;
        }

        public void setDep_id(Integer dep_id) {
            this.dep_id = dep_id;
        }

        public class SocialNetworkInfo{
            private String dep_facebook;
            private String dep_twitter;
        }
    }
}
