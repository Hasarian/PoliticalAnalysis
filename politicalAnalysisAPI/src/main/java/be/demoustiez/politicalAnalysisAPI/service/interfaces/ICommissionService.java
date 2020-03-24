package be.demoustiez.politicalAnalysisAPI.service.interfaces;

/*
    lite des commissions - choix législation
    composition d'une commission
    calendrier d'une commission - pagination
    publications liées à une commission (considéré - pagination)
*/

import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;

import java.util.Collection;

public interface ICommissionService {
    Collection<Commission> getCommissions(Legislature legislature);
}
