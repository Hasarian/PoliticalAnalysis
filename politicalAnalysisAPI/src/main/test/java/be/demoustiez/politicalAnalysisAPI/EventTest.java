package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.Errors.InvalidParameter;
import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.AgendaDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.CommissionDAO;
import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Event;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.service.EventService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IEventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class EventTest {

    private IEventService eventService;
    @Mock
    private AgendaDAO dao;
    @Mock
    private CommissionDAO comDAO;
    private HashMap<Integer,Event> mocks;
    private Collection<Legislature> mockLegs;
    private Collection<Commission> mockCommissions;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        eventService=new EventService();
        initMocks();
            mockLegs.forEach((mockLeg) -> Mockito.when(dao.getEvents(mockLeg.getName()))
                    .thenReturn(mocks.values().stream().filter(
                            mock -> mock.getCommission().getLegislation().getId() == mockLeg.getId()
                            ).collect(Collectors.toList())
                    )
            );
        mockCommissions.forEach(mockCom->Mockito.when(comDAO.getCommission(mockCom.getId())).thenReturn(mockCom));
        Mockito.when(comDAO.getCommissions()).thenReturn(mockCommissions);

    }

    public void initMocks(){
        mockLegs=new ArrayList<>();
        for (int i=0;i<5;i++){
            Legislature leg=new Legislature();
            leg.setId(i+1);
            leg.setName(i+"that");
            mockLegs.add(leg);
        }
        mockCommissions=new ArrayList<>();
        mockLegs.forEach(mockleg->{
            for(int i=0;i<5;i++){
                Commission commission=new Commission();
                commission.setLegislation(mockleg);
                commission.setId(mockleg.getId()*10+i);
                mockCommissions.add(commission);
            }
        });
        mocks = new HashMap<>();
        mockCommissions.forEach(mockCom->{
            String startDate="06/04/2020/";
            String startHour="10:00";
            for (int i=0;i<2+mockCom.getLegislation().getId();i++){
                Event event=new Event();
                event.setId(mockCom.getId()*10+i);
                try {
                    event.setDate(startDate,startHour);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                event.setCommission(mockCom);
                mocks.put(event.getId(),event);
            }
        });
    }

    @Test
    public void getEvents_startDateInvalid_exception(){
        try {
            Date invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("30/2/2020");
            Assert.assertThrows(InvalidParameter.class,()->{
                        this.eventService.getEvents(invalidDate, new Date());
                    }
            );
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new InvalidParameter("bad parse");
        }
    }
    @Test(expected = InvalidParameter.class)
    public void getEvents_endDateInvalid_exception(){
        Date invalidDate;
        try {
            invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("30/2/2020");
            Assert.assertThrows(InvalidParameter.class,()->{
                        this.eventService.getEvents(new Date(), invalidDate);
                    }
            );
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new InvalidParameter("bad parse");
        }
    }

    @Test(expected = InvalidParameter.class)
    public void getEventsByCommission_startDateInvalidException(){
        Commission commission = mockCommissions.stream().findAny().get();
        Date invalidDate;
        try {
            invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("30/2/2020");
            Assert.assertThrows(InvalidParameter.class,()->{
                        this.eventService.getEventsByCommission(commission,invalidDate,new Date());
                    }
            );
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new InvalidParameter("bad parse");
        }
    }
    @Test(expected = InvalidParameter.class)
    public void getEventsByCommission_endDateInvalidException(){
        Commission commission = mockCommissions.stream().findAny().get();
        Date invalidDate;
        try {
            invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/2/2020");
            Assert.assertThrows(InvalidParameter.class,()->{
                        this.eventService.getEventsByCommission(commission,new Date(), invalidDate);
                    }
            );
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new InvalidParameter("bad parse");
        }
    }

    @Test(expected = ResourceNotFound.class)
    public void getEventByCommission_notFound(){
        Commission com = new Commission();
        com.setId(this.mockCommissions.size()+10);
        this.eventService.getEventsByCommission(com,null,null);
    }
    @Test
    public void getEventStartDate_null_shouldReturnSomething(){
        Date today= new Date();
        Assert.assertNotEquals(null,this.eventService.getEvents(null,today));
    }
    @Test
    public void getEventEndDate_null_shouldReturnSomething(){
        Date today=new Date();
        Assert.assertNotEquals(null,this.eventService.getEvents(today,null));
    }
    @Test
    public void getEventByCommissionStartDate_null_noError() {
        Commission com = mockCommissions.stream().findAny().get();
        Date today= new Date();
        Assert.assertNotEquals(null,this.eventService.getEventsByCommission(com,null,today));
    }
    @Test
    public void getEventByCommissionEndDate_null_noError(){
        Commission com = mockCommissions.stream().findAny().get();
        Date today=new Date();
        Assert.assertNotEquals(null,this.eventService.getEventsByCommission(com,today,null));
    }
    @Test
    public void getEventByCommission_correctCommission(){
        Commission com = mockCommissions.stream().findAny().get();
        Collection<Event> events = this.eventService.getEventsByCommission(com,null,null);
        events.forEach(event->Assert.assertEquals(event.getCommission().getId(),com.getId()));
    }
    @Test
    public void getEventByCommission_allEventsByCommission(){
        Commission com = mockCommissions.stream().findAny().get();
        Collection<Event> events = this.eventService.getEventsByCommission(com,null,null);
        Assert.assertEquals(events.size(),
                mocks.values().stream().filter(
                        mock->mock.getCommission().getId()==com.getId()
                ).collect(Collectors.toList()).size()
        );
    }
}
