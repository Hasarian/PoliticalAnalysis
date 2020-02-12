package main.java.be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP;

public class PublicationDTO {
    private PublicationsInfo publications_infos;
    private Publication[] publications;

    public PublicationsInfo getPublications_infos() {
        return publications_infos;
    }

    public void setPublications_infos(PublicationsInfo publications_infos) {
        this.publications_infos = publications_infos;
    }

    public Publication[] getPublications() {
        return publications;
    }

    public void setPublications(Publication[] publications) {
        this.publications = publications;
    }

    private class PublicationsInfo{
        private Integer nb_pub;

        public Integer getNb_pub() {
            return nb_pub;
        }

        public void setNb_pub(Integer nb_pub) {
            this.nb_pub = nb_pub;
        }
    }
    private class Publication{
        private Integer PUB_ID;
        private String PUB_TYPE;
        private String PUB_TYPECOMPLET;
        private Integer PUB_NUMERO;
        private String PUB_REFERENCE;
        private String PUB_DATUM;
        private Integer PUB_ID_LEG;
        private Integer PUB_ID_SES;
        private Integer PUB_MOIS;
        private Integer PUB_ANNEE;
        private String PUB_TITRE;
        private String PUB_FICHIER;
        private String PUB_LIEN;
        private Integer AG_ID_COM;

        public Integer getPUB_ID() {
            return PUB_ID;
        }

        public void setPUB_ID(Integer PUB_ID) {
            this.PUB_ID = PUB_ID;
        }

        public String getPUB_TYPE() {
            return PUB_TYPE;
        }

        public void setPUB_TYPE(String PUB_TYPE) {
            this.PUB_TYPE = PUB_TYPE;
        }

        public String getPUB_TYPECOMPLET() {
            return PUB_TYPECOMPLET;
        }

        public void setPUB_TYPECOMPLET(String PUB_TYPECOMPLET) {
            this.PUB_TYPECOMPLET = PUB_TYPECOMPLET;
        }

        public Integer getPUB_NUMERO() {
            return PUB_NUMERO;
        }

        public void setPUB_NUMERO(Integer PUB_NUMERO) {
            this.PUB_NUMERO = PUB_NUMERO;
        }

        public String getPUB_REFERENCE() {
            return PUB_REFERENCE;
        }

        public void setPUB_REFERENCE(String PUB_REFERENCE) {
            this.PUB_REFERENCE = PUB_REFERENCE;
        }

        public String getPUB_DATUM() {
            return PUB_DATUM;
        }

        public void setPUB_DATUM(String PUB_DATUM) {
            this.PUB_DATUM = PUB_DATUM;
        }

        public Integer getPUB_ID_LEG() {
            return PUB_ID_LEG;
        }

        public void setPUB_ID_LEG(Integer PUB_ID_LEG) {
            this.PUB_ID_LEG = PUB_ID_LEG;
        }

        public Integer getPUB_ID_SES() {
            return PUB_ID_SES;
        }

        public void setPUB_ID_SES(Integer PUB_ID_SES) {
            this.PUB_ID_SES = PUB_ID_SES;
        }

        public Integer getPUB_MOIS() {
            return PUB_MOIS;
        }

        public void setPUB_MOIS(Integer PUB_MOIS) {
            this.PUB_MOIS = PUB_MOIS;
        }

        public Integer getPUB_ANNEE() {
            return PUB_ANNEE;
        }

        public void setPUB_ANNEE(Integer PUB_ANNEE) {
            this.PUB_ANNEE = PUB_ANNEE;
        }

        public String getPUB_TITRE() {
            return PUB_TITRE;
        }

        public void setPUB_TITRE(String PUB_TITRE) {
            this.PUB_TITRE = PUB_TITRE;
        }

        public String getPUB_FICHIER() {
            return PUB_FICHIER;
        }

        public void setPUB_FICHIER(String PUB_FICHIER) {
            this.PUB_FICHIER = PUB_FICHIER;
        }

        public String getPUB_LIEN() {
            return PUB_LIEN;
        }

        public void setPUB_LIEN(String PUB_LIEN) {
            this.PUB_LIEN = PUB_LIEN;
        }

        public Integer getAG_ID_COM() {
            return AG_ID_COM;
        }

        public void setAG_ID_COM(Integer AG_ID_COM) {
            this.AG_ID_COM = AG_ID_COM;
        }
    }
}
