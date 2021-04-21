package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	
	void insert(Department obj); //reponsavel por inserir obj no banco de dados.
	void update(Department obj); //atualiza o banco de dados
	void deleteById(Integer id); //deleta o obj id
	Department findById(Integer id); /* reponsavel por pegar o id e consultar o banco de dados se existir
	irá retornar se não existir retornará nullo*/
	List<Department> findAll(); // responsavel a presentar todos elementos do Department	

}
