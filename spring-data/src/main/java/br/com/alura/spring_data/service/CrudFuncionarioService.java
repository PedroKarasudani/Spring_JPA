package br.com.alura.spring_data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring_data.orm.Funcionario;
import br.com.alura.spring_data.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
	
	private boolean system = true;
	
	private final FuncionarioRepository funcionarioRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
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
		System.out.print("Qual nome do Funcionario: ");
		String nomeFuncionario = scanner.next();
		System.out.print("Qual o CPF: ");
		String cpfFuncionario = scanner.next();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nomeFuncionario);
		funcionario.setCpf(cpfFuncionario);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo!");
	}
	
	public void atualizar(Scanner scanner) {
		System.out.print("Id: ");
		Integer id = scanner.nextInt();
		System.out.print("Qual nome do Funcionario: ");
		String nome = scanner.next();
		System.out.print("Qual o CPF: ");
		String cpf = scanner.next();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Atualizado!");
	}
	
	public void visualizar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));		
	}
	
	public void deletar(Scanner scanner) {
		System.out.print("Id: ");
		Integer id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado!");
	}

}
