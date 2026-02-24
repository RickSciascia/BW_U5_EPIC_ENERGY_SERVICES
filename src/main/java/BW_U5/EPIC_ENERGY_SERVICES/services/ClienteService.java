package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.ClienteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ClienteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final Cloudinary cloudinary;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository, Cloudinary cloudinary) {
		this.clienteRepository = clienteRepository;
		this.cloudinary = cloudinary;
	}

	//------------------------------------- P O S T ----------------------------------------------

	// save new cliente

	public Cliente saveCliente(ClienteDTO clienteDTO) {
		this.clienteRepository.findByEmail(clienteDTO.email()).ifPresent(cliente -> {
			throw new IllegalArgumentException("Cliente already exists");
		});
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
				clienteDTO.logoAziendale(),
				clienteDTO.tipoCliente(),
				clienteDTO.indirizzoLegale(),
				clienteDTO.indirizzoCommerciale()
		);
		return clienteRepository.save(cliente);
	}

	//------------------------------------- G E T ----------------------------------------------

	// get all cliente

	public Page<Cliente> findAllClientes(int size, int page, String sortBy) {
		if (page < 0) page = 0;
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

	//------------------------------------- P U T ----------------------------------------------

	// modify cliente

	public Cliente updateCliente(long id, ClienteDTO clienteDTO) {
		Cliente cliente = this.findById(id);
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
		cliente.setIndirizzoLegale(clienteDTO.indirizzoLegale());
		cliente.setIndirizzoCommerciale(clienteDTO.indirizzoCommerciale());

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
