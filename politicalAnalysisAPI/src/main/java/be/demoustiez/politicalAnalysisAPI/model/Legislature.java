package be.demoustiez.politicalAnalysisAPI.model;

import java.util.Collection;
import java.util.HashMap;

public class Legislature {
    private Integer id;
    private String name;
    private HashMap<Integer,Commission> commissions;
    private HashMap<Integer,Publication> publications;

    public Legislature(){
        this.commissions=new HashMap<>();
        this.publications=new HashMap<>();
    }

    public Collection<Publication> getPublications(){
        return this.publications.values();
    }
    public Collection<Commission> getCommissions(){
        return this.commissions.values();
    }
    public void addPublication(Publication publication){
        if(publication!=null) this.publications.putIfAbsent(publication.getId(),publication);
    }
    public void addCommission(Commission commission){
        if(commission!=null)
        this.commissions.putIfAbsent(commission.getId(),commission);
    }
    public void removePublication(Publication publication){
        this.publications.remove(publication.getId());
    }
    public void removeCommission(Commission commission){
        this.commissions.remove(commission.getId());
    }
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
}
