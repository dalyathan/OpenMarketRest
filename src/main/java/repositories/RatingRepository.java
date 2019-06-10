package repositories;

import org.springframework.data.repository.CrudRepository;

import models.Product;
import models.Rating;

public interface RatingRepository extends CrudRepository<Rating,Long>{
	Rating findByProduct(Product product);
}
