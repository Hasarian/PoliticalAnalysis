package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.model.Event;

import java.text.ParseException;
import java.util.List;

public interface AgendaAccess {
    List<Event> getEvents(String legislation) throws ArgumentError, ParseException;
    List<Event> getEvents(String legilsation,String session,String year, String month,String typeCode, String event,
                          String date,String commission)throws ArgumentError, ParseException;
}
