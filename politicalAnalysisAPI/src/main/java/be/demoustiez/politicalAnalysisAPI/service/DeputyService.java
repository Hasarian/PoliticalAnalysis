package be.demoustiez.politicalAnalysisAPI.service;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.DeputeesDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.DeputyAccess;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IDeputyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeputyService implements IDeputyService{
    private DeputyAccess access;

    @Autowired
    public DeputyService(DeputeesDAO dao){
        this.access=dao;
    }

    @Override
    public Collection<Deputy> getDeputees() {
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
    public HashMap<String, List<Deputy>> getDeputeesOrderedByGroup(){
        return null;
    }

    @Override
    public List<Deputy> searchDeputyByName(String lastNameSearchTerm, String firstNameSearchTerm) {
        return null;
    }
}
