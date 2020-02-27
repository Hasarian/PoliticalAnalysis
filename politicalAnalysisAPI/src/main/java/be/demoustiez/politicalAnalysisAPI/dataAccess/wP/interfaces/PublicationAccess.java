package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;

import be.demoustiez.politicalAnalysisAPI.Errors.ArgumentError;
import be.demoustiez.politicalAnalysisAPI.model.Legislature;
import be.demoustiez.politicalAnalysisAPI.model.Publication;

import java.util.List;

public interface PublicationAccess {
    List<Publication> getPublicationsByIds(List<Integer> ids) throws ArgumentError;
    List<Publication> getPublicationsByIds(List<Integer> ids, Legislature legislature) throws ArgumentError;
    Publication getPublicationById(Integer id) throws ArgumentError;
    Publication getPublicaitonById(Integer id,Legislature legislature) throws ArgumentError;
    List<Publication> getPublications(Legislature idLeg) throws ArgumentError;
    List<Publication> getPublications(Integer idLeg,Integer idses,Integer year,Integer month,String type,Integer pubNum,
                                      String date,Integer idCom) throws ArgumentError;
}
