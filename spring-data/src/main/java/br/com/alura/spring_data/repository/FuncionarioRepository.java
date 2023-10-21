package br.com.alura.spring_data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring_data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository <Funcionario, Integer>{
	List<Funcionario> findByNome(String nome);
	
	//Derived Query funciona, mas nome ficou dificil de leitura
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);
	
	//ultilizando JPQL fica assim
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nomeZ AND f.salario >= :salarioZ AND f.dataContratacao = :dataZ")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nomeZ, Double salarioZ, LocalDate dataZ);
}
