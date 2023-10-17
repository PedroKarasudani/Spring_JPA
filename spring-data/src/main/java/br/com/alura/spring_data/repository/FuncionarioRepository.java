package br.com.alura.spring_data.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import br.com.alura.spring_data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository <Funcionario, Integer>{
	
}
