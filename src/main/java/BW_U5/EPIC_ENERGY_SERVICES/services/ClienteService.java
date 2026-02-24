package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.ClienteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	//------------------------------------- P O S T ----------------------------------------------


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

	public Page<Cliente> findAllClientes(int size, int page, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		if (sortBy == null) sortBy = "name";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return clienteRepository.findAll(pageable);
	}

	public Cliente findById(long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente con id " + id + " non trovato"));
	}

	public Cliente findByEmail(String email) {
		return clienteRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Cliente con email " + email + " non trovato"));
	}

}
