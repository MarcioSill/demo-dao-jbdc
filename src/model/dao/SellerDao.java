package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	
	void insert(Seller obj); //reponsavel por inserir obj no banco de dados.
	void update(Seller obj); //atualiza o banco de dados
	void deleteById(Integer id); //deleta o obj id
	Seller findById(Integer id); /* reponsavel por pegar o id e consultar o banco de dados se existir
	irá retornar se não existir retornará nullo*/
	List<Seller> findAll(); // responsavel a presentar todos elementos do Seller	

}
