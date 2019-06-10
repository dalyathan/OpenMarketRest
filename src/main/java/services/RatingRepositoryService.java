package services;

import org.springframework.stereotype.Service;

import models.Product;
import models.Rating;
import repositories.RatingRepository;

@Service
public class RatingRepositoryService {

	private RatingRepository ratingRepository;
	
	public RatingRepositoryService(RatingRepository ratingRepository) {
		this.ratingRepository=ratingRepository;
	}
	
	
	public Rating findByProduct(Product product) {
		return this.ratingRepository.findByProduct(product);
	}
}
