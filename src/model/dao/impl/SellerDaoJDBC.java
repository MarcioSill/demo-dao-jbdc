package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;  // PreparedStatement monta consulta no banco de dados
		ResultSet rs = null; // retorna o resultado da consulta
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+" FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id"
					+" WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);
				return obj;
				
				
			}
			return null;
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;  // PreparedStatement monta consulta no banco de dados
		ResultSet rs = null; // retorna o resultado da consulta
		try {
			st = conn.prepareStatement( //busca todos os vendedores com nome do departamento
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department " // departamento interno do vendedor
					+ "ON seller.DepartmentId = department.Id " // 
					+ "ORDER BY Name");  // ordenar por nome
		
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>(); // controla a não repetição do departamet
			
			while (rs.next()) {   //while para percorrer enquanto triver dados
				
				Department dep = map.get(rs.getInt("DepartmentId")); // o map faz a pesquisa e se não tiver DepartmentId criado retorna null
				
				if(dep == null) { // se dep == a nulo não a dep não há departamento inserido
					dep = instantiateDepartment(rs); // instanciar departamento criando o departamentro
					map.put(rs.getInt("DepartmentId"), dep); // acrecenta o departamento criado no map de pesquisa
				}
				
 // se o departamento ja esistir vai instanciar o vendedor no dep existent ou no criado no teste if   
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);								
			}
			return list;
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;  // PreparedStatement monta consulta no banco de dados
		ResultSet rs = null; // retorna o resultado da consulta
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {   //while para percorrer enquanto triver dados
				
				Department dep = map.get(rs.getInt("DepartmentId")); // o map faz a pesquisa e se não tiver DepartmentId criado retorna null
				
				if(dep == null) { // se dep == a nulo não a dep
					dep = instantiateDepartment(rs); // instanciar departamento criando o departamentro
					map.put(rs.getInt("DepartmentId"), dep); // acrecenta o departamento criado no map de pesquisa
				}
				
 // se o departamento ja esistir vai instanciar o vendedor no dep existent ou no criado no teste if   
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);								
			}
			return list;
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
