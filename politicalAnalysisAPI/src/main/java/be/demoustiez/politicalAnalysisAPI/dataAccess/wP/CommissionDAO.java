package be.demoustiez.politicalAnalysisAPI.dataAccess.wP;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.CommissionAccess;
import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.List;

public class CommissionDAO implements CommissionAccess {
    @Override
    public Commission getCommission(Integer commissionId){
        return null;
    }

    @Override
    public List<Commission> getCommissions() {
        return null;
    }

    @Override
    public List<Deputy> getCommissionComposition(Integer commissionId) {
        return null;
    }
}
