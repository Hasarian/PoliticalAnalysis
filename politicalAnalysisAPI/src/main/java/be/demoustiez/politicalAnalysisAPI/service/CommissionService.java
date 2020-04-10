package be.demoustiez.politicalAnalysisAPI.service;

import be.demoustiez.politicalAnalysisAPI.Errors.ResourceNotFound;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.CommissionDAO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.CommissionAccess;
import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.service.interfaces.ICommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CommissionService implements ICommissionService{

    private CommissionAccess commissionAccess;

    @Autowired
    public CommissionService(CommissionDAO comDao){
        this.commissionAccess=comDao;
    }

    @Override
    public Collection<Commission> getCommissions(Legislature legislature) {
        Collection<Commission> commissions = this.commissionAccess.getCommissions().stream().filter(commission ->
                commission.getLegislation().getId()==(commission.getLegislation().getId())).collect(Collectors.toList());
        return commissions;
    }

    @Override
    public Commission getCommissionById(Integer id) {
        Commission commission= this.commissionAccess.getCommission(id);
        if(commission==null) throw new ResourceNotFound("commission "+id);
        return commission;
    }
}
