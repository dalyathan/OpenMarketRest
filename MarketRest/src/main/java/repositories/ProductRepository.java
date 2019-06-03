package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import models.Product;

public interface ProductRepository extends CrudRepository<Long,Product>{
	Product findById(long id);
	List<Product> findByName(String name);
}
