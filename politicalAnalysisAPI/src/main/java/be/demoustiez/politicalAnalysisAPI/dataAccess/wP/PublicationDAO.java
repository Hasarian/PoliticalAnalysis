package be.demoustiez.politicalAnalysisAPI.dataAccess.wP;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import be.demoustiez.politicalAnalysisAPI.dataAccess.UrlBuilder;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.CommissionAccess;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.LegislatureAccess;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.PublicationAccess;
import be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces.SessionAccess;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.model.Publication;
import be.demoustiez.politicalAnalysisAPI.dataAccess.dtoWP.PublicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PublicationDAO implements PublicationAccess {

    private static final String IDLEG_TAG="idleg";
    private static final String IDSES_TAG="idses";
    private static final String YEAR_TAG="annee";
    private static final String MONTH_TAG="mois";
    private static final String TYPE_TAG="type";
    private static final String NUM_TAG="num";
    private static final String DATE_TAG="date";
    private static final String COMMISSION_TAG="idcom";


    private HashMap<Integer,Publication> publicaitonCache;
    private LegislatureAccess legislatureAccess;
    private SessionAccess sessionAccess;
    private CommissionAccess commissionAccess;
    private UrlBuilder<PublicationDTO> urlBuilder;

    @Autowired
    public PublicationDAO(ConfigurationLoader loader,LegislatureDAO legDao,SessionDAO sesDAO,CommissionDAO comDAO){
        this.legislatureAccess=legDao;
        this.sessionAccess=sesDAO;
        this.commissionAccess=comDAO;
        this.urlBuilder=new UrlBuilder<>(loader,"publications",PublicationDTO.class);
        this.publicaitonCache=new HashMap<>();
    }

    @Override
    public List<Publication> getPublicationsByIds(List<Integer> ids) throws ArgumentError {
        return getPublicationsByIds(ids,this.legislatureAccess.getCurrentLegislature());
    }

    @Override
    public List<Publication> getPublicationsByIds(List<Integer> ids, Legislature legislature) throws ArgumentError {
        List<Publication> publications = new ArrayList<>();

        for (Integer id : ids){
            if(this.publicaitonCache.containsKey(id)) publications.add(this.publicaitonCache.get(id));
            else{
                publications.add(this.getPublicaitonById(id,legislature));
            }
        }
        return publications;
    }


    @Override
    public Publication getPublicationById(Integer id) throws ArgumentError{
        return getPublicaitonById(id,this.legislatureAccess.getCurrentLegislature());
    }

    @Override
    public Publication getPublicaitonById(Integer id, Legislature legislature) throws ArgumentError {
        if(!publicaitonCache.containsKey(id)) this.getPublications(legislature.getId(),
                null,null,null,null, null,null,null);
        return this.publicaitonCache.get(id);
    }

    @Override
    public List<Publication> getPublications(Legislature idLeg) throws ArgumentError {

        return this.getPublications(idLeg.getId(),null,null,null,null,
                null,null,null);
    }


    @Override
    public List<Publication> getPublications(Integer idLeg, Integer idses, Integer year, Integer month, String type,
                                             Integer pubId, String date, Integer idCom) throws ArgumentError {
        HashMap<String,String> args= new HashMap<>();
        if(idLeg==null) throw  new ArgumentError("idLeg","is mandatory");
        args.put(IDLEG_TAG,idLeg.toString());
        if(idses!=null) args.put(IDSES_TAG,idses.toString());
        if(year!=null) args.put(YEAR_TAG,year.toString());
        if(month!=null) args.put(MONTH_TAG,month.toString());
        if(type!=null) args.put(TYPE_TAG,type);
        if(pubId!=null) args.put(NUM_TAG,pubId.toString());
        if(date!=null) args.put(DATE_TAG,date);
        if(idCom!=null) args.put(COMMISSION_TAG,idCom.toString());
        PublicationDTO dto = this.urlBuilder.sendRequest(args);
        return fromDTOToPublications(dto);
    }
    private List<Publication> fromDTOToPublications(PublicationDTO dto){
        List<Publication> publications = new ArrayList<>();
        for (PublicationDTO.Publication pubDTO:dto.getPublications()) {
            if(!this.publicaitonCache.containsKey(pubDTO.getPUB_ID())) {
                Publication publication = new Publication();

                publication.setType(pubDTO.getPUB_TYPE());
                publication.setTitle(pubDTO.getPUB_TITRE());
                publication.setSession(this.sessionAccess.sessionById(pubDTO.getPUB_ID_SES()));
                publication.setReference(pubDTO.getPUB_REFERENCE());
                publication.setLegislation(this.legislatureAccess.getLegislatureById(pubDTO.getPUB_ID_LEG()));
                publication.setId(pubDTO.getPUB_ID());
                publication.setFileName(pubDTO.getPUB_FICHIER());
                publication.setFileLink(pubDTO.getPUB_LIEN());
                publication.setCommission(commissionAccess.getCommission(pubDTO.getAG_ID_COM()));
                publication.setDate(pubDTO.getPUB_DATUM());

                publications.add(publication);
                this.publicaitonCache.put(publication.getId(), publication);
            }
        }
        return publications;
    }
    private void putPublicationsInCache(List<Publication> publications){
        this.publicaitonCache=new HashMap<>();
        for (Publication publicaiton:publications) {
            this.publicaitonCache.put(publicaiton.getId(),publicaiton);
        }
    }
}
