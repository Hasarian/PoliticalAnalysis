package be.demoustiez.politicalAnalysisAPI.service.interfaces;

import be.demoustiez.politicalAnalysisAPI.model.Legislature;

public interface ILegislatureService {
    Legislature getLegislatureById(Integer id);
}
