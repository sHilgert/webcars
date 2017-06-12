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
	
	public boolean salvar(String placa, String marca, String modelo){
		Carro carro = new Carro();
		carro.setMarca(marca);
		carro.setModelo(modelo);
		carro.setPlaca(placa);		
		try {
			carroRepository.save(carro);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public Carro getCarById(Long id){
		return carroRepository.findOne(id);
	}

}
