package BW_U5.EPIC_ENERGY_SERVICES.specifications;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FatturaSpecification {

    public static Specification<Fattura> hasNumeroFattura(int numeroFattura){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("numero"),
                        numeroFattura));
    }

    public static Specification<Fattura> hasIdCliente(long idCliente){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.join("cliente").get("id"),
                        idCliente));
    }

    public static Specification<Fattura> hasIdStatoFattura(long idStato){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.join("statoFattura").get("id"),
                idStato));
    }

    public static Specification<Fattura> hasData(LocalDate data) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("data"),
                        data);
    }

    public static Specification<Fattura> hasDataBetween(LocalDate inizio, LocalDate fine) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("data"), inizio, fine);
    }

    public static Specification<Fattura> hasImportoBetween(double importoMin, double importoMax) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("importo"), importoMin, importoMax);
    }
}
