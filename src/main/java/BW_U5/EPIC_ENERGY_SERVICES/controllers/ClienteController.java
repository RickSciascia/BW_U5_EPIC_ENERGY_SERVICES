package BW_U5.EPIC_ENERGY_SERVICES.controllers;


import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.ClienteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClienteController {

	private final ClienteService clienteService;

// QUERY PARAMETERS

	// JPASPECIFICATION
	JpaSpecificationExecutor<Cliente> clienteSpecification;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	//------------------------------------ P O S T  ----------------------------------------------

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
	public Cliente save(@RequestBody @Validated ClienteDTO clienteDTO) {
		return this.clienteService.saveCliente(clienteDTO);
	}


	//------------------------------------- G E T -----------------------------------------------
	@GetMapping
	@PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
	public Page<Cliente> findAllClientes(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "ragioneSociale") String orderBy) {
		return clienteService.findAllClientes(page, size, orderBy);
	}

	@GetMapping("/{clients_id}")
	@PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
	public Cliente findClienteById(@PathVariable long clients_id) {
		return clienteService.findById(clients_id);
	}

	@GetMapping("/filter")
	public List<Cliente> filterClienti(
			@RequestParam(required = false) String ragioneSociale,
			@RequestParam(required = false) Double fatturatoAnnuale,
			@RequestParam(required = false) LocalDate dataInserimentoInizio,
			@RequestParam(required = false) LocalDate dataInserimentoFine,
			@RequestParam(required = false) LocalDate dataUltimoContattoInizio,
			@RequestParam(required = false) LocalDate dataUltimoContattoFine,
			@RequestParam(required = false) String cognomeContatto
	) {
		return clienteService.filtriDinamici(
				ragioneSociale,
				fatturatoAnnuale,
				dataInserimentoInizio,
				dataInserimentoFine,
				dataUltimoContattoInizio,
				dataUltimoContattoFine,
				cognomeContatto

		);
	}

	//---------------------------------------- P U T ----------------------------------------------
	@PutMapping("/{clients_id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cliente updateCliente(@PathVariable("clients_id") long clients_id, ClienteDTO clienteDTO) {
		return clienteService.updateCliente(clients_id, clienteDTO);
	}


	//-------------------------------------- D E L E T E ------------------------------------------

	@DeleteMapping("/{clients_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteCliente(@PathVariable("clients_id") long clients_id) {
		clienteService.deleteCliente(clients_id);
	}

	//--------------------------------------- P A T C H --------------------------------------------

	// change logo aziendale -->

	@PatchMapping("/{clients_id}/logo")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cliente uploadLogo(@RequestParam("logoAziendale") MultipartFile file, @PathVariable long clients_id) {
		System.out.println(file.getOriginalFilename());
		try {
			return clienteService.updateLogoAziendale(clients_id, file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}