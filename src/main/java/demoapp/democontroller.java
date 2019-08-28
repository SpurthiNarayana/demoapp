package demoapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class democontroller {
	 @Autowired
	demorepository demo;
	 @GetMapping("/")
	 public ResponseEntity<List<Articles>> allarticles(){
		 List<Articles> articles=demo.findAll();
//		 System.out.println(articles.toString());
//		 System.out.println(ResponseEntity.status(200).body(articles));
		return ResponseEntity.status(200).body(articles);
		 
	 }
//	 @GetMapping
//	    public ResponseEntity<List<article>> getAllStudents() {
//	        List<article> allStudents = demo.findAll();
//	        return ResponseEntity.status(200).body(allStudents);
//	    }

//	 @GetMapping("/h")
//	public String sayhello() {
//		return "hello";
//	}
	 @GetMapping("/{id}")
	 public ResponseEntity<Optional<Articles>> onearticle(@PathVariable("id")Integer id){
		 if (demo.existsById(id))
				 {
		return ResponseEntity.status(200).body(demo.findById(id));
		 }
		 else {
			 return ResponseEntity.status(404).build();
		 }
	 }
	 
	 @PostMapping(path = "/")
	 public ResponseEntity addarticle(@RequestBody Articles article) {
		return ResponseEntity.status(201).body(demo.save(article)); 

		 }
	 @PutMapping(path = "/{id}")
		 public ResponseEntity changearticle(@RequestBody Articles article,@PathVariable int id) {
		 if(demo.existsById(id)==true) {
			 return ResponseEntity.status(200).body( demo.save(article));
		 }
		 else {
			 return ResponseEntity.status(404).build();
		 }
		
	
	 }
	 @DeleteMapping(path = "/{id}")
	public boolean deletearticle(@PathVariable("id") Integer id) {
		 boolean response=false;
		 if(demo.existsById(id)) {
			 demo.deleteById(id);
			 response=true;
		 }
	 return response;
	 }

}
