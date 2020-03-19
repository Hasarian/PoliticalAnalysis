package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.DeputeesDAO;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;
import be.demoustiez.politicalAnalysisAPI.service.DeputyService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IDeputyService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DeputyTest {
    @Mock
    private DeputeesDAO deputyAccess;
    private IDeputyService deputyService;
    private HashMap<Integer,Deputy> mockdeputies;



    private void initDeputyMock(){
        this.mockdeputies=new HashMap<>();
        Deputy deputy = initDeputy(1,"aaa","aaa","1");
        mockdeputies.put(deputy.getId(),deputy);
        deputy=initDeputy(2,"abb","AAA","1");
        mockdeputies.put(deputy.getId(),deputy);
        deputy=initDeputy(3,"bbb","Abb","2");
        mockdeputies.put(deputy.getId(),deputy);

    }
    private Deputy initDeputy(Integer id, String firstName,String lastName,String group){
        Deputy deputy = new Deputy();
        deputy.setId(id);
        deputy.setFirstName(firstName);
        deputy.setLastName(lastName);
        deputy.setGroup(group);
        return deputy;
    }

    @Before
    public void setMocks(){
        MockitoAnnotations.initMocks(this);
        this.deputyService=new DeputyService(this.deputyAccess);
        initDeputyMock();
        mockdeputies.forEach((id,deputy)->{
            Mockito.when(this.deputyAccess.getDeputyById(id)).thenReturn(deputy);
        });
        Mockito.when(this.deputyAccess.getDeputies()).thenReturn(mockdeputies.values());
    }
    @Test(expected = ResourceNotFound.class)
    public void getDeputyById_NotFound() throws ResourceNotFound{
        this.deputyService.getDeputyById(mockdeputies.size()+1);

    }
    @Test(expected = ResourceNotFound.class)
    public void getDeputiesByGroup_NotFound() throws ResourceNotFound{
        this.deputyService.getDeputeesByGroup("3");
    }
}
