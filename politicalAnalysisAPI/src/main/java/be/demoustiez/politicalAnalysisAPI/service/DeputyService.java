package be.demoustiez.politicalAnalysisAPI.service;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.DeputeesDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.DeputyAccess;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IDeputyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DeputyService implements IDeputyService{
    private DeputyAccess access;

    @Autowired
    public DeputyService(DeputeesDAO dao){
        this.access=dao;
    }

    @Override
    public Collection<Deputy> getDeputies() {
        return this.access.getDeputies();
    }

    @Override
    public Deputy getDeputyById(Integer id) throws ResourceNotFound {
        Deputy deputy= this.access.getDeputyById(id);
        if(deputy==null) throw new ResourceNotFound("deputy");
        return deputy;
    }

    @Override
    public List<Deputy> getDeputiesByGroup(String groupName) throws ResourceNotFound {
        Collection<Deputy> deputies = this.access.getDeputies();
        List<Deputy> groupDeputies = deputies.stream().filter(deputy -> deputy.getGroup().equals(groupName)).collect(Collectors.toList());
        if(groupDeputies.size()==0) throw new ResourceNotFound("group name");
        return groupDeputies;
    }

    @Override
    public HashMap<String, List<Deputy>> getDeputiesOrderedByGroup(){
        Collection<Deputy> deputies = this.access.getDeputies();
        HashMap<String,List<Deputy>> orderedDeputies = new HashMap<>();
        deputies.forEach(deputy->{
            if(!orderedDeputies.containsKey(deputy.getGroup())){
                orderedDeputies.put(deputy.getGroup(),new ArrayList<>());
            }
            orderedDeputies.get(deputy.getGroup()).add(deputy);
        });
        return orderedDeputies;
    }

    @Override
    public List<Deputy> searchDeputyByName(String lastNameSearchTerm, String firstNameSearchTerm) {
        Collection<Deputy> deputies = this.access.getDeputies();
        NameFilter filter = new NameFilter();
        if(lastNameSearchTerm!=null)
            filter.addFilter(deputy-> deputy.getLastName().toLowerCase().contains(lastNameSearchTerm.toLowerCase()));
        if(firstNameSearchTerm!=null)
            filter.addFilter(deputy -> deputy.getFirstName().toLowerCase().contains(firstNameSearchTerm.toLowerCase()));
        return deputies.stream().filter(filter)
                .collect(Collectors.toList());
    }

    private class NameFilter implements Predicate<Deputy>{
        private Collection<Predicate<Deputy>> filters;
        public NameFilter(){
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
}
