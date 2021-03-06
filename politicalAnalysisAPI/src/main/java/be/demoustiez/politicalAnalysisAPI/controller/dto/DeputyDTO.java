package be.demoustiez.politicalAnalysisAPI.controller.dto;

import be.demoustiez.politicalAnalysisAPI.model.Deputy;

public class DeputyDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String pictureURL;

    public DeputyDTO(Deputy model){
        this.id=model.getId();
        this.firstName=model.getFirstName();
        this.lastName=model.getLastName();
        this.pictureURL=model.getPictureURL();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPictureURL() {
        return pictureURL;
    }
}
