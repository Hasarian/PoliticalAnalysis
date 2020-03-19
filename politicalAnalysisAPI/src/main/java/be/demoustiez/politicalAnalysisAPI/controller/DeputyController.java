package be.demoustiez.politicalAnalysisAPI.controller;

import be.demoustiez.politicalAnalysisAPI.controller.dto.DeputyDTO;
import be.demoustiez.politicalAnalysisAPI.controller.dto.DeputyFullDTO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.DeputeesDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.DeputyAccess;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

/*
*
*
    tous les députés
    un député selon son id
    les députés membres d'un groupe
    tous les députés triés par groupe

*   recherche selon le nom
* */
@RestController
@RequestMapping("/deputees")
public class DeputyController {
    private DeputyAccess deputyAccess;

    @Autowired
    public DeputyController(DeputeesDAO dao){
        this.deputyAccess=dao;
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
        return null;
    }
}
