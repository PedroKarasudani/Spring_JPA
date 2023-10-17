package br.com.alura.spring_data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring_data.orm.UnidadeTrabalho;
import br.com.alura.spring_data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private boolean system = true;
	
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository  unidadeTrabalhoRepository ) {
		this.unidadeTrabalhoRepository  = unidadeTrabalhoRepository ;
	}
	
	public void inicia(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Vizualizar");
			System.out.println("4 - Deletar");
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
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	public void salvar(Scanner scanner) {
		System.out.print("Qual a Descricao: ");
		String descricao = scanner.next();
		System.out.print("Qual o endereco: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo!");
	}
	
	public void atualizar(Scanner scanner) {
		System.out.print("Id: ");
		Integer id = scanner.nextInt();
		System.out.print("Qual a Descricao: ");
		String descricao = scanner.next();
		System.out.print("Qual o endereco: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Atualizado!");
	}
	
	public void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));		
	}
	
	public void deletar(Scanner scanner) {
		System.out.print("Id: ");
		Integer id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado!");
	}

}
