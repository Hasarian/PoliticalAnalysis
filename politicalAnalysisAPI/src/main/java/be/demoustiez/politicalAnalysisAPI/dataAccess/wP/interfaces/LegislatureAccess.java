package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.model.Legislature;

import java.util.Collection;

public interface LegislatureAccess {
    Legislature getLegislatureByName(String name);
    Collection<Legislature> getLegislatures();
    Legislature getLegislatureById(Integer id);
    Legislature getCurrentLegislature();
}
