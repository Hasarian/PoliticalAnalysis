package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.model.Session;

import java.util.Collection;

public interface SessionAccess {
    Session sessionById(Integer id);
    Collection<Session> getSessions();
}