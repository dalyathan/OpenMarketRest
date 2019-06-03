package repositories;

import org.springframework.data.repository.CrudRepository;

import models.Product;
import models.Rating;

public interface RatingRepository extends CrudRepository<Long,Rating>{
	Rating findByProduct(Product product);
}
