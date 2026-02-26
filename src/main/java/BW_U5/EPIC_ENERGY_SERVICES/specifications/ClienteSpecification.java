package BW_U5.EPIC_ENERGY_SERVICES.specifications;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ClienteSpecification {

	public static Specification<Cliente> hasRagioneSociale(String ragioneSociale) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.like(
						criteriaBuilder.lower(root.get("ragioneSociale")),
						"%" + ragioneSociale.toLowerCase() + "%"
				);
	}

	public static Specification<Cliente> hasFatturatoMaggioreDi(double fatturato) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.greaterThan(root.get("fatturatoAnnuale"), fatturato);
	}

	public static Specification<Cliente> hasDataInserimentoBetween(LocalDate inizio, LocalDate fine) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.between(root.get("dataInserimento"), inizio, fine);
	}

	public static Specification<Cliente> hasDataUltimoContattoBetween(LocalDate inizio, LocalDate fine) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.between(root.get("dataUltimoContatto"), inizio, fine);
	}

	public static Specification<Cliente> hasCognomeContatto(String cognomeContatto) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.like(criteriaBuilder.lower(root.get("cognomeContatto")),
						"%" + cognomeContatto.toLowerCase() + "%"
				);
	}
}

