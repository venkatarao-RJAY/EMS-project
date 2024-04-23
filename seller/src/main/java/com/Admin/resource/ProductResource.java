package com.Admin.resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Admin.Dto.CategoryResponseDto;
import com.Admin.Dto.CommonApiResponse;
import com.Admin.Dto.ProductResponseDto;
import com.Admin.entity.Category;
import com.Admin.entity.Product;
import com.Admin.service.ProductService;

@Service
public class ProductResource {
	@Autowired
	ProductService ps;

	public ResponseEntity<CommonApiResponse> addproduct(String name, String description, BigDecimal price, int quantity,
			String status, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException {

		CommonApiResponse api = new CommonApiResponse();
		Product p = new Product();
		p.setName(name);
		// p.setCategory(category_id);
		p.setPrice(price);
		p.setQuantity(quantity);
		p.setDescription(description);
		p.setStatus("Active");
		p.setImage1(image1.getBytes());
		p.setImage2(image2.getBytes());
		p.setImage3(image3.getBytes());

		Product add = ps.addpro(p);

		if (add == null) {
			api.setResponseMessage("missing input");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api, HttpStatus.BAD_REQUEST);
		} else
			api.setResponseMessage("product added succcessfully");
		api.setSuccess(true);
		return new ResponseEntity<CommonApiResponse>(api, HttpStatus.OK);

	}

	
	public ResponseEntity<CommonApiResponse> updateproduct(int id,String name, String description, BigDecimal price, int quantity,
			String status, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException{
		CommonApiResponse api=new CommonApiResponse();
		
		Product findbyid = ps.findbyid(id);
		
		if(findbyid==null) {
			api.setResponseMessage("product id not found");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.BAD_REQUEST);
		}
		
		//Product p = new Product();
		//p.setId(id);
		findbyid.setName(name);
		// p.setCategory(category_id);
		findbyid.setPrice(price);
		findbyid.setQuantity(quantity);
		findbyid.setDescription(description);
		findbyid.setStatus("Active");
		findbyid.setImage1(image1.getBytes());
		findbyid.setImage2(image2.getBytes());
		findbyid.setImage3(image3.getBytes());
		
		Product pp = ps.update(findbyid);
		
		if(pp==null) {
			api.setResponseMessage("failed to update product ");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.INTERNAL_SERVER_ERROR);
		}else
		api.setResponseMessage("product updated successfully");
		api.setSuccess(true);
		
		return new ResponseEntity<CommonApiResponse>(api,HttpStatus.OK);
		
	}
	
public ResponseEntity<CommonApiResponse>deletebyid(int id){
		
		CommonApiResponse api = new CommonApiResponse();
		if(id==0) {
			api.setResponseMessage("id is null");
			api.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.BAD_REQUEST);
		}
		boolean b=ps.delete(id);
		if(b) {
			api.setResponseMessage(id+" id delted successfully");
			api.setSuccess(true);
			return new ResponseEntity<CommonApiResponse>(api,HttpStatus.OK);
		}else
			api.setResponseMessage("id not found");
		api.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(api,HttpStatus.INTERNAL_SERVER_ERROR);
	}


public ResponseEntity<ProductResponseDto> findalldata() {
    ProductResponseDto dto = new ProductResponseDto();

    // Make sure ps is not null
    if (ps == null) {
        dto.setResponseMessage("Service not available");
        dto.setSuccess(false);
        return new ResponseEntity<ProductResponseDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  List<Product> catt = ps.fetchall();

    if (catt.isEmpty()) {
        dto.setResponseMessage("No data found");
        dto.setSuccess(false);
        return new ResponseEntity<ProductResponseDto>(dto, HttpStatus.NOT_FOUND);
    } else {
        dto.setproducts(catt);
        dto.setResponseMessage("Data fetched successfully");
        dto.setSuccess(true);
        return new ResponseEntity<ProductResponseDto>(dto, HttpStatus.OK);
    }
}
}
