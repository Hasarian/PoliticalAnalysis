package be.demoustiez.politicalAnalysisAPI.model;

import java.util.List;

public class Commission {
    private Integer id;
    private String name;
    private Legislature legislationName;
    private String type;
    private List<Deputy> compositionEffective;
    private Deputy president;
    private List<Deputy> vice_presidents;
    private List<Deputy> compositionSuppleant;

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

    public List<Deputy> getCompositionEffective() {
        return compositionEffective;
    }

    public void setCompositionEffective(List<Deputy> compositionEffective) {
        this.compositionEffective = compositionEffective;
    }

    public Deputy getPresident() {
        return president;
    }

    public void setPresident(Deputy president) {
        this.president = president;
    }

    public List<Deputy> getVice_presidents() {
        return vice_presidents;
    }

    public void setVice_presidents(List<Deputy> vice_presidents) {
        this.vice_presidents = vice_presidents;
    }

    public List<Deputy> getCompositionSuppleant() {
        return compositionSuppleant;
    }

    public void setCompositionSuppleant(List<Deputy> compositionSuppleant) {
        this.compositionSuppleant = compositionSuppleant;
    }
}
