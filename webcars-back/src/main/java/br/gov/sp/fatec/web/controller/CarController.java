package br.gov.sp.fatec.web.controller;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.service.CarroService;
import br.gov.sp.fatec.web.request.CarroRequest;

@RestController
public class CarController {

	@Autowired
	private CarroService carroService;

	@RequestMapping(path = "/car", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Collection<Carro>> getAll() {
		ResponseEntity<Collection<Carro>> response = null;
		try {
			List<Carro> result = new ArrayList<Carro>(carroService.todos());
			if (result != null && !result.isEmpty()) {
				response = new ResponseEntity<Collection<Carro>>(result, HttpStatus.OK);
			} else {
				response = new ResponseEntity<Collection<Carro>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Collection<Carro>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(path = "/car/{carId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Carro> getCar(@PathVariable("carId") Long carId) {
		ResponseEntity<Carro> response = null;
		try {
			Carro result = carroService.getCarById(carId);
			if (result != null) {
				response = new ResponseEntity<Carro>(result, HttpStatus.OK);
			} else {
				response = new ResponseEntity<Carro>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Carro>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(path = "/car", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("isAuthenticated()")
	public @ResponseBody ResponseEntity<Carro> postCar(@RequestBody CarroRequest carroRequest) {
		ResponseEntity<Carro> response = null;
		try {

			boolean result = carroService.salvar(carroRequest.getPlaca(), carroRequest.getMarca(),
					carroRequest.getModelo());
			if (result) {
				response = new ResponseEntity<Carro>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<Carro>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<Carro>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	public CarroService getCarroService() {
		return carroService;
	}

	public void setCarroService(CarroService carroService) {
		this.carroService = carroService;
	}

}
