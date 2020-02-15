package be.demoustiez.politicalAnalysisAPI.dataAccess.wP;

import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import be.demoustiez.politicalAnalysisAPI.dataAccess.UrlBuilder;
import be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP.CommissionDTO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP.CompositionDTO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.CommissionAccess;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.DeputyAccess;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.LegislatureAccess;
import be.demoustiez.politicalAnalysisAPI.model.Commission;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CommissionDAO implements CommissionAccess {

    private UrlBuilder<CommissionDTO> urlBuilderCommission;
    private UrlBuilder<CompositionDTO> urlBuilderComposition;
    private LegislatureAccess legislatureAccess;
    private DeputyAccess deputyAccess;
    private HashMap<Integer,Commission> commissions;

    private static final String COMMISSION_TAG="id";


    public CommissionDAO(ConfigurationLoader configurationLoader,
                         LegislatureDAO legislatureAccess,DeputeesDAO deputyAccess){
        this.urlBuilderCommission=new UrlBuilder<>(configurationLoader,"commissions");
        this.urlBuilderComposition=new UrlBuilder<>(configurationLoader,"composition");
        this.legislatureAccess=legislatureAccess;
        this.deputyAccess=deputyAccess;
        loadCommissions();
    }

    @Override
    public Commission getCommission(Integer commissionId){
        if(this.commissions==null||this.commissions.size()==0) loadCommissions();
        return commissions.get(commissionId);
    }
    private void loadCommissions(){
        CommissionDTO dto = this.urlBuilderCommission.sendRequest(new HashMap<>());
        this.commissions=new HashMap<>();
        for (CommissionDTO.Commission commissionDTO:dto.getCommissions()) {
            Commission commission= new Commission();
            commission.setId(commissionDTO.getCom_id());
            commission.setLegislationName(legislatureAccess.getLegislatureByName(commissionDTO.getCom_nom_leg()));
            commission.setName(commissionDTO.getCom_nom_leg());
            commission.setType(commissionDTO.getCom_type());
            loadComposition(commission);
            this.commissions.put(commission.getId(),commission);

        }
    }
    @Override
    public Collection<Commission> getCommissions() {
        if(this.commissions==null||this.commissions.size()==0) loadCommissions();
        return this.commissions.values();
    }
    private void loadComposition(Commission commission){
        HashMap<String,String> args= new HashMap<>();
        args.put(COMMISSION_TAG,commission.getId().toString());
        List<Deputy> effectives = new ArrayList<>();
        List<Deputy> suppleants = new ArrayList<>();
        List<Deputy> vice_presidents= new ArrayList<>();
        CompositionDTO compositionDTO = this.urlBuilderComposition.sendRequest(args);
        for (CompositionDTO.Member memberDTO:compositionDTO.getMembres()) {
            Deputy deputy = deputyAccess.getDeputyById(memberDTO.getDep_id());
            if (memberDTO.isDep_membre_effectif()){
                String mandat = memberDTO.getDep_mandat();
                if(mandat.equals("Président")||mandat.equals("Présidente")){
                    commission.setPresident(deputy);
                }else if (mandat.equals("Vice Président")||mandat.equals("Vice Présidente")) {
                    vice_presidents.add(deputy);
                }else{
                    effectives.add(deputy);
                }
            }else{
                suppleants.add(deputy);
            }
        }
        commission.setCompositionEffective(effectives);
        commission.setVice_presidents(vice_presidents);
        commission.setCompositionSuppleant(suppleants);
    }
}
