package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Comune;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Indirizzo;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.ValidationException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.ClienteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ClienteRepository;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ComuneRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static BW_U5.EPIC_ENERGY_SERVICES.specifications.ClienteSpecification.*;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final Cloudinary cloudinary;
	private final ComuneRepository comuneRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository, Cloudinary cloudinary, ComuneRepository comuneRepository) {
		this.clienteRepository = clienteRepository;
		this.cloudinary = cloudinary;
		this.comuneRepository = comuneRepository;
	}

	//------------------------------------- P O S T ----------------------------------------------

	// save new cliente

	public Cliente saveCliente(ClienteDTO clienteDTO) {
		this.clienteRepository.findByEmail(clienteDTO.email()).ifPresent(cliente -> {
			throw new ValidationException("Cliente already exists");
		});
		Comune comuneIndirizzoLegale = comuneRepository.findById(clienteDTO.indirizzoLegale().idComune()).orElseThrow(() -> new NotFoundException("Comune non trovato"));

		Comune comuneIndirizzoCommerciale = comuneRepository.findById(clienteDTO.indirizzoCommerciale().idComune()).orElseThrow(() -> new NotFoundException("Comune non trovato"));

		Indirizzo indirizzoLegale = new Indirizzo(
				clienteDTO.indirizzoLegale().via(),
				clienteDTO.indirizzoLegale().civico(),
				clienteDTO.indirizzoLegale().cap(),
				comuneIndirizzoLegale);

		Indirizzo indirizzoCommerciale = new Indirizzo(
				clienteDTO.indirizzoCommerciale().via(),
				clienteDTO.indirizzoCommerciale().civico(),
				clienteDTO.indirizzoCommerciale().cap(),
				comuneIndirizzoCommerciale);

		Cliente cliente = new Cliente(
				clienteDTO.ragioneSociale(),
				clienteDTO.partitaIva(),
				clienteDTO.email(),
				clienteDTO.dataInserimento(),
				clienteDTO.dataUltimoContatto(),
				clienteDTO.fatturatoAnnuale(),
				clienteDTO.pec(),
				clienteDTO.numeroDiTelefono(),
				clienteDTO.emailDiContatto(),
				clienteDTO.nomeContatto(),
				clienteDTO.cognomeContatto(),
				clienteDTO.telefonoDiContatto(),
				clienteDTO.tipoCliente(),
				indirizzoLegale,
				indirizzoCommerciale

		);
		return clienteRepository.save(cliente);
	}

	//------------------------------------- G E T ----------------------------------------------

//	 get all cliente

	public Page<Cliente> findAllClientes(int page, int size, String sortBy) {
		if (page <= 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		if (sortBy == null) sortBy = "name";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return clienteRepository.findAll(pageable);
	}

	// get cliente by id

	public Cliente findById(long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente con id " + id + " non trovato"));
	}

	// get cliente by email

	public Cliente findByEmail(String email) {
		return clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Cliente con email " + email + " non trovato"));
	}

	// ----------------------------------------- QUERIES ------------------------------------------------

	public List<Cliente> filtriDinamici(
			String ragioneSociale,
			Double fatturato,
			LocalDate dataInserimentoStart,
			LocalDate dataInserimentoEnd,
			LocalDate ultimoContattoStart,
			LocalDate ultimoContattoEnd,
			String cognomeContatto
	) {

		Specification<Cliente> spec = Specification.where((root, query, criteriaBuilder) -> null);

		if (ragioneSociale != null) {
			spec = spec.and(hasRagioneSociale(ragioneSociale));
		}

		if (fatturato != null) {
			spec = spec.and(hasFatturatoMaggioreDi(fatturato));
		}

		if (dataInserimentoStart != null && dataInserimentoEnd != null) {
			spec = spec.and(hasDataInserimentoBetween(dataInserimentoStart, dataInserimentoEnd));
		}

		if (ultimoContattoStart != null && ultimoContattoEnd != null) {
			spec = spec.and(hasDataUltimoContattoBetween(ultimoContattoStart, ultimoContattoEnd));
		}
		if (cognomeContatto != null) {
			spec = spec.and(hasCognomeContatto(cognomeContatto));
		}

		return clienteRepository.findAll(spec);
	}

	//------------------------------------- P U T ----------------------------------------------

	// modify cliente

	public Cliente updateCliente(long id, ClienteDTO clienteDTO) {
		Cliente cliente = this.findById(id);

		Comune comuneIndirizzoCommerciale = comuneRepository.findById(clienteDTO.indirizzoCommerciale().idComune()).orElseThrow(() -> new NotFoundException("Comune non trovato"));
		Comune comuneIndirizzoLegale = comuneRepository.findById(clienteDTO.indirizzoLegale().idComune()).orElseThrow(() -> new NotFoundException("Comune non trovato"));

		Indirizzo indirizzoCommerciale = new Indirizzo(
				clienteDTO.indirizzoCommerciale().via(),
				clienteDTO.indirizzoCommerciale().civico(),
				clienteDTO.indirizzoCommerciale().cap(),
				comuneIndirizzoCommerciale);

		Indirizzo indirizzoLegale = new Indirizzo(
				clienteDTO.indirizzoLegale().via(),
				clienteDTO.indirizzoLegale().civico(),
				clienteDTO.indirizzoLegale().cap(),
				comuneIndirizzoLegale);

		cliente.setRagioneSociale(clienteDTO.ragioneSociale());
		cliente.setEmail(clienteDTO.email());
		cliente.setDataInserimento(clienteDTO.dataInserimento());
		cliente.setDataUltimoContatto(clienteDTO.dataUltimoContatto());
		cliente.setPec(clienteDTO.pec());
		cliente.setNumeroDiTelefono(clienteDTO.numeroDiTelefono());
		cliente.setEmailDiContatto(clienteDTO.emailDiContatto());
		cliente.setNomeContatto(clienteDTO.nomeContatto());
		cliente.setCognomeContatto(clienteDTO.cognomeContatto());
		cliente.setTelefonoDiContatto(clienteDTO.telefonoDiContatto());
		cliente.setLogoAziendale(clienteDTO.logoAziendale());
		cliente.setTipoCliente(clienteDTO.tipoCliente());
		cliente.setIndirizzoCommerciale(indirizzoCommerciale);
		cliente.setIndirizzoLegale(indirizzoLegale);


		return clienteRepository.save(cliente);
	}

	//----------------------------------- D E L E T E  ----------------------------------------------

	// delete cliente

	public void deleteCliente(long id) {
		clienteRepository.deleteById(id);
	}

	//------------------------------------ P A T C H ----------------------------------------------

	// change logo aziendale

	public Cliente updateLogoAziendale(long id, MultipartFile file) throws IOException {
		Cliente cliente = this.findById(id);
		String logoUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
		cliente.setLogoAziendale(logoUrl);
		return clienteRepository.save(cliente);
	}

}
