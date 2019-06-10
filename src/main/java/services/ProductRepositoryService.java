package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Product;
import models.User;
import repositories.ProductRepository;

@Service
public class ProductRepositoryService {
	private ProductRepository productRepository;
	
	@Autowired
	public ProductRepositoryService(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	public Iterable<Product> findAll(){
		return this.productRepository.findAll();
	}
	
	
	public Product findById(long id){
		return this.productRepository.findById(id);
	}
	
	
	public List<Product> findByOwner(User user){
		return this.productRepository.findByOwner(user);
	}
	
	
	public Product save(Product product) {
		return this.productRepository.save(product);
	}
	
	
	public boolean existsById(long id) {
		return this.productRepository.existsById(id);
	}
	
	public void deleteById(long id) {
		this.productRepository.deleteById(id);
	}
}
