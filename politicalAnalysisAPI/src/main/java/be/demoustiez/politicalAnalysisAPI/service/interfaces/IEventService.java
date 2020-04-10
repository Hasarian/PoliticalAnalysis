package be.demoustiez.politicalAnalysisAPI.service.interfaces;

import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Event;

import java.util.Date;
import java.util.Collection;

public interface IEventService {
    Collection<Event> getEvents(Date periodStart, Date periodEnd);
    Collection<Event> getEventsByCommission(Commission commission, Date periodStart,Date periodEnd);
    Event getEventById(Integer id);
}
