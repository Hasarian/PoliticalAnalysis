package be.demoustiez.politicalAnalysisAPI.service;

import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.LegislatureDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.LegislatureAccess;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.ILegislatureService;
import org.springframework.beans.factory.annotation.Autowired;

public class LegislatureService implements ILegislatureService{
    private LegislatureAccess dao;

    @Autowired
    public LegislatureService(LegislatureDAO dao){
        this.dao=dao;
    }
    @Override
    public Legislature getLegislatureById(Integer id) {
        return null;
    }
}
