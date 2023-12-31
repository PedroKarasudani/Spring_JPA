package br.com.alura.spring_data.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring_data.orm.Funcionario;
import br.com.alura.spring_data.orm.FuncionarioProjecao;
import br.com.alura.spring_data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionaroRepository;
	
	public RelatoriosService(FuncionarioRepository funcionaroRepository) {
		this.funcionaroRepository = funcionaroRepository;
	}
	
	public void inicia(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("2 - Busca funcionario nome, data contratacao e salario maior");
			System.out.println("3 - Busca funcionario data contratacao");
			System.out.println("4 - Pesquisa funcionario salario");
			System.out.println("0 - Sair");
			
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
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
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar");
		String nome = scanner.next();
		System.out.println("Qual salario deseja pesquisar");
		Double salario = scanner.nextDouble();
		System.out.println("Qual data contratacao deseja pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionaroRepository.findByNomeAndSalarioGreaterThanAndDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual data contratacao deseja pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionaroRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
		
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionaroRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario ID: " + f.getId() + " | Nome: " + f.getNome() + " | Salario: " + f.getSalario()));
			}
}