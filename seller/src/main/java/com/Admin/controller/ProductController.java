package com.Admin.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Admin.Dto.CommonApiResponse;
import com.Admin.Dto.ProductResponseDto;
import com.Admin.resource.ProductResource;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductResource resource;
	
	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addpro(@RequestParam("name") String name, @RequestParam String description,
			@RequestParam BigDecimal price, @RequestParam int quantity, @RequestParam String status,
			@RequestParam MultipartFile image1, @RequestParam MultipartFile image2,
			@RequestParam MultipartFile image3) throws IOException{
		return resource.addproduct(name, description, price, quantity, status, image1, image2, image3);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<CommonApiResponse> updatepro(@RequestParam int id,@RequestParam("name") String name, @RequestParam String description,
			@RequestParam BigDecimal price, @RequestParam int quantity, @RequestParam String status,
			@RequestParam MultipartFile image1, @RequestParam MultipartFile image2,
			@RequestParam MultipartFile image3) throws IOException{
		return resource.updateproduct(id, name, description, price, quantity, status, image1, image2, image3);
	}
	
	@GetMapping(value = "/allproducts")
	public ResponseEntity<ProductResponseDto>findall(){
		return resource.findalldata();
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<CommonApiResponse> delete(@RequestParam int id){
		return resource.deletebyid(id);
	}
}
