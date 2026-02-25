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
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "ragioneSociale") String orderBY) {
		return clienteService.findAllClientes(page, size, orderBY);
	}

	@GetMapping("/{clients_id}")
	public Cliente findClienteById(@PathVariable("clients_id") long clients_id) {
		return clienteService.findById(clients_id);
	}

	//---------------------------------------- P U T ----------------------------------------------

	//-------------------------------------- D E L E T E ------------------------------------------

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
