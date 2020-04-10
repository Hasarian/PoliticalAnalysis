package be.demoustiez.politicalAnalysisAPI.service.interfaces;

import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Event;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.model.Publication;

import java.util.Date;
import java.util.Collection;

public interface IPublicationService {
    Collection<Publication> getPublicationsByCommission(Commission commission);
    Collection<Publication> getPublicationsByCommissionFromTo(Commission commission, Date from, Date to);
    Collection<Publication> getPublicationsByLegislature(Legislature legislature);
    Collection<Publication> getPublicationsByLegislatureFromTo(Legislature legislature, Date from, Date to);
    Collection<Publication> getPublicationsByEvent(Event event);
    Publication getPublicationById(Integer id);
}
