package be.demoustiez.politicalAnalysisAPI.service;

import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Event;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IEventService;

import java.util.Date;
import java.util.Collection;

public class EventService implements IEventService {
    @Override
    public Collection<Event> getEvents(Date periodStart, Date periodEnd) {
        return null;
    }

    @Override
    public Collection<Event> getEventsByCommission(Commission commission, Date periodStart, Date periodEnd) {
        return null;
    }

    @Override
    public Event getEventById(Integer id) {
        return null;
    }
}
