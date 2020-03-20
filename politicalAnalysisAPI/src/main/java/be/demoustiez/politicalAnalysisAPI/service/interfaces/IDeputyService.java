package be.demoustiez.politicalAnalysisAPI.service.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface IDeputyService {
    Collection<Deputy> getDeputies();
    Deputy getDeputyById(Integer id) throws ResourceNotFound;
    Collection<Deputy> getDeputiesByGroup(String groupName) throws ResourceNotFound;
    HashMap<String,List<Deputy>> getDeputiesOrderedByGroup();
    Collection<Deputy> searchDeputyByName(String lastNameSearchTerm,String firstNameSearchTerm);
}
