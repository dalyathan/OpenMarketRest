package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.Comment;
import models.Product;
import repositories.CommentRepository;
import repositories.ProductRepository;
import services.CommentRepositoryService;
import services.ProductRepositoryService;

@RestController
@RequestMapping(path="/comment",produces="application/json",consumes="application/json")
@CrossOrigin(origins="*")
public class CommentController {
	private CommentRepositoryService commentRepositoryService;
	private ProductRepositoryService productRepsitoryService;
	
	
	@Autowired
	public CommentController(CommentRepositoryService commentRepositoryService,ProductRepositoryService productRepsitoryService) {
		this.commentRepositoryService=commentRepositoryService;
		this.productRepsitoryService=productRepsitoryService;
	}
	
	
	//from retrofit
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteComment(@PathVariable("id") long id) {
		if(this.commentRepositoryService.existsById(id)) {
			this.commentRepositoryService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/save/{product_id}")
	public ResponseEntity<Object> saveComment(@RequestBody retrofitclassesmapper.Comment comment,
			@PathVariable("product_id") long id) {
		models.Comment commentEntity=new Comment(comment);
		commentEntity.setProduct(this.productRepsitoryService.findById(id));
		this.commentRepositoryService.save(commentEntity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<List<retrofitclassesmapper.Comment>> getCommentByUsername(@PathVariable("username") String username){
		List<Comment> comments=this.commentRepositoryService.findByUserName(username);
		if(comments!=null) {
			List<retrofitclassesmapper.Comment> mappedComments=new ArrayList<>();
			for(Comment comment:comments) {
				mappedComments.add(new retrofitclassesmapper.Comment(comment));
			}
			return new ResponseEntity<>(mappedComments,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateComment(@RequestBody retrofitclassesmapper.Comment comment,@PathVariable("id") long id) {
		Optional<Comment> possibleComment=this.commentRepositoryService.findById(id);
		if(possibleComment.isPresent()) {
			Comment storedComment=possibleComment.get();
			storedComment.updateComment(comment);
			this.commentRepositoryService.save(storedComment);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<retrofitclassesmapper.Comment> getCommentById(@PathVariable("id") long id) {
		Optional<Comment> possibleComment=this.commentRepositoryService.findById(id);
		if(possibleComment.isPresent()) {
			return new ResponseEntity<>(new retrofitclassesmapper.Comment(possibleComment.get()),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/products/{product_id}")
	public ResponseEntity<List<retrofitclassesmapper.Comment>> getCommentsForProduct(@PathVariable("product_id") long id){
		Product product=this.productRepsitoryService.findById(id);
		if(product==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Comment> comments=this.commentRepositoryService.findByProduct(product);
		if(comments==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<retrofitclassesmapper.Comment> mappedComments=new ArrayList<>();
		for(Comment comment:comments) {
			mappedComments.add(new retrofitclassesmapper.Comment(comment));
		}
		return new ResponseEntity<>(mappedComments,HttpStatus.OK);
	}
}
