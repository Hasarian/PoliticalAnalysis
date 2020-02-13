package be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;


import be.demoustiez.politicalAnalysisAPI.configuration.Constants;

public class DeputyDTO {
    private DeputeesInfos deputes_infos;
    private Deputy[] deputes;

    public DeputeesInfos getDeputes_infos() {
        return deputes_infos;
    }

    public void setDeputes_infos(DeputeesInfos deputes_infos) {
        this.deputes_infos = deputes_infos;
    }

    public Deputy[] getDeputes() {
        return deputes;
    }

    public void setDeputes(Deputy[] deputes) {
        this.deputes = deputes;
    }

    public class DeputeesInfos{
        private Integer dep_count;

        public Integer getDep_count() {
            return dep_count;
        }

        public void setDep_count(Integer dep_count) {
            this.dep_count = dep_count;
        }
    }
    public class Deputy{
        private Integer dep_id;
        private String dep_nomcomplet;
        private String dep_nom;
        private String dep_prenom;
        private String dep_civ;
        private String dep_parti;
        private String dep_circons;
        private String dep_province;
        private Address dep_adresse_postale;
        private String dep_tel;
        private String dep_fax;
        private String dep_email;
        private String dep_url;
        private boolean dep_presidentgroupe;
        private boolean dep_membre_bureau;
        private String dep_fonction_bureau;
        private Integer dep_ordre_bureau;
        private boolean dep_membre_burau_elargi;
        private String dep_fonction_bureau_elargi;
        private Integer dep_ordre_bureau_elargi;
        private boolean dep_membre_conf_pres;
        private String dep_fonction_conf_pres;
        private Integer dep_ordre_conf_pres;
        private String dep_photo;
        private SocialNetworkInfo dep_social_nt;

        public boolean isDep_presidentgroupe() {
            return dep_presidentgroupe;
        }

        public void setDep_presidentgroupe(String dep_presidentgroupe) {
            this.dep_presidentgroupe = dep_presidentgroupe.equals(Constants.TRUE);
        }

        public boolean isDep_membre_bureau() {
            return dep_membre_bureau;
        }

        public void setDep_membre_bureau(String dep_membre_bureau) {
            this.dep_membre_bureau = dep_membre_bureau.equals(Constants.TRUE);
        }

        public boolean isDep_membre_burau_elargi() {
            return dep_membre_burau_elargi;
        }

        public void setDep_membre_burau_elargi(String dep_membre_burau_elargi) {
            this.dep_membre_burau_elargi = dep_membre_burau_elargi.equals(Constants.TRUE);
        }

        public boolean isDep_membre_conf_pres() {
            return dep_membre_conf_pres;
        }

        public void setDep_membre_conf_pres(String dep_membre_conf_pres) {
            this.dep_membre_conf_pres = dep_membre_conf_pres.equals(Constants.TRUE);
        }

        public Integer getDep_id() {
            return dep_id;
        }

        public void setDep_id(Integer dep_id) {
            this.dep_id = dep_id;
        }

        public String getDep_nomcomplet() {
            return dep_nomcomplet;
        }

        public void setDep_nomcomplet(String dep_nomcomplet) {
            this.dep_nomcomplet = dep_nomcomplet;
        }

        public String getDep_nom() {
            return dep_nom;
        }

        public void setDep_nom(String dep_nom) {
            this.dep_nom = dep_nom;
        }

        public String getDep_prenom() {
            return dep_prenom;
        }

        public void setDep_prenom(String dep_prenom) {
            this.dep_prenom = dep_prenom;
        }

        public String getDep_civ() {
            return dep_civ;
        }

        public void setDep_civ(String dep_civ) {
            this.dep_civ = dep_civ;
        }

        public String getDep_parti() {
            return dep_parti;
        }

        public void setDep_parti(String dep_parti) {
            this.dep_parti = dep_parti;
        }

        public String getDep_circons() {
            return dep_circons;
        }

        public void setDep_circons(String dep_circons) {
            this.dep_circons = dep_circons;
        }

