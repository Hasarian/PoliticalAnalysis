package be.demoustiez.politicalAnalysisAPI.model;

public class Commission {
    private Integer id;
    private String name;
    private Legislature legislationName;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Legislature getLegislationName() {
        return legislationName;
    }

    public void setLegislationName(Legislature legislationName) {
        this.legislationName = legislationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
