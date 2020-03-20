package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.DeputeesDAO;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;
import be.demoustiez.politicalAnalysisAPI.service.DeputyService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.IDeputyService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DeputyTest {
    @Mock
    private DeputeesDAO deputyAccess;
    private IDeputyService deputyService;
    private HashMap<Integer,Deputy> mockDeputies;



    private void initDeputyMock(){
        this.mockDeputies=new HashMap<>();
        Deputy deputy = initDeputy(1,"aaa","aaa","1");
        mockDeputies.put(deputy.getId(),deputy);
        deputy=initDeputy(2,"abb","AAA","1");
        mockDeputies.put(deputy.getId(),deputy);
        deputy=initDeputy(3,"bbb","Abb","2");
        mockDeputies.put(deputy.getId(),deputy);

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
        mockDeputies.forEach((id,deputy)-> Mockito.when(this.deputyAccess.getDeputyById(id)).thenReturn(deputy));
        Mockito.when(this.deputyAccess.getDeputies()).thenReturn(mockDeputies.values());
    }

    //get by id
    @Test(expected = ResourceNotFound.class)
    public void getDeputyById_NotFound(){
        this.deputyService.getDeputyById(mockDeputies.size()+1);

    }
    @Test
    public void getDeputyById_CorrectResult(){
        Integer idRef= 1;
        Deputy deputy = this.deputyService.getDeputyById(idRef);
        Assert.assertEquals(deputy.getId(),idRef);
    }

    //deputies in group
    @Test(expected = ResourceNotFound.class)
    public void getDeputiesByGroup_NotFound(){
        this.deputyService.getDeputiesByGroup("3");
    }

    @Test
    public void getDeputiesByGroup_AllInGroup() {
        String groupNameRef="1";
        Collection<Deputy> deputies=this.deputyService.getDeputiesByGroup(groupNameRef);
        deputies.forEach((deputy)->Assert.assertEquals(deputy.getGroup(),groupNameRef));
    }

    @Test
    public void getDeputiesByGroup_FullGroup(){
        String groupNameRef="1";
        long groupSize = this.mockDeputies.values().stream().filter(deputy ->
                deputy.getGroup().equals(groupNameRef)
            ).count();
        Collection<Deputy> deputies=this.deputyService.getDeputiesByGroup(groupNameRef);
        Assert.assertEquals(deputies.size(),groupSize);
    }

    //getDeputies
    @Test
    public void getDeputies_AllDeputies(){
        long deputiesNumber = this.mockDeputies.size();
        Collection<Deputy> deputies = this.deputyService.getDeputies();
        Assert.assertEquals(deputiesNumber,deputies.size());
    }
    @Test
    public void getDeputies_ListSize0(){
        Mockito.reset(deputyAccess);
        Mockito.when(this.deputyAccess.getDeputies()).thenReturn(new ArrayList<>());
        Collection<Deputy> deputies = this.deputyService.getDeputies();
        Assert.assertEquals(deputies.size(),0);
    }

    //ordered by group
    @Test
    public void getDeputeesOrderedByGroup_correctNumberOfGroups(){
        HashMap<String,List<Deputy>> groups= this.deputyService.getDeputiesOrderedByGroup();
        Assert.assertEquals(groups.size(),2);
    }

    @Test
    public void getDeputeesOrderedByGroup_GroupCorrectSize(){
        HashMap<String,List<Deputy>> groups= this.deputyService.getDeputiesOrderedByGroup();
        groups.forEach((groupName,deputies)->{
            Assert.assertEquals(deputies.size(),
                    this.mockDeputies.values().stream().filter((deputy)->deputy.getGroup().equals(groupName)).count());
        });
    }

    //search by name
    @Test
    public void searchDeputiesByName_doubleNullReturnsFullList(){
        Assert.assertEquals(this.deputyService.searchDeputyByName(null,null).size(),
                this.mockDeputies.size());
    }
    @Test
    public void searchDeputiesByName_nonCaseSensitiveLastName(){
        Collection<Deputy> deputiesUpper = this.deputyService.searchDeputyByName("A",null);
        Collection<Deputy> deputiesLower = this.deputyService.searchDeputyByName("a",null);
        Assert.assertEquals(deputiesLower.size(),deputiesUpper.size());
        deputiesLower.forEach((deputyLower ->
            Assert.assertTrue(deputiesUpper.stream().anyMatch(deputyUpper->deputyUpper.getId().equals(deputyLower.getId())))
                ));
        deputiesUpper.forEach((deputyUpper ->
                Assert.assertTrue(deputiesLower.stream().anyMatch(deputyLower->deputyUpper.getId().equals(deputyLower.getId())))
        ));
    }
    @Test
    public void searchDeputiesByName_nonCasSensitiveFirstName(){
        Collection<Deputy> deputiesUpper = this.deputyService.searchDeputyByName(null,"A");
        Collection<Deputy> deputiesLower = this.deputyService.searchDeputyByName(null,"a");
        Assert.assertEquals(deputiesLower.size(),deputiesUpper.size());
        deputiesLower.forEach((deputyLower ->
                Assert.assertTrue(deputiesUpper.stream().anyMatch(deputyUpper->deputyUpper.getId().equals(deputyLower.getId())))
        ));
        deputiesUpper.forEach((deputyUpper ->
                Assert.assertTrue(deputiesLower.stream().anyMatch(deputyLower->deputyUpper.getId().equals(deputyLower.getId())))
        ));
    }
    @Test
    public void searchDeputiesByName_correctResult(){
        Collection<Deputy> foundDeputies = this.deputyService.searchDeputyByName("a","a");
        foundDeputies.forEach(deputy->
            Assert.assertTrue((deputy.getLastName().toLowerCase().contains("a"))&&
                                (deputy.getFirstName().toLowerCase().contains("a")))
        );
    }
}
