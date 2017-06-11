package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.repository.CarroRepository;

@Service("carroService")
@Transactional
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	public List<Carro> todos() {
		List<Carro> carros = new ArrayList<Carro>();
		for (Carro carro : carroRepository.findAll()) {
			carros.add(carro);
		}
		return carros;
	}
	
	public Carro getCarById(Long id){
		return carroRepository.findOne(id);
	}

}
