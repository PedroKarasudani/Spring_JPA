package br.com.alura.spring_data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring_data.orm.Funcionario;
import br.com.alura.spring_data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	
	private final FuncionarioRepository funcionaroRepository;
	
	public RelatoriosService(FuncionarioRepository funcionaroRepository) {
		this.funcionaroRepository = funcionaroRepository;
	}
	
	public void inicia(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("0 - Sair");
			
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome desea pesquisar");
		String nome = scanner.next();
		List<Funcionario> list = funcionaroRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
}
