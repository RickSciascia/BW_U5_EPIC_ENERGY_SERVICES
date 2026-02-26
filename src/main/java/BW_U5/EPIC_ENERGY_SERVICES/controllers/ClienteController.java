package BW_U5.EPIC_ENERGY_SERVICES.controllers;


import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.ClienteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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


	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	//------------------------------------ P O S T  ----------------------------------------------

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody @Validated ClienteDTO clienteDTO) {
		return this.clienteService.saveCliente(clienteDTO);
	}


	//------------------------------------- G E T -----------------------------------------------
	@GetMapping
	public Page<Cliente> findAllClientes(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "ragioneSociale") String orderBy) {
		return clienteService.findAllClientes(page, size, orderBy);
	}

	@GetMapping("/{clients_id}")
	public Cliente findClienteById(@PathVariable long clients_id) {
		return clienteService.findById(clients_id);
	}

	@GetMapping("/fatturato_annuale/{fatturatoAnnuale}")
	public List<Cliente> getByFatturatoAnnuale(@PathVariable double fatturatoAnnuale) {
		List<Cliente> cliente = clienteService.findByFatturatoAnnuale(fatturatoAnnuale);
		if (cliente.isEmpty()) {
			throw new NotFoundException("Cliente non trovato");
		}
		return cliente;
	}

	@GetMapping("/ragione_sociale/{ragioneSociale}")
	public List<Cliente> getByRagioneSociale(@PathVariable String ragioneSociale) {
		List<Cliente> cliente = clienteService.findByRagioneSociale(ragioneSociale);
		if (cliente.isEmpty()) {
			throw new NotFoundException("Cliente non trovato");
		}
		return cliente;
	}

	@GetMapping("/clients{dataInserimento}/{dataInserimento2}")
	public List<Cliente> getByDataInserimento(@PathVariable LocalDate dataInserimento, @PathVariable LocalDate dataInserimento2) {
		List<Cliente> cliente = clienteService.findByDataInserimento(dataInserimento, dataInserimento2);
		if (cliente.isEmpty()) {
			throw new NotFoundException("Cliente non trovato");
		}
		return cliente;
	}

	@GetMapping("/data_ultimocontatto/{dataUltimoContatto}/{dataUltimoContatto2}")
	public List<Cliente> getByDataUltimoContatto(@PathVariable LocalDate dataUltimoContatto, @PathVariable LocalDate dataUltimoContatto2) {
		List<Cliente> cliente = clienteService.findByDataUltimoContatto(dataUltimoContatto, dataUltimoContatto2);
		if (cliente.isEmpty()) {
			throw new NotFoundException("Cliente non trovato");
		}
		return cliente;
	}


	//---------------------------------------- P U T ----------------------------------------------
	@PutMapping("/{clients_id}")
	@ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
	public Cliente updateCliente(@PathVariable("clients_id") long clients_id, ClienteDTO clienteDTO) {
		return clienteService.updateCliente(clients_id, clienteDTO);
	}


	//-------------------------------------- D E L E T E ------------------------------------------

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@RequestParam long id) {
		clienteService.deleteCliente(id);
	}

	//--------------------------------------- P A T C H --------------------------------------------

	// change logo aziendale -->

	@PatchMapping("/{clients_id}/logo")
	public Cliente uploadLogo(@RequestParam("logoAziendale") MultipartFile file, @PathVariable long clients_id) {
		System.out.println(file.getOriginalFilename());
		try {
			return clienteService.updateLogoAziendale(clients_id, file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}