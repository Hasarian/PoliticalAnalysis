package be.demoustiez.politicalAnalysisAPI.util;

import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class Filter implements Predicate<Deputy> {
    private Collection<Predicate<Deputy>> filters;
    public Filter(){
        filters=new ArrayList<>();
    }
    public void addFilter(Predicate<Deputy> filter){
        filters.add(filter);
    }

    @Override
    public boolean test(Deputy deputy) {
        return filters.size()==0||this.filters.stream().allMatch(predicate -> predicate.test(deputy));
    }
}