package com.springboot.csvtopdf.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.csvtopdf.model.User;

/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
public interface UserService {

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param file
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 *
	 */
	List<User> parseCsv(MultipartFile file) throws IOException;

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param users
	 * 
	 * @throws IOException 
	 *
	 * @since 1.0
	 *
	 */
	byte[] generateIdCards(List<User> users) throws IOException;
}
