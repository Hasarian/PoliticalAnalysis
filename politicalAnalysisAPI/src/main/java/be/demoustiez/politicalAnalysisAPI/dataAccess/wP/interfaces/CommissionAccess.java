package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.Collection;
import java.util.List;

//no arg for list
// composition commission id

public interface CommissionAccess {
    Commission getCommission(Integer commissionId);
    Collection<Commission> getCommissions();
}
