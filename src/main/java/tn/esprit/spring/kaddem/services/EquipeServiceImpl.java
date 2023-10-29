package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
	return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId) {
		Optional<Equipe> equipe = equipeRepository.findById(equipeId);

		if (!equipe.isPresent()) {
			throw new EntityNotFoundException("No Equipe found with ID: " + equipeId);
		}

		return equipe.get();
	}

	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
		for (Equipe equipe : equipes) {
			if (!isEligibleForEvolution(equipe)) {
				continue;
			}

			int nbEtudiantsAvecContratsActifs = countActiveContracts((List<Etudiant>) equipe.getEtudiants());
			if (nbEtudiantsAvecContratsActifs >= 3) {
				promoteEquipe(equipe);
			}
		}
	}

	private boolean isEligibleForEvolution(Equipe equipe) {
		return equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR);
	}

	private int countActiveContracts(List<Etudiant> etudiants) {
		int count = 0;
		for (Etudiant etudiant : etudiants) {
			for (Contrat contrat : etudiant.getContrats()) {
				if (isActiveContract(contrat)) {
					count++;
					break;
				}
			}
			if (count >= 3) {
				break;
			}
		}
		return count;
	}

	private boolean isActiveContract(Contrat contrat) {
		if (Boolean.TRUE.equals(contrat.getArchive())) {
			return false;
		}

		Date dateSysteme = new Date();
		long differenceInTime = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
		long differenceInYears = differenceInTime / (1000L * 60 * 60 * 24 * 365);

		return differenceInYears > 1;
	}

	private void promoteEquipe(Equipe equipe) {
		if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
			equipe.setNiveau(Niveau.SENIOR);
		} else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
			equipe.setNiveau(Niveau.EXPERT);
		}
		equipeRepository.save(equipe);
	}
}