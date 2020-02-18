package be.demoustiez.politicalAnalysisAPI.dataAccess.wP.interfaces;
    /*
    * pa = nom du groupe (trouvable dans les informations sur les députés, il s'agit de l'abréviation du parti (ps/mr/...), ne fait pas de différence maj/minuscule )
      circ = nom de la circonscription (liste)
    *
    * */

import be.demoustiez.politicalAnalysisAPI.model.Deputy;

import java.util.Collection;
import java.util.List;

public interface DeputyAccess {
    Collection<Deputy> getDeputies();
    List<Deputy> getDeputies(String group,String circ);
    Deputy getDeputyById(Integer id);
}
