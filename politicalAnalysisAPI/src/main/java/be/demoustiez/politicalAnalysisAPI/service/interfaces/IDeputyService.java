package be.demoustiez.politicalAnalysisAPI.service.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface IDeputyService {
    Collection<Deputy> getDeputees();
    Deputy getDeputyById(Integer id) throws ResourceNotFound;
    Collection<Deputy> getDeputeesByGroup(String groupName) throws ResourceNotFound;
    HashMap<String,List<Deputy>> getDeputeesOrderedByGroup() throws ResourceNotFound;
    Collection<Deputy> searchDeputyByName(String lastNameSearchTerm,String firstNameSearchTerm);
}
