package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.LegislatureDAO;
import be.demoustiez.politicalAnalysisAPI.service.LegislatureService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.ILegislatureService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LegislatureTest {

    private ILegislatureService legislatureService;
    @Mock
    private LegislatureDAO dao;
    private HashMap<Integer,Legislature> mocks;

    private void initMocks(){
        mocks=new HashMap<>();
        for (int i=0;i<5;i++){
            Legislature leg = new Legislature();
            leg.setId(i);
            mocks.put(leg.getId(),leg);
        }
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        legislatureService=new LegislatureService(this.dao);
        initMocks();
        mocks.forEach((id,mock)-> Mockito.when(dao.getLegislatureById(id)).thenReturn(mock));
        Mockito.when(dao.getCurrentLegislature()).thenReturn(mocks.values().stream().findFirst().get());
    }

    @Test(expected = ResourceNotFound.class)
    public void getLegById_notFound(){
        this.legislatureService.getLegislatureById(this.mocks.size()+14);
    }
    @Test
    public void getLegById_correctId(){
        mocks.forEach((id,mock)-> Assert.assertEquals(id,this.legislatureService.getLegislatureById(id).getId()));
    }
}
