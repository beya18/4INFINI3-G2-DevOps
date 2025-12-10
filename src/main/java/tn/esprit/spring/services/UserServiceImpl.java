package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public List<User> retrieveAllUsers() {
		l.info("Début de la méthode retrieveAllUsers()");

		List<User> users = userRepository.findAll();

		l.debug("Liste des utilisateurs récupérée : " + users);
		l.info("Fin de la méthode retrieveAllUsers() - nombre d'utilisateurs = " + users.size());

		return users;
	}

	@Override
	public User addUser(User u) {

		User utilisateur = null;

		try {
			l.info("Début de la méthode addUser() avec user = " + u);

			utilisateur = userRepository.save(u);

			l.info("Utilisateur ajouté avec succès : " + utilisateur);
			l.debug("Détails utilisateur ajouté : " + utilisateur.toString());

		} catch (Exception e) {
			l.error("Erreur dans addUser() : ", e);
		}

		return utilisateur;
	}

	@Override
	public User updateUser(User u) {

		User userUpdated = null;

		try {
			l.info("Début de la méthode updateUser() avec user = " + u);

			userUpdated = userRepository.save(u);

			l.info("Utilisateur mis à jour avec succès : " + userUpdated);
			l.debug("Détails utilisateur mis à jour : " + userUpdated.toString());

		} catch (Exception e) {
			l.error("Erreur dans updateUser() : ", e);
		}

		return userUpdated;
	}

	@Override
	public void deleteUser(String id) {

		try {
			l.info("Début de la méthode deleteUser() avec id = " + id);

			userRepository.deleteById(Long.parseLong(id));

			l.info("Utilisateur supprimé avec succès. ID supprimé : " + id);

		} catch (Exception e) {
			l.error("Erreur dans deleteUser() : ", e);
		}

	}

	@Override
	public User retrieveUser(String id) {
		User u = null;

		try {
			l.info("Début de retrieveUser() avec id = " + id);

			u = userRepository.findById(Long.parseLong(id)).orElse(null);

			l.info("Utilisateur récupéré : " + u);
			l.debug("Détails utilisateur récupéré : " + (u != null ? u.toString() : "null"));

		} catch (Exception e) {
			l.error("Erreur dans retrieveUser() : ", e);
		}

		return u;
	}
}
