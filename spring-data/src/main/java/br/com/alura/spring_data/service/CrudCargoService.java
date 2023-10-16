package br.com.alura.spring_data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring_data.orm.Cargo;
import br.com.alura.spring_data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private boolean system = true;
	
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicia(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Vizualizar");
			System.out.println("0 - Sair");
			
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	public void salvar(Scanner scanner) {
		System.out.print("Qual nome do cargo: ");
		String nomeCargo = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(nomeCargo);
		cargoRepository.save(cargo);
		System.out.println("Salvo!");
	}
	
	public void atualizar(Scanner scanner) {
		System.out.print("Id: ");
		Integer id = scanner.nextInt();
		System.out.print("Qual nome do cargo: ");
		String nome = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(nome);
		cargoRepository.save(cargo);
		System.out.println("Atualizado!");
	}
	
	public void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));		
	}

}
