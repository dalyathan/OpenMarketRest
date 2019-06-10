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
import services.ProductRepositoryService;
import services.UserRepositoryService;

@RestController
@RequestMapping(path="/products",produces="application/json")
@CrossOrigin(origins="*")
public class ProductController {
	private ProductRepositoryService productRepositoryService;
	private UserRepositoryService userRepositorySerive;
	
	
	@Autowired
	public ProductController(ProductRepositoryService productRepositoryService
			,UserRepositoryService userRepositorySerive) {
		this.productRepositoryService=productRepositoryService;
		this.userRepositorySerive=userRepositorySerive;
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<List<retrofitclassesmapper.Product>>  getAllProducts(){
		List<retrofitclassesmapper.Product> allProducts=new ArrayList<retrofitclassesmapper.Product>();
		this.productRepositoryService.findAll().forEach(product->allProducts.add(new retrofitclassesmapper.Product(product)));
		return new ResponseEntity<>(allProducts,HttpStatus.OK);
	}
	
	
	@PostMapping("/save/{username}")
	public ResponseEntity saveProduct(@RequestBody Product product,@PathVariable("username") String username) {
		User user=this.userRepositorySerive.findByUsername(username);
		if(user==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		user.getMyProducts().add(product);
		this.userRepositorySerive.save(user);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
	@PostMapping("/save_id/{user_id}")
	public ResponseEntity saveProductWithId(@RequestBody Product product,@PathVariable("user_id") long id) {
		Optional<User> optionalUser=this.userRepositorySerive.findById(id);
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			user.getMyProducts().add(product);
			this.userRepositorySerive.save(user);
			return new ResponseEntity(HttpStatus.CREATED);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<retrofitclassesmapper.Product> getProductId(@PathVariable("id") long id) {
		Product product=this.productRepositoryService.findById(id);
		if(product!=null) {
			return new ResponseEntity<>(new retrofitclassesmapper.Product(product),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
	}
	
	
	@GetMapping("username/{username}")
	public ResponseEntity<List<retrofitclassesmapper.Product>> getProductsByUsername(@PathVariable("username") String username){
		User user=this.userRepositorySerive.findByUsername(username);
		if(user!=null) {
			List<Product> listOfProducts=this.productRepositoryService.findByOwner(user);
			if(listOfProducts!=null) {
				List<retrofitclassesmapper.Product> mappedProduts=new ArrayList<>();
				listOfProducts.forEach(product->mappedProduts.add(new retrofitclassesmapper.Product(product)));
				return new ResponseEntity<>(mappedProduts,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity updateProduct(@RequestBody retrofitclassesmapper.Product product,@PathVariable("id") long id) {
		Product fromDb=this.productRepositoryService.findById(product.getId());
		if(fromDb!=null) {
			fromDb.updateProduct(product);
			this.productRepositoryService.save(fromDb);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteProduct(@PathVariable("id") long id) {
		if(this.productRepositoryService.existsById(id)) {
			this.productRepositoryService.deleteById(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/users/{user_id}")
	public ResponseEntity<List<retrofitclassesmapper.Product>> getProductsForUser(@PathVariable("user_id") long id){
		Optional<User> optionalUser=this.userRepositorySerive.findById(id);
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			if(user!=null) {
				List<Product> productsOfUser=this.productRepositoryService.findByOwner(user);
				if(productsOfUser!=null) {
					List<retrofitclassesmapper.Product> mappedProducts=new ArrayList<>();
					productsOfUser.forEach(product->new retrofitclassesmapper.Product(product));
					return new ResponseEntity<>(mappedProducts,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
