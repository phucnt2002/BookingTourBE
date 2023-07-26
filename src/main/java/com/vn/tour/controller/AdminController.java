package com.vn.tour.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.ui.Model;
import com.vn.tour.entity.*;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vn.tour.service.IAdminService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bookingtour/admin")
public class AdminController {
	@Autowired
	IAdminService imAdminService;
	@PostMapping("login")
	public ResponseEntity<ResponseObject> login(@RequestBody Account account){
		return ResponseEntity.status(HttpStatus.OK).body(imAdminService.login(account.getUserName(), account.getPassword()));
	}
	@PostMapping("/createCustomer")
	public ResponseEntity<ResponseObject> createCustomer(@RequestBody Customer customer) {
		ResponseObject responseObject = imAdminService.createCustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress(), customer.getAccount());
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@PostMapping("/createTourGuide")
	public ResponseEntity<ResponseObject> createTourGuide(@RequestBody TourGuide tourGuide) {
		ResponseObject responseObject = imAdminService.createTourGuide(tourGuide.getGuideName(), tourGuide.getGuideBio(), tourGuide.getAccount());
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@PostMapping("/createTour")
	public ResponseEntity<ResponseObject> createTour(@RequestBody Tour tour) {
		ResponseObject responseObject = imAdminService.createTour(tour.getTourName(), tour.getDescription(), tour.getPrice(), tour.getDuration(),tour.getQuality(), tour.getTimeStart(), tour.getTimeEnd(), tour.getImgURL(), tour.getLocation());
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@PostMapping("/createLocation")
	public ResponseEntity<ResponseObject> createLocation(@RequestBody Location location) {
		ResponseObject responseObject = imAdminService.createLocation(location.getLocationName(), location.getAddress(), location.getCity(), location.getCountry());
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@GetMapping("/getAccount")
	public ResponseEntity<ResponseObject> getAccounts() {
		List<Account> data = imAdminService.getAllAccount();
		return data.isEmpty()
				? ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("failed", "Cannot find any account record", ""))
				: ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "Get all account successfully", data));
	}

	@GetMapping("/getAllCustomer")
	public  ResponseEntity<ResponseObject> getAllCustomer(){
		return ResponseEntity.status(HttpStatus.OK).body(imAdminService.getAllCustomer());
	}

	@GetMapping("/getAllTourGuide")
	public  ResponseEntity<ResponseObject> getAllTourGuide(){
		return ResponseEntity.status(HttpStatus.OK).body(imAdminService.getAllTourGuide());
	}

	@PutMapping("/blockAccount/{id}")
	public ResponseEntity<ResponseObject> blockAccount(@PathVariable("id") Long id){
		ResponseObject responseObject = imAdminService.blockAccount(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@PutMapping("/unBlockAccount/{id}")
	public ResponseEntity<ResponseObject> unBlockAccount(@PathVariable("id") Long id){
		ResponseObject responseObject = imAdminService.unBlockAccount(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@GetMapping("/getAllTour")
	public ResponseEntity<ResponseObject> getAllTour(){
		ResponseObject responseObject = imAdminService.getAllTour();
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

	@GetMapping("/getAllLocation")
	public ResponseEntity<ResponseObject> getAllLocation(){
		ResponseObject responseObject = imAdminService.getAllLocation();
		return ResponseEntity.status(HttpStatus.OK).body(responseObject);
	}

    @PostMapping("/v1/image")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        // Kiểm tra xem file có tồn tại không.
        String UPLOAD_DIR = "uploads/";
        if (file.isEmpty()) {
            throw new Exception("The file is empty.");
        }

        try {
            // Lưu file.
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (Exception e) {
            throw new Exception("Failed to store the image.", e);
        }
    }

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("image")MultipartFile multipartFile,
							 Model model) throws IOException {
		String imageURL = imAdminService.uploadFile(multipartFile);
		model.addAttribute("imageURL",imageURL);
		return imageURL;
	}

}
