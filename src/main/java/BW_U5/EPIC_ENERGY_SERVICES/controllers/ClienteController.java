package BW_U5.EPIC_ENERGY_SERVICES.controllers;


import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
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


	//------------------------------------- G E T ----------------------------------------------
	@GetMapping
	public Page<Cliente> findAllClientes(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "ragioneSociale") String orderBY) {
		return clienteService.findAllClientes(page, size, orderBY);
	}

	@GetMapping("/{clients_id}")
	public Cliente findClienteById(@PathVariable("clients_id") long clients_id) {
		return clienteService.findById(clients_id);
	}

	@GetMapping("/clients/fatturato_annuale")
	public List<Cliente> getByFatturatoAnnuale(@RequestParam double fatturatoAnnuale) {
		return clienteService.findByFatturatoAnnuale(fatturatoAnnuale);
	}

	@GetMapping("/clients/ragione_sociale")
	public List<Cliente> getByRagioneSociale(@RequestParam String ragioneSociale) {
		return clienteService.findByRagioneSociale(ragioneSociale);
	}

	@GetMapping("/clients/{data_inserimento}/{data_inserimento2}")
	public List<Cliente> getByDataInserimento(@RequestParam LocalDate dataInserimento, @RequestParam LocalDate dataInserimento2) {
		return clienteService.findByDataInserimento(dataInserimento, dataInserimento2);
	}

	@GetMapping("/clients/{data_ultimocontatto}/{data_inserimento2}")
	public List<Cliente> getByDataUltimoContatto(@RequestParam LocalDate dataUltimoContatto, @RequestParam LocalDate dataUltimoContatto2) {
		return clienteService.findByDataUltimoContatto(dataUltimoContatto, dataUltimoContatto2);
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

	//--------------------------------------- P A T C H -------------------------------------------

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