package be.demoustiez.politicalAnalysisAPI;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.CommissionDAO;
import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.service.CommissionService;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.ICommissionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class CommissionTest {

    private ICommissionService commissionService;
    @Mock
    private CommissionDAO comDAO;
    private HashMap<Legislature,Collection<Commission>> mockCommissions;

    @Before
    private void setMock(){
        MockitoAnnotations.initMocks(this);
        commissionService=new CommissionService(comDAO);
        mockCommissions=initMockCommissions();
        mockCommissions.values().forEach(mocks->mocks.forEach(mock->
            Mockito.when(comDAO.getCommission(mock.getId())).thenReturn(mock)
        ));
        Mockito.when(comDAO.getCommissions()).thenReturn(mockCommissions.values().stream().reduce(new ArrayList<>(),
                ((precResult,current)-> {
                    precResult.addAll(current);
                    return precResult;
                })));
    }
    private HashMap<Legislature,Collection<Commission>> initMockCommissions(){
        HashMap<Legislature,Collection<Commission>> mocks = new HashMap();
        Legislature leg= new Legislature();
        leg.setId(1);
        mocks.put(leg,new ArrayList<>());
        leg=new Legislature();
        leg.setId(2);
        mocks.put(leg,new ArrayList<>());
        mocks.forEach((legislature,commissions)->{
            int i=100*legislature.getId();
            int iStart=i;
            while(i<iStart+10){
                commissions.add(createCom(i,legislature));
                i++;
            }
        });
        return mocks;
    }
    private Commission createCom(Integer id,Legislature legislature){
        Commission com = new Commission();
        com.setId(id);
        com.setLegislation(legislature);
        return com;
    }

    @Test
    public void getCommissions_LegislatureNotFound_size0(){
        Legislature nonExistant=new Legislature();
        nonExistant.setId(this.mockCommissions.size()+1);
        Collection<Commission>commissions=this.commissionService.getCommissions(nonExistant);
        Assert.assertEquals(commissions.size(),0);
    }

    @Test
    public void getCommissions_allCommissionsHaveLegislature(){
        Legislature leg = mockCommissions.keySet().stream().findAny().get();
        Collection<Commission> commissions=this.commissionService.getCommissions(leg);
        commissions.forEach(com-> Assert.assertTrue(leg.equals(com.getLegislation())));
    }
    @Test
    public void getCommissions_gotAllCommissionsFromMocks(){
        Legislature leg = mockCommissions.keySet().stream().findAny().get();
        Collection<Commission> commissions=this.commissionService.getCommissions(leg);
        Assert.assertEquals(this.mockCommissions.get(leg).size(),commissions.size());
    }
    @Test(expected = ResourceNotFound.class)
    public void getCommmissionById_notFound(){
        this.commissionService.getCommissionById(10000);
    }
    @Test
    public void getCommissionById_correctResult(){
        Commission commission = this.commissionService.getCommissionById(1);
        Assert.assertEquals(commission.getId().intValue(),1);
    }
}
