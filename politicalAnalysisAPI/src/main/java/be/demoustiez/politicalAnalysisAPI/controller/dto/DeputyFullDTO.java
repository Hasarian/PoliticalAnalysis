package be.demoustiez.politicalAnalysisAPI.controller.dto;

import be.demoustiez.politicalAnalysisAPI.model.Deputy;

public class DeputyFullDTO extends DeputyDTO {
    private String group;
    private String circonscription;
    private String address;
    private String email;
    private String url;
    private String facebookURL;
    private String twitterURL;
    private boolean isGroupPres;
    public DeputyFullDTO(Deputy model) {
        super(model);
        this.group=model.getGroup();
        this.circonscription=model.getCirconscription();
        this.address=model.getAdressString();
        this.email=model.getEmail();
        this.url=model.getUrl();
        this.facebookURL=model.getFacebookURL();
        this.twitterURL=model.getTwitterURL();
        this.isGroupPres=model.isGroupPres();
    }

    public String getGroup() {
        return group;
    }

    public String getCirconscription() {
        return circonscription;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public String getFacebookURL() {
        return facebookURL;
    }

    public String getTwitterURL() {
        return twitterURL;
    }

    public boolean isGroupPres() {
        return isGroupPres;
    }
}
