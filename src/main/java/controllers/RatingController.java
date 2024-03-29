package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.Product;
import models.Rating;
import repositories.RatingRepository;
import services.RatingRepositoryService;

@RestController
@RequestMapping(path="/rating",produces="application/json")
@CrossOrigin(origins="*")
public class RatingController {
	private RatingRepositoryService ratingRepositoryService;
	@Autowired
	public RatingController(RatingRepositoryService ratingRepositoryService) {
		this.ratingRepositoryService=ratingRepositoryService;
	}
	@GetMapping("/product")
	public Rating getRating(@RequestParam("product") Product product) {
		return this.ratingRepositoryService.findByProduct(product);
	}
}
