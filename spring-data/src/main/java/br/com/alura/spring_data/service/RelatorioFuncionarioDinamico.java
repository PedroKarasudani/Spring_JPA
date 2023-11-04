package br.com.alura.spring_data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring_data.orm.Funcionario;
import br.com.alura.spring_data.repository.FuncionarioRepository;
import br.com.alura.spring_data.specification.SpecificationFuncionario;

public class RelatorioFuncionarioDinamico {
	private final FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("Digite um nome: ");
		String nome = scanner.next();
		if(nome.equalsIgnoreCase("NULL")) {
			nome=null;
		}
		
		System.out.println("Digite um cpf: ");
		String cpf = scanner.next();
		if(nome.equalsIgnoreCase("NULL")) {
			cpf=null;
		}
		
		System.out.println("Digite um salario: ");
		Double salario  = scanner.nextDouble();
		if(salario == 0) {
			salario=null;
		}
		
		System.out.println("Digite uma data de contratacao: ");
		String data  = scanner.next();
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification.where(SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						);
		funcionarios.forEach(System.out::println);
		
	}
}
