package be.demoustiez.politicalAnalysisAPI.dataAccess.wP;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import be.demoustiez.politicalAnalysisAPI.dataAccess.UrlBuilder;
import be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP.AgendaDTO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.AgendaAccess;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.CommissionAccess;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.PublicationAccess;
import be.demoustiez.politicalAnalysisAPI.model.*;

import java.util.*;

public class AgendaDAO implements AgendaAccess {
    private static final String CODE_URL="agenda";

    private static final String CAL_TAG="cal";
    private static final String LEG_TAG="idleg";
    private static final String SESSION_TAG="idses";
    private static final String YEAR_TAG="annee";
    private static final String MONTH_TAG="mois";
    private static final String TYPE_TAG="type";
    private static final String EVENT_TAG="idag";
    private static final String DATE_TAG="date";
    private static final String COMMISSION_TAG="idcom";

    private static final String CAL_VALUE="recherche";


    private UrlBuilder<AgendaDTO> urlBuilder;
    private PublicationAccess publicationAccess;
    private CommissionAccess commissionAccess;

    public AgendaDAO(ConfigurationLoader configuration,PublicationDAO publicationDAO,CommissionDAO commissionDAO)  {
        this.urlBuilder= new UrlBuilder(configuration,CODE_URL,AgendaDTO.class);
        this.publicationAccess=publicationDAO;
        this.commissionAccess=commissionDAO;
    }

    public List<Event> getEvents(String legislation) throws ArgumentError{
        return this.getEvents(legislation,null,null,null,null,null,null,null);
    }
    public List<Event> getEvents(String legilsation,String session,String year, String month,String typeCode, String event,
                                 String date,String commission)throws ArgumentError{
        HashMap<String,String> args=new HashMap<>();
        args.put(CAL_TAG,CAL_VALUE);
        if(legilsation==null) throw new ArgumentError("legislation id","is mandatory");
        args.put(LEG_TAG,legilsation);
        if(!(session==null||session.isEmpty())) args.put(SESSION_TAG,session);
        if(!(year==null||year.isEmpty())) args.put(YEAR_TAG,year);
        if(!(month==null||month.isEmpty())) args.put(MONTH_TAG,year);
        if(!(typeCode==null||typeCode.isEmpty())) args.put(TYPE_TAG,typeCode);
        if(!(event==null||event.isEmpty())) args.put(EVENT_TAG,event);
        if(!(date==null||date.isEmpty())) args.put(DATE_TAG,date);
        if(!(commission==null||commission.isEmpty())) args.put(COMMISSION_TAG,commission);

        AgendaDTO dto = this.urlBuilder.sendRequest(args);
        return makeEventsFromDTO(dto);

    }
    private List<Event> makeEventsFromDTO(AgendaDTO dto) throws ArgumentError{
        List<Event> events = new ArrayList<>();
        for (AgendaDTO.Event eventDTO: dto.getAgenda()) {
            Event event = new Event();
            event.setDate(eventDTO.getEvent_date(),eventDTO.getEvent_heure_minute());
            event.setEventPodcast(eventDTO.getEvent_podcast());
            event.setId(eventDTO.getEvent_id());
            event.setSubject(eventDTO.getEvent_objet());

            event.setCommission(commissionAccess.getCommission(eventDTO.getEvent_id_com()));
            event.setEventPublications(this.getPublications(eventDTO.getEvent_publications()));

            events.add(event);
        }
        return events;
    }
    private List<Publication> getPublications(AgendaDTO.Event.Publication[] publicationDTOs) throws ArgumentError{
        List<Integer> ids = new ArrayList<>();
        for (AgendaDTO.Event.Publication pubDTOs:publicationDTOs) {
            ids.add(pubDTOs.getPub_id());
        }
        return this.publicationAccess.getPublicationsByIds(ids);
    }

}
