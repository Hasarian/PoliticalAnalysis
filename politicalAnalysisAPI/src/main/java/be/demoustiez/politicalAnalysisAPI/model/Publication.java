package main.java.be.demoustiez.politicalAnalysisAPI.model;

import javax.swing.*;
import java.util.GregorianCalendar;

public class Publication {
    private Integer id;
    private String type;
    private String reference;
    private GregorianCalendar date;
    private Legislature legislation;
    private Session session;
    private String title;
    private String fileName;
    private String fileLink;
    private Commission commission;

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

    public GregorianCalendar getDate() {
        return date;
    }

    public Legislature getLegislation() {
        return legislation;
    }

    public void setLegislation(Legislature legislation) {
        this.legislation = legislation;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }
}
