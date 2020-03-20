# PoliticalAnalysis
Projet personnel visant à créer un site de traitement des informations publiques du parlement wallon

## code
partie API REST, faite sous Spring

## appels possibles

### Deputés (/deputies)

[détails](https://github.com/Hasarian/PoliticalAnalysis/wiki/Deputy-controller)

* tous les députés (/all)
* un député selon son id (/{id})
* les députés membres d'un groupe (/group/{name})
* tous les députés triés par groupe (/groupsComposition)
* recherche selon le nom (/search?[lastNameTerm][&firstNameTerm])
