package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import models.Product;
import models.User;

public interface ProductRepository extends CrudRepository<Product,Long>{
	Product findById(long id);
	List<Product> findByName(String name);
	List<Product> findByOwner(User user);
}
