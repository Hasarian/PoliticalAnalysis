package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.DeputeesDAO;
import be.demoustiez.politicalAnalysisAPI.service.DeputyService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IDeputyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DeputyTest {
    @Mock
    DeputeesDAO deputyAccess;
    IDeputyService deputyService;

    @Before
    public void setMocks(){
        MockitoAnnotations.initMocks(this);
        this.deputyService=new DeputyService(this.deputyAccess);
    }
    @Test
    public void getDeputyById_NotFound(){

    }
    @Test
    public void getDeputiesByGroup_NotFound(){

    }
    @Test
    void getDeputiesOrderedByGroup_NotFound(){

    }


}
