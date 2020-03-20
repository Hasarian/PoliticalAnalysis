package be.demoustiez.politicalAnalysisAPI.controller;

import be.demoustiez.politicalAnalysisAPI.controller.dto.DeputyDTO;
import be.demoustiez.politicalAnalysisAPI.controller.dto.DeputyFullDTO;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;
import be.demoustiez.politicalAnalysisAPI.service.DeputyService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IDeputyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deputies")
public class DeputyController {
    private IDeputyService deputyAccess;

    @Autowired
    public DeputyController(DeputyService service){
        this.deputyAccess=service;
    }

    @GetMapping("/all")
    public Collection<DeputyDTO> getDeputees(){
        Collection<Deputy> deputees=this.deputyAccess.getDeputies();
        ArrayList<DeputyDTO> dto=new ArrayList<>();
        deputees.forEach((deputy)->dto.add(new DeputyDTO(deputy)));
        return dto;
    }

    @GetMapping("/{id}")
    public DeputyFullDTO getDeputy(@PathVariable Integer id){
        Deputy deputy = this.deputyAccess.getDeputyById(id);
        return new DeputyFullDTO(deputy);
    }
    /*
    * les députés membres d'un groupe*/
    @GetMapping("/group/{name}")
    public Collection<DeputyFullDTO> getDeputiesFromGroup(@PathVariable String name){
         Collection<Deputy> deputies=this.deputyAccess.getDeputiesByGroup(name);
         return deputies.stream().map(DeputyFullDTO::new).collect(Collectors.toList());
    }

/*tous les députés triés par groupe*/
    @GetMapping("/groupsComposition")
    public HashMap<String,List<DeputyDTO>> getDeputiesOrderedBuGroup(){
        HashMap<String,List<Deputy>> orderedDeputies=this.deputyAccess.getDeputiesOrderedByGroup();
        HashMap<String,List<DeputyDTO>> dto=new HashMap<>();
        orderedDeputies.forEach((groupName,deputies)->dto.put(groupName,this.generateDeputyDTOListFromModelList(deputies)));
        return dto;
    }
    private List<DeputyDTO> generateDeputyDTOListFromModelList(List<Deputy> models){
        return models.stream().map(DeputyDTO::new).collect(Collectors.toList());
    }
/*recherche selon le nom
    * */
    @GetMapping("/search")
    public Collection<DeputyDTO> searchDeputies(@RequestParam(required = false) String lastNameTerm,
                                                @RequestParam(required = false) String firstNameTerm){
        return this.deputyAccess.searchDeputyByName(lastNameTerm,firstNameTerm).stream().map(DeputyDTO::new)
                .collect(Collectors.toList());
    }
}
