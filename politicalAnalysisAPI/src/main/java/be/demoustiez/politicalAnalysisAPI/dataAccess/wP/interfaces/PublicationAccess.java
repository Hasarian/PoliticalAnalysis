package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.model.Publication;

import java.util.List;

public interface PublicationAccess {
    List<Publication> getPublicationsByIds(List<Integer> ids);
    Publication getPublicationById(Integer id);
    List<Publication> getPublications(Integer idLeg) throws ArgumentError;
    List<Publication> getPublications(Integer idLeg,Integer idses,Integer year,Integer month,String type,Integer pubId,
                                      String date,Integer idCom) throws ArgumentError;
}
