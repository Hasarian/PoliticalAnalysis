package be.demoustiez.politicalAnalysisAPI.dataAccess.wP;

import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import be.demoustiez.politicalAnalysisAPI.dataAccess.UrlBuilder;
import be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP.SessionDTO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.SessionAccess;
import be.demoustiez.politicalAnalysisAPI.model.Session;

import java.util.Collection;
import java.util.HashMap;

public class SessionDAO implements SessionAccess {

    private UrlBuilder<SessionDTO> urlBuilder;
    private HashMap<Integer,Session> sessionCache;
    public SessionDAO(ConfigurationLoader config){
        this.urlBuilder=new UrlBuilder<>(config,"sessions");
    }

    private void loadCache(){
        this.sessionCache=new HashMap<>();
        SessionDTO dto = this.urlBuilder.sendRequest(new HashMap<>());
        for (SessionDTO.Session sessiondto :dto.getSessions()) {
            Session session=fromDTOToSession(sessiondto);
            this.sessionCache.put(session.getId(),session);
        }
    }
    @Override
    public Session sessionById(Integer id) {
        return this.sessionCache.get(id);
    }

    @Override
    public Collection<Session> getSessions() {
        return this.sessionCache.values();
    }
    private Session fromDTOToSession(SessionDTO.Session dto){
        Session session= new Session();
        session.setId(dto.getSes_id());
        session.setName(dto.getSes_nom());
        return session;
    }
}
