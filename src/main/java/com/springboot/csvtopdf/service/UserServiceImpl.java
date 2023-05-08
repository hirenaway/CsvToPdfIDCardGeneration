package com.springboot.csvtopdf.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.csvtopdf.model.User;

/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */

@Service
public class UserServiceImpl implements UserService {

	/**
	 * 
	 * @author Hiren Solanki
	 *
	 * @param file 
	 * 
	 * @throws IOException
	 *
	 *
	 * @since 1.0
	 *
	 */
	@Override
	public List<User> parseCsv(MultipartFile file) throws IOException {
		List<User> users = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] data = StringUtils.commaDelimitedListToStringArray(line);
			if (data.length == 5) {
				String name = data[0];
				String designation = data[1];
				String phoneNumber = data[2];
				String email = data[3];
				String image = data[4];

				User user = new User(name, designation, phoneNumber, email, image);
				users.add(user);
			}
		}
		return users;
	}

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
	@Override
	public byte[] generateIdCards(List<User> users) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();

			for (User user : users) {
				PdfContentByte cb = writer.getDirectContent();
				Image image = Image.getInstance(user.getImage());
				image.scaleAbsolute(120, 120);
				image.setAbsolutePosition(50, 600);
				cb.addImage(image);

				cb.beginText();
				cb.moveText(200, 600);
				cb.setFontAndSize(BaseFont.createFont(), 14);
				cb.showText(user.getName());
				cb.moveText(0, -20);
				cb.showText(user.getDesignation());
				cb.moveText(0, -20);
				cb.showText(user.getPhoneNumber());
				cb.moveText(0, -20);
				cb.showText(user.getEmail());
				cb.endText();

				document.newPage();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
		return outputStream.toByteArray();
	}

}
