package be.demoustiez.politicalAnalysisAPI.dataAccess.wP;

import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import be.demoustiez.politicalAnalysisAPI.dataAccess.UrlBuilder;
import be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP.DeputyDTO;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.DeputyAccess;
import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DeputeesDAO implements DeputyAccess {

    private UrlBuilder<DeputyDTO> urlBuilder;
    private HashMap<Integer,Deputy> deputeesCache;
    private static final String GROUP_TAG="pa";
    private static final String CIRC_TAG="circ";

    public DeputeesDAO(ConfigurationLoader config){
        this.urlBuilder=new UrlBuilder<>(config,"deputees");
        loadDeputies();
    }
    private void loadDeputies(){
        deputeesCache= new HashMap<>();
        HashMap<String,String> args = new HashMap<>();
        DeputyDTO dto = this.urlBuilder.sendRequest(args);
        for (DeputyDTO.Deputy deputyDTO:dto.getDeputes()) {
            Deputy deputy = fromDTOToDeputy(deputyDTO);
            this.deputeesCache.put(deputy.getId(),deputy);
        }
    }
    private List<Deputy> fromDTOtoList(DeputyDTO dto){
        List<Deputy> deputies=new ArrayList<>();
        for (DeputyDTO.Deputy deputyDTO:dto.getDeputes()){
            deputies.add(fromDTOToDeputy(deputyDTO));
        }
        return deputies;
    }
    private Deputy fromDTOToDeputy(DeputyDTO.Deputy deputyDTO){
        Deputy deputy = new Deputy();

        Deputy.Address address= new Deputy.Address();
        address.setLocal(deputyDTO.getDep_adresse_postale().getDep_localite());
        address.setStreet(deputyDTO.getDep_adresse_postale().getDep_adresse());
        address.setZip(deputyDTO.getDep_adresse_postale().getDep_cp());
        deputy.setAddress(address);

        Deputy.BureauInfo bureauPosition=new Deputy.BureauInfo();
        bureauPosition.setFunction(deputyDTO.getDep_fonction_bureau());
        bureauPosition.setOrder(deputyDTO.getDep_ordre_bureau());
        deputy.setBureauPosition(bureauPosition);

        deputy.setCirconscription(deputyDTO.getDep_circons());
        deputy.setCivilTitle(deputyDTO.getDep_civ());

        Deputy.BureauInfo confiPosition = new Deputy.BureauInfo();
        confiPosition.setOrder(deputyDTO.getDep_ordre_conf_pres());
        confiPosition.setFunction(deputyDTO.getDep_fonction_conf_pres());
        deputy.setConfPosition(confiPosition);

        deputy.setEmail(deputyDTO.getDep_email());
        deputy.setFacebookURL(deputyDTO.getDep_social_nt().getDep_facebook());
        deputy.setFax(deputyDTO.getDep_fax());
        deputy.setFirstName(deputyDTO.getDep_prenom());
        deputy.setGroup(deputyDTO.getDep_parti());
        deputy.setGroupPres(deputyDTO.isDep_presidentgroupe());
        deputy.setId(deputyDTO.getDep_id());
        deputy.setLastName(deputyDTO.getDep_nom());
        deputy.setPictureURL(deputyDTO.getDep_photo());
        deputy.setProvince(deputyDTO.getDep_province());
        deputy.setTelNumber(deputyDTO.getDep_tel());
        deputy.setTwitterURL(deputyDTO.getDep_social_nt().getDep_twitter());
        deputy.setUrl(deputyDTO.getDep_url());

        Deputy.BureauInfo wideBureauPosition = new Deputy.BureauInfo();
        wideBureauPosition.setFunction(deputyDTO.getDep_fonction_bureau_elargi());
        wideBureauPosition.setOrder(deputyDTO.getDep_ordre_bureau_elargi());
        deputy.setWideBureauPosition(wideBureauPosition);
        return deputy;
    }
    @Override
    public Collection<Deputy> getDeputies() {
        return this.deputeesCache.values();
    }

    @Override
    public List<Deputy> getDeputies(String group, String circ) {
        HashMap<String,String> agrs = new HashMap<>();
        if(group!=null&&!group.isEmpty()) agrs.put(GROUP_TAG,group);
        if(circ!=null&&!circ.isEmpty()) agrs.put(CIRC_TAG,circ);
        return this.fromDTOtoList(this.urlBuilder.sendRequest(agrs));
    }

    @Override
    public Deputy getDeputyById(Integer id) {
        return this.deputeesCache.get(id);
    }
}
