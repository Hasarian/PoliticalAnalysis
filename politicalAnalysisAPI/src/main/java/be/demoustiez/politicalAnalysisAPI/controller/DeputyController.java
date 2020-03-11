package be.demoustiez.politicalAnalysisAPI.controller;

import be.demoustiez.politicalAnalysisAPI.controller.dto.DeputyDTO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.DeputeesDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.DeputyAccess;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class DeputyController {
    private DeputyAccess deputyAccess;

    @Autowired
    public DeputyController(DeputeesDAO dao){
        this.deputyAccess=dao;
    }

    @GetMapping("/deputees")
    public Collection<DeputyDTO> getDeputees(){
        Collection<Deputy> deputees=this.deputyAccess.getDeputies();
        ArrayList<DeputyDTO> dto=new ArrayList<>();
        deputees.forEach((deputy)->dto.add(new DeputyDTO(deputy)));
        return dto;
    }
}
