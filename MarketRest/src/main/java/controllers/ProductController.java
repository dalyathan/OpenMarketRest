package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.Product;
import models.User;
import repositories.ProductRepository;
import repositories.UserRepository;

@RestController
@RequestMapping(path="/products",produces="application/json")
@CrossOrigin(origins="*")
public class ProductController {
	private ProductRepository productRepository;
	private UserRepository userRepository;
	@Autowired
	public ProductController(ProductRepository productRepository
			,UserRepository userRepository) {
		this.productRepository=productRepository;
		this.userRepository=userRepository;
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product>>  getAllProducts(){
		List<Product> allProducts=new ArrayList<Product>();
		this.productRepository.findAll().forEach(product->allProducts.add(product));
		return new ResponseEntity<>(allProducts,HttpStatus.OK);
	}
	@GetMapping("/{name}")
	public ResponseEntity<List<Product>> 
		getAllProductsBy(@PathVariable("name") String name){
		return null;
	}
	//--------------MOHAMMEDS----
	@PostMapping("/save/{username}")
	public ResponseEntity saveProduct(@RequestBody Product product,@PathVariable("username") String username) {
		User user=this.userRepository.findByUsername(username);
		if(user==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		user.getMyProducts().add(product);
		this.userRepository.save(user);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	@PostMapping("/save_id/{user_id}")
	public ResponseEntity saveProductWithId(@RequestBody Product product,@PathVariable("user_id") long id) {
		Optional<User> optionalUser=this.userRepository.findById(id);
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			user.getMyProducts().add(product);
			this.userRepository.save(user);
			return new ResponseEntity(HttpStatus.CREATED);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductId(@PathVariable("id") long id) {
		Product product=this.productRepository.findById(id);
		if(product!=null) {
			return new ResponseEntity<>(product,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
	}
	@GetMapping("username/{username}")
	public ResponseEntity<List<Product>> getProductsByUsername(@PathVariable("username") String username){
		User user=this.userRepository.findByUsername(username);
		if(user!=null) {
			List<Product> listOfProducts=this.productRepository.findByOwner(user);
			if(listOfProducts!=null) {
				return new ResponseEntity<>(listOfProducts,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity updateProduct(@RequestBody retrofitclassesmapper.Product product,@PathVariable("id") long id) {
		Product fromDb=this.productRepository.findById(product.getId());
		if(fromDb!=null) {
			fromDb.updateProduct(product);
			this.productRepository.save(fromDb);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteProduct(@PathVariable("id") long id) {
		if(this.productRepository.existsById(id)) {
			this.productRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	@GetMapping("/users/{user_id}")
	public ResponseEntity<List<Product>> getProductsFroUser(@PathVariable("user_id") long id){
		Optional<User> optionalUser=this.userRepository.findById(id);
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			if(user!=null) {
				List<Product> productsOfUser=this.productRepository.findByOwner(user);
				return new ResponseEntity<>(productsOfUser,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
