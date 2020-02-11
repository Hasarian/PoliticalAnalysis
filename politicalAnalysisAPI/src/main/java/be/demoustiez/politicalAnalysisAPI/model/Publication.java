package main.java.be.demoustiez.politicalAnalysisAPI.model;

import java.util.GregorianCalendar;

public class Publication {
    private Integer id;
    private String type;
    private String reference;
    private GregorianCalendar date;
    private Integer idLegislation;
    private Integer idSession;
    private String title;
    private String fileName;
    private String fileLink;
    private Integer idCommission;

    public void setDate(String dateDay, String hourMinute) {
        date = new GregorianCalendar();
        String[] dateElements = dateDay.split("/");
        String[] hourElement=hourMinute.split(":");
        date.set(Integer.parseInt(dateElements[2]),Integer.parseInt(dateElements[1]),Integer.parseInt(dateElements[0]),
                Integer.parseInt(hourElement[1]),Integer.parseInt(hourElement[0]));

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getIdLegislation() {
        return idLegislation;
    }

    public void setIdLegislation(Integer idLegislation) {
        this.idLegislation = idLegislation;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public Integer getIdCommission() {
        return idCommission;
    }

    public void setIdCommission(Integer idCommission) {
        this.idCommission = idCommission;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
