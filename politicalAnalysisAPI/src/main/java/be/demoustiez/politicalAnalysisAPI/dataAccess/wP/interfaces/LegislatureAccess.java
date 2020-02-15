package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;

import java.util.List;

public interface LegislatureAccess {
    Legislature getLegislatureByYear(Integer year);
    List<Legislature> getLegislatures();
}
