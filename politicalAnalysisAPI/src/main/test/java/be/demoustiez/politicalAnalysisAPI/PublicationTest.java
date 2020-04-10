package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.Errors.InvalidParameter;
import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.AgendaDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.CommissionDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.LegislatureDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.PublicationDAO;
import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Event;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.model.Publication;
import be.demoustiez.politicalAnalysisAPI.service.PublicationService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IPublicationService;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PublicationTest {
    private IPublicationService publicationService;
    @Mock
    private PublicationDAO dao;
    @Mock
    private CommissionDAO comDao;
    @Mock
    private AgendaDAO agendaDAO;
    @Mock
    private LegislatureDAO legislatureDAO;

    private HashMap<Integer,Publication> mockpublication;
    private HashMap<Integer,Legislature>  mockLeg;
    private HashMap<Integer,Commission> mockCommission;
    private HashMap<Integer,Event> mockEvents;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        publicationService = new PublicationService();
        initMocks();
    }

    private void initMocks(){
        initEventMocks();
        mockpublication=new HashMap<>();
        mockCommission.forEach((id,com)->{
            for (int i=0;i<5;i++){
                Publication publication = new Publication();
                publication.setCommission(com);
                publication.setLegislation(com.getLegislation());
                publication.setId(com.getId()*10+i);
                publication.setDate(i+1+"04/2020");
                mockpublication.put(publication.getId(),publication);
                Mockito.when(dao.getPublicationById(publication.getId())).thenReturn(publication);
                Mockito.when(dao.getPublicaitonById(publication.getId(),com.getLegislation())).thenReturn(publication);
            }
        });
        mockLeg.forEach((id,leg)->Mockito.when(dao.getPublications(leg)).thenReturn(mockpublication.values().stream().filter(
                pub-> pub.getCommission().getLegislation().getId()==leg.getId()
        ).collect(Collectors.toList())));


    }
    private void initLegMocks(){
        mockLeg=new HashMap<>();
        for (int i=0;i<5;i++){
            Legislature leg = new Legislature();
            leg.setId(i);
            leg.setName(i+"");
            mockLeg.put(leg.getId(),leg);
            Mockito.when(legislatureDAO.getLegislatureById(leg.getId())).thenReturn(leg);
            Mockito.when(legislatureDAO.getLegislatureByName(leg.getName())).thenReturn(leg);
        }
        Mockito.when(legislatureDAO.getLegislatures()).thenReturn(mockLeg.values());
        Mockito.when(legislatureDAO.getCurrentLegislature()).thenReturn(mockLeg.values().stream().findFirst().get());
    }
    private void initComMocks(){
        initLegMocks();
        mockCommission= new HashMap<>();
        mockLeg.forEach((id,leg)->{
            for(int i=0;i<5;i++){
                Commission com = new Commission();
                com.setLegislation(leg);
                com.setId(id*10+i);
                mockCommission.put(com.getId(),com);
                Mockito.when(comDao.getCommission(com.getId())).thenReturn(com);
            }
        });
        Mockito.when(comDao.getCommissions()).thenReturn(mockCommission.values());

    }
    private void initEventMocks(){
        initComMocks();
        mockEvents=new HashMap<>();
        mockCommission.forEach((id,com)->{
            for (int i=0;i<5;i++){
                Event event = new Event();
                event.setCommission(com);
                event.setId(com.getId()*10+i);
                mockEvents.put(event.getId(),event);
            }
        });
        mockLeg.forEach((id,leg)->Mockito.when(agendaDAO.getEvents(leg.getName())).thenReturn(
                mockEvents.values().stream().filter(event->event.getCommission().getLegislation().getId()==id)
                        .collect(Collectors.toList())
        ));
    }

    //    Collection<Publication> getPublicationsByCommission(Commission commission);
    @Test(expected = ResourceNotFound.class)
    public void getPublicationByCommission_commissionNotFound(){
        Commission com = new Commission();
        com.setId(6);
        this.publicationService.getPublicationsByCommission(com);
    }
    @Test
    public void getPublicationByCommission_allPublicationsHaveCommission(){
        mockCommission.values().forEach(com->{
            Collection<Publication> publications = publicationService.getPublicationsByCommission(com);
            publications.forEach(publication -> Assert.assertEquals(publication.getCommission().getId(),com.getId()));
        });
    }
    @Test
    public void getPublicationByCommission_returnedAllPublicationFromCommission(){
        mockCommission.forEach((id,com)->{
            Collection<Publication> publications = publicationService.getPublicationsByCommission(com);
            int expectedSize=mockpublication.values().stream().filter(pub->
                    pub.getCommission().getId()==id)
                    .collect(Collectors.toList())
                    .size();
            Assert.assertEquals(expectedSize,publications.size());
        });
    }
    //    Collection<Publication> getPublicationsByCommissionFromTo(Commission commission, Date from, Date to);
    @Test (expected = ResourceNotFound.class)
    public void getPublicationByCommissionFromTo_CommissionNotFound(){
        Commission com = new Commission();
        com.setId(6);
        this.publicationService.getPublicationsByCommissionFromTo(com,new Date(),new Date());
    }
     @Test
    public void getPublicationByCommissionFromTo_invalidStartDate(){
         Commission commission = mockCommission.values().stream().findAny().get();
         Date invalidDate;
         try {
             invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("30/2/2020");
             Assert.assertThrows(InvalidParameter.class,()->{
                         this.publicationService.getPublicationsByCommissionFromTo(commission,invalidDate,new Date());
                     }
             );
         } catch (ParseException e) {
             e.printStackTrace();
             throw  new InvalidParameter("bad parse");
         }
    }
    @Test
    public void getPublicationByCommissionFromTo_invalidEndDate(){
        Commission commission = mockCommission.values().stream().findAny().get();
        Date invalidDate;
        try {
            invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/2/2020");
            Assert.assertThrows(InvalidParameter.class,()->{
                        this.publicationService.getPublicationsByCommissionFromTo(commission,new Date(),invalidDate);
                    }
            );
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new InvalidParameter("bad parse");
        }
    }
    @Test
    public void getPublicationByCommissionFromTo_startDateNullShouldReturnSomething(){
        Commission commission = mockCommission.values().stream().findAny().get();
        Assert.assertNotEquals(null,
                this.publicationService.getPublicationsByCommissionFromTo(commission,null,new Date()));
    }
    @Test
    public void getPublicationByCommissionFromTo_endDateNullShouldReturnSomething(){
        Commission commission = mockCommission.values().stream().findAny().get();
        Assert.assertNotEquals(null,
                this.publicationService.getPublicationsByCommissionFromTo(commission,new Date(),null));
    }
    //    Collection<Publication> getPublicationsByLegislature(Legislature legislature);
    @Test(expected = ResourceNotFound.class)
    public void getPublicationByLegislature_legislature_notFound(){
        Legislature invalidLegislature = new Legislature();
        invalidLegislature.setId(6);
        publicationService.getPublicationsByLegislature(invalidLegislature);
    }
    @Test
    public void getPublicationByLegislature_allPublicationsHaveCorrectLegislature(){
        mockLeg.forEach((id,leg)->{
            Collection<Publication> publications = publicationService.getPublicationsByLegislature(leg);
            publications.forEach(pub->Assert.assertEquals(id,pub.getCommission().getLegislation().getId()));
        });
    }
    @Test
    public void getPublicationsByLegislature_returnedAllAskedPublications(){
        mockLeg.forEach((id,leg)->{
            Collection<Publication> publications= publicationService.getPublicationsByLegislature(leg);
            int expectedSize = mockpublication.values().stream().filter(
                    publication -> publication.getCommission().getLegislation().getId()==id
            ).collect(Collectors.toList()).size();
            Assert.assertEquals(expectedSize,publications.size());
        });
    }
    //    Collection<Publication> getPublicationsByLegislatureFromTo(Legislature legislature, Date from, Date to);
    @Test (expected = ResourceNotFound.class)
    public void getPublicationByLegislatureFromTo_LegislatureNotFound(){
        Legislature leg = new Legislature();
        leg.setId(6);
        this.publicationService.getPublicationsByLegislatureFromTo(leg,new Date(),new Date());
    }
    @Test
    public void getPublicationByLegislatureFromTo_invalidStartDate(){
        Legislature leg = mockLeg.values().stream().findAny().get();
        Date invalidDate;
        try {
            invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("30/2/2020");
            Assert.assertThrows(InvalidParameter.class,()->{
                        this.publicationService.getPublicationsByLegislatureFromTo(leg,invalidDate,new Date());
                    }
            );
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new InvalidParameter("bad parse");
        }
    }
    @Test
    public void getPublicationByLegislationFromTo_invalidEndDate(){
        Legislature leg = mockLeg.values().stream().findAny().get();
        Date invalidDate;
        try {
            invalidDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/2/2020");
            Assert.assertThrows(InvalidParameter.class,()->{
                        this.publicationService.getPublicationsByLegislatureFromTo(leg,new Date(),invalidDate);
                    }
            );
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new InvalidParameter("bad parse");
        }
    }
    @Test
    public void getPublicationByLegislationFromTo_startDateNullShouldReturnSomething(){
        Legislature leg = mockLeg.values().stream().findAny().get();
        Assert.assertNotEquals(null,
                this.publicationService.getPublicationsByLegislatureFromTo(leg,null,new Date()));
    }
    @Test
    public void getPublicationByLegislationFromTo_endDateNullShouldReturnSomething(){
        Legislature legislature = mockLeg.values().stream().findAny().get();
        Assert.assertNotEquals(null,
                this.publicationService.getPublicationsByLegislatureFromTo(legislature,new Date(),null));
    }
    //    Publication getPublicationById(Integer id);
    @Test(expected = ResourceNotFound.class)
    public void getPublicationById_notFound(){
        this.publicationService.getPublicationById(-1);
    }
    @Test
    public void getPublicationById_correctReturn(){
        Publication mockPub = this.mockpublication.values().stream().findAny().get();
        Assert.assertEquals(mockPub.getId(),this.publicationService.getPublicationById(mockPub.getId()).getId());
    }
    //    Collection<Publication> getPublicationsByEvent(Event event);
    @Test(expected = ResourceNotFound.class)
    public void getPublicationByEvent_eventNotFound(){
        Event event= new Event();
        event.setId(6);
        this.publicationService.getPublicationsByEvent(event);
    }
    @Test
    public void getPublicationByEvent_publicationIncluded(){
        mockEvents.forEach((id,event)->{
            Collection<Publication> publications=this.publicationService.getPublicationsByEvent(event);
            publications.forEach(publication -> Assert.assertTrue(event.getEventPublications().contains(publication)));
        });
    }
}
