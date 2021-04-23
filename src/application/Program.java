package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

     /*Department obj = new Department(1, "Books");
      System.out.println(obj);
      
      Seller seller = new Seller(21, "Bob","bob@gmail.com", new Date(), 3000.0, obj);
      */
/*a classe DaoFactory é a responsavel por estanciar  o SellerDao bastando
		chamar a classe DaoFactory sem a nescessidade de acrescentar o new */
      SellerDao sellerDao = DaoFactory.createSellerDao();
      
      System.out.println("=== TEST 1: seller findById ==== ");      
      Seller seller = sellerDao.findById(3);      
      System.out.println(seller);
      
      System.out.println();
      
      System.out.println("=== TEST 2: seller findByDepartment ==== ");      
      Department department = new Department(2, null);
      
      List<Seller> list = sellerDao.findByDepartment(department);
      for(Seller obj : list) {
    	  System.out.println(obj);
      }
      
      System.out.println();
      System.out.println("=== TEST 3: seller findAll ==== ");      
       list = sellerDao.findAll();
      for(Seller obj : list) {
    	  System.out.println(obj);
      } 
      
      System.out.println();
      System.out.println("=== TEST 4: seller insert ==== "); 
      Seller newSeller = new Seller(null, "Greg", "breg@gmail.com", new Date(), 4000.0, department );
      sellerDao.insert(newSeller);
      System.out.println("Inserted! New id = " + newSeller.getId());
      
      System.out.println();
      System.out.println("=== TEST 5: seller Update ==== "); 
      seller = sellerDao.findById(1);
      seller.setName("Martha Waine");
      sellerDao.update(seller);
      System.out.println("Update completed");
      
	}

}
