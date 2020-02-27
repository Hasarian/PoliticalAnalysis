package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.*;
import be.demoustiez.politicalAnalysisAPI.model.Event;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.model.Session;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class App {
        public static void main(String args[]) throws Exception {
            ConfigurationLoader configurationLoader= new ConfigurationLoader();
            DeputeesDAO deputeesDAO = new DeputeesDAO(configurationLoader);
            LegislatureDAO legislatureDAO = new LegislatureDAO(configurationLoader);
            SessionDAO sessionDAO = new SessionDAO(configurationLoader);
            CommissionDAO commissionDAO= new CommissionDAO(configurationLoader,legislatureDAO,deputeesDAO);
            PublicationDAO publicationDAO = new PublicationDAO(configurationLoader,legislatureDAO,sessionDAO,commissionDAO);
            AgendaDAO agendaDAO = new AgendaDAO(configurationLoader,publicationDAO,commissionDAO);


           List<Event> events = agendaDAO.getEvents(legislatureDAO.getCurrentLegislature().getId().toString());

           events.forEach(event->System.out.println(eventDescription(event)));
            /*Collection<Legislature> legislatures=legislatureDAO.getLegislatures();
            Collection<Session> sessions=sessionDAO.getSessions();
            System.out.println("legislatures");
            legislatures.forEach(legislature -> System.out.println("\t"+legislature.getName()));
            System.out.println("sessions");
            sessions.forEach(session->System.out.println("\t"+session.getName()));*/
            System.out.println("end");
        }
        public static String eventDescription(Event event){
            StringBuilder builder = new StringBuilder();
            builder.append("event "+event.getId());
            builder.append("\n\tCommission name: "+event.getCommission().getName());
            builder.append("\n\tSubject: "+event.getSubject());
            builder.append("\n\tdate: "+event.getDate().get(Calendar.DAY_OF_MONTH)+
                            "/"+event.getDate().get(Calendar.MONTH)+
                            "/"+event.getDate().get(Calendar.YEAR));
            return builder.toString();
        }

}
