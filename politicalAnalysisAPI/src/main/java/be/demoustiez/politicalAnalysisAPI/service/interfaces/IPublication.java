package be.demoustiez.politicalAnalysisAPI.service.interfaces;

import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Publication;

import java.time.LocalDate;
import java.util.Collection;

public interface IPublication {
    Collection<Publication> getPublicationsByCommission(Commission commission);
    Collection<Publication> getPublicationsByCommissionFromTo(Commission commission, LocalDate from, LocalDate to);
}
