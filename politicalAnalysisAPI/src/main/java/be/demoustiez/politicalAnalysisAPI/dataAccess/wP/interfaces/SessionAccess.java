package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.model.Session;

import java.util.List;

public interface SessionAccess {
    Session sessionByYear(Integer year);
    List<Session> getSessions();
}