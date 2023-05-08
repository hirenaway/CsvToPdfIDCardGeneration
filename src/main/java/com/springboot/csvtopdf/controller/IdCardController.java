package com.springboot.csvtopdf.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.csvtopdf.model.User;
import com.springboot.csvtopdf.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("/api")
public class IdCardController {

	private UserService userService;

	@Autowired
	public IdCardController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Generate ID cards from CSV file", consumes = "multipart/form-data")
	@PostMapping("/generate-id-cards")
	public ResponseEntity<byte[]> generateIdCards(
			@ApiParam(value = "CSV file containing user data") @RequestParam("file") @NotNull MultipartFile file)
			throws IOException {
		List<User> users = userService.parseCsv(file);
		byte[] pdfBytes = userService.generateIdCards(users);
		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"id-cards.pdf\"")
				.body(pdfBytes);
	}
}
