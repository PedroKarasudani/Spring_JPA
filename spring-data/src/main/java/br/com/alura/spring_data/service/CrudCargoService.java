package br.com.alura.spring_data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring_data.orm.Cargo;
import br.com.alura.spring_data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicia(Scanner scanner) {
		salvar(scanner);
	}
	
	public void salvar(Scanner scanner) {
		System.out.print("Qual nome do cargo: ");
		String nomeCargo = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(nomeCargo);
		cargoRepository.save(cargo);
		System.out.println("Salvo!");
	}
	
}