        public String getDep_province() {
            return dep_province;
        }

        public void setDep_province(String dep_province) {
            this.dep_province = dep_province;
        }

        public Address getDep_adresse_postale() {
            return dep_adresse_postale;
        }

        public void setDep_adresse_postale(Address dep_adresse_postale) {
            this.dep_adresse_postale = dep_adresse_postale;
        }

        public String getDep_tel() {
            return dep_tel;
        }

        public void setDep_tel(String dep_tel) {
            this.dep_tel = dep_tel;
        }

        public String getDep_fax() {
            return dep_fax;
        }

        public void setDep_fax(String dep_fax) {
            this.dep_fax = dep_fax;
        }

        public String getDep_email() {
            return dep_email;
        }

        public void setDep_email(String dep_email) {
            this.dep_email = dep_email;
        }

        public String getDep_url() {
            return dep_url;
        }

        public void setDep_url(String dep_url) {
            this.dep_url = dep_url;
        }

        public String getDep_fonction_bureau() {
            return dep_fonction_bureau;
        }

        public void setDep_fonction_bureau(String dep_fonction_bureau) {
            this.dep_fonction_bureau = dep_fonction_bureau;
        }

        public Integer getDep_ordre_bureau() {
            return dep_ordre_bureau;
        }

        public void setDep_ordre_bureau(Integer dep_ordre_bureau) {
            this.dep_ordre_bureau = dep_ordre_bureau;
        }

        public String getDep_fonction_bureau_elargi() {
            return dep_fonction_bureau_elargi;
        }

        public void setDep_fonction_bureau_elargi(String dep_fonction_bureau_elargi) {
            this.dep_fonction_bureau_elargi = dep_fonction_bureau_elargi;
        }

        public Integer getDep_ordre_bureau_elargi() {
            return dep_ordre_bureau_elargi;
        }

        public void setDep_ordre_bureau_elargi(Integer dep_ordre_bureau_elargi) {
            this.dep_ordre_bureau_elargi = dep_ordre_bureau_elargi;
        }

        public String getDep_fonction_conf_pres() {
            return dep_fonction_conf_pres;
        }

        public void setDep_fonction_conf_pres(String dep_fonction_conf_pres) {
            this.dep_fonction_conf_pres = dep_fonction_conf_pres;
        }

        public Integer getDep_ordre_conf_pres() {
            return dep_ordre_conf_pres;
        }

        public void setDep_ordre_conf_pres(Integer dep_ordre_conf_pres) {
            this.dep_ordre_conf_pres = dep_ordre_conf_pres;
        }

        public String getDep_photo() {
            return dep_photo;
        }

        public void setDep_photo(String dep_photo) {
            this.dep_photo = dep_photo;
        }

        public SocialNetworkInfo getDep_social_nt() {
            return dep_social_nt;
        }

        public void setDep_social_nt(SocialNetworkInfo dep_social_nt) {
            this.dep_social_nt = dep_social_nt;
        }

        public class Address{
            private String dep_adresse;
            private String dep_cp;
            private String dep_localite;

            public String getDep_adresse() {
                return dep_adresse;
            }

            public void setDep_adresse(String dep_adresse) {
                this.dep_adresse = dep_adresse;
            }

            public String getDep_cp() {
                return dep_cp;
            }

            public void setDep_cp(String dep_cp) {
                this.dep_cp = dep_cp;
            }

            public String getDep_localite() {
                return dep_localite;
            }

            public void setDep_localite(String dep_localite) {
                this.dep_localite = dep_localite;
            }
        }
        public class SocialNetworkInfo{
            private String dep_facebook;
            private String dep_twitter;

            public String getDep_facebook() {
                return dep_facebook;
            }

            public void setDep_facebook(String dep_facebook) {
                this.dep_facebook = dep_facebook;
            }

            public String getDep_twitter() {
                return dep_twitter;
            }

            public void setDep_twitter(String dep_twitter) {
                this.dep_twitter = dep_twitter;
            }
        }
    }
}
