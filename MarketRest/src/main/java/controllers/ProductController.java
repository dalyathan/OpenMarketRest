package controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	@GetMapping("/{id}")
	public @ResponseBody Product getProductByid(@PathVariable("id") long id) {
		return null;
	}
	@GetMapping("/")
	public @ResponseBody List<Product> getAllProducts(){
		return null;
	}
	@GetMapping("/{name}")
	public @ResponseBody List<Product> 
		getAllProductsBy(@PathVariable("name") String name){
		return null;
	}
}
