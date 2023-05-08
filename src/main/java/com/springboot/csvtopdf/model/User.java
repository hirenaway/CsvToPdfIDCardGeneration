package com.springboot.csvtopdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String name;

	private String designation;

	private String phoneNumber;

	private String email;

	private String image;

}
