package br.com.alura.spring_data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.spring_data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository <Funcionario, Integer>{
	List<Funcionario> findByNome(String nome);
	
	//Derived Query funciona, mas nome ficou dificil de leitura
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);
	
	//ultilizando JPQL fica assim
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(@Param("nome") String nome,@Param("salario") Double salario,@Param("data") LocalDate data);
	
	//ultilizando uma busca direto do bando, native query
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(@Param("data") LocalDate data);
}
