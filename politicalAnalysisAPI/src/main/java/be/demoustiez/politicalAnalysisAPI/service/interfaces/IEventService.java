package be.demoustiez.politicalAnalysisAPI.service.interfaces;

import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Event;

import java.time.LocalDate;
import java.util.Collection;

public interface IEventService {
    Collection<Event> getEvents(LocalDate periodStart, LocalDate periodEnd);
    Collection<Event> getEventsByCommission(Commission commission, LocalDate periodStart,LocalDate periodEnd);
}
