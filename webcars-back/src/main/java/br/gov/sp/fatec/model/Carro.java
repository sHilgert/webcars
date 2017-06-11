package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAR_CARRO")
public class Carro {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
	private Long id;
	
	@Column(name = "CAR_MARCA")
	private String marca;
	
	@Column(name = "CAR_PLACA")
	private String placa;
	
	@Column(name = "CAR_MODELO")
	private String modelo;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
}
