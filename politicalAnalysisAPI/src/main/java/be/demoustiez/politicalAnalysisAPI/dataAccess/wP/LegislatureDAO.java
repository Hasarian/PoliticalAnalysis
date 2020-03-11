package be.demoustiez.politicalAnalysisAPI.dataAccess.wP;

import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import be.demoustiez.politicalAnalysisAPI.dataAccess.UrlBuilder;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.LegislatureAccess;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP.LegilsatureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map.Entry;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class LegislatureDAO implements LegislatureAccess {

    private UrlBuilder<LegilsatureDTO> urlBuilder;
    private HashMap<Integer,Legislature> legislaturesCache;

    public LegislatureDAO(ConfigurationLoader config){

        this.urlBuilder=new UrlBuilder<>(config,"legislatures",LegilsatureDTO.class);
        loadLegislatures();
    }

    @Autowired
    private void loadLegislatures(){
        LegilsatureDTO dto = this.urlBuilder.sendRequest(new HashMap<>());
        this.legislaturesCache=new HashMap<>();
        for (LegilsatureDTO.Legislature legDTO:dto.getLegislatures()) {
            Legislature leg = new Legislature();
            leg.setId(legDTO.getLeg_id());
            leg.setName(legDTO.getLeg_nom());
            this.legislaturesCache.put(leg.getId(),leg);
        }
    }

    @Override
    public Legislature getLegislatureByName(String name) {
        Iterator iter = this.legislaturesCache.entrySet().iterator();
        Legislature currentLegislature=null;
        while (iter.hasNext()&&(currentLegislature!=null&&!currentLegislature.getName().equals(name))){
            Entry<Integer,Legislature> pair = (Entry<Integer, Legislature>) iter.next();
            currentLegislature= pair.getValue();
        }
        return (currentLegislature!=null&&currentLegislature.getName().equals(name))?currentLegislature:null;
    }

    @Override
    public Collection<Legislature> getLegislatures() {
        return this.legislaturesCache.values();
    }

    @Override
    public Legislature getLegislatureById(Integer id) {
        return this.legislaturesCache.get(id);
    }

    @Override
    public Legislature getCurrentLegislature() {
        Iterator it = this.legislaturesCache.entrySet().iterator();
        Entry<Integer,Legislature> currentLegislature=null;
        while  (it.hasNext()){
            Entry<Integer,Legislature> legislature = (Entry) it.next();
            if(currentLegislature==null||currentLegislature.getKey()<legislature.getKey()) currentLegislature=legislature;
        }
        return currentLegislature.getValue();
    }
}
