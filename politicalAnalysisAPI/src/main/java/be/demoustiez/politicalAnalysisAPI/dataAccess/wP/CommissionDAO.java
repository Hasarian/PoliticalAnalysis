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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class CommissionDAO implements CommissionAccess {

    private UrlBuilder<CommissionDTO> urlBuilderCommission;
    private UrlBuilder<CompositionDTO> urlBuilderComposition;
    private LegislatureAccess legislatureAccess;
    private DeputyAccess deputyAccess;
    private HashMap<Integer,Commission> commissions;

    private static final String COMMISSION_TAG="id";

    @Autowired
    public CommissionDAO(ConfigurationLoader configurationLoader,
                         LegislatureDAO legislatureAccess,DeputeesDAO deputyAccess){
        this.urlBuilderCommission=new UrlBuilder<>(configurationLoader,"commissions",CommissionDTO.class);
        this.urlBuilderComposition=new UrlBuilder<>(configurationLoader,"composition",CompositionDTO.class);
        this.legislatureAccess=legislatureAccess;
        this.deputyAccess=deputyAccess;
        this.commissions=new HashMap<>();
    }

    @Override
    public Commission getCommission(Integer commissionId){
        Commission com;
       if(this.commissions.containsKey(commissionId)) com= this.commissions.get(commissionId);
       else{
           com= getCommissionFromURL(commissionId);
           this.commissions.put(commissionId,com);
       }
        return com;
    }
    private void loadCommissions(){
        CommissionDTO dto = this.urlBuilderCommission.sendRequest(new HashMap<>());
        this.commissions=new HashMap<>();
        for (CommissionDTO.Commission commissionDTO:dto.getCommissions()) {
            if(!this.commissions.containsKey(commissionDTO.getCom_id())) {
                Commission commission = fromDTOToCommission(commissionDTO);
                this.commissions.put(commission.getId(), commission);
            }
        }
    }
    public Commission fromDTOToCommission(CommissionDTO.Commission dto){
        Commission commission= new Commission();
        commission.setId(dto.getCom_id());
        commission.setLegislation(legislatureAccess.getLegislatureByName(dto.getCom_nom_leg()));
        commission.setName(dto.getCom_nom_leg());
        commission.setType(dto.getCom_type());
        loadComposition(commission);
        return commission;
    }
    @Override
    public Collection<Commission> getCommissions() {
        loadCommissions();
        return this.commissions.values();
    }
    private Commission getCommissionFromURL(Integer commissionId){
        if(commissionId==null) return null;
        CommissionDTO dto = this.urlBuilderCommission.sendRequest(new HashMap<>());
        int i=0;
        CommissionDTO.Commission commission=dto.getCommissions()[i];
        int currentId=commission.getCom_id();
        while(i<dto.getCommissions_infos().getNb_com()&&currentId!=commissionId){
            i++;
            if(i<dto.getCommissions_infos().getNb_com()) {
                commission = dto.getCommissions()[i];
                currentId = commission.getCom_id();
            }
        }
        return (currentId==commissionId)?fromDTOToCommission(commission):null;
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
