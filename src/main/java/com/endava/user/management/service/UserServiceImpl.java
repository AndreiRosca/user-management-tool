package com.endava.user.management.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.endava.user.management.domain.Address;
import com.endava.user.management.domain.Framework;
import com.endava.user.management.domain.Gender;
import com.endava.user.management.domain.User;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.form.CreateUserForm;
import com.endava.user.management.web.form.UpdateUserForm;
import com.endava.user.management.web.util.FileUtil;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User create(User user) {
		return userRepository.create(user);
	}

	@Override
	public User update(User user) {
		return userRepository.update(user);
	}

	@Override
	public void delete(long userId) {
		userRepository.delete(userId);
	}

	@Override
	public User create(CreateUserForm userForm, ServletContext context) {
		String cvFilePath = persistCvFile(context, userForm);
		return create(buildUserFromRequest(userForm, cvFilePath));
	}

	private String persistCvFile(ServletContext context, CreateUserForm userForm) {
		FileUtil util = new FileUtil(context);
		String cvFilePath = util.persistCvFile(userForm.getCvFile());
		return cvFilePath;
	}
	
	private User buildUserFromRequest(CreateUserForm userForm, String cvFilePath) {
		User user = User.newBuilder()
				.setName(userForm.getName())
				.setEmail(userForm.getEmail())
				.setSex(Gender.valueOf(userForm.getGender().toUpperCase()))
				.setCvFilePath(cvFilePath)
				.setCvFileContent(getCvFileContent(userForm.getCvFile()))
				.setFrameworks(getFormFrameworks(userForm))
				.setBirthDate(parseBirthdate(userForm.getBirthDate()))
				.setAddress(Address.newBuilder()
						.setCountry(userForm.getCountry())
						.setCity(userForm.getCity())
						.setState(userForm.getState())
						.setZipCode(userForm.getZipCode())
						.build())
				.build();
		return user;
	}

	private Date parseBirthdate(String birthDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			return format.parse(birthDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] tryGetCvFileContent(Part filePart) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = filePart.getInputStream();
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = in.read(buffer)) != -1)
			out.write(buffer, 0, bytesRead);
		return out.toByteArray();
	}

	private byte[] getCvFileContent(Part filePart) {
		try {
			return tryGetCvFileContent(filePart);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Framework> getFormFrameworks(CreateUserForm userForm) {
		return userForm.getFrameworks()
				.stream()
				.map(Framework::new)
				.collect(Collectors.toList());
	}

	@Override
	public User update(UpdateUserForm userForm) {
		return update(buildUserFromRequest(userForm));
	}

	private User buildUserFromRequest(UpdateUserForm userForm) {
		User oldUser = findById(Long.valueOf(userForm.getId()));
		User user = User.newBuilder()
				.setId(Long.valueOf(userForm.getId()))
				.setName(userForm.getName())
				.setEmail(userForm.getEmail())
				.setSex(Gender.valueOf(userForm.getGender().toUpperCase()))
				.setFrameworks(getFormFrameworks(userForm))
				.setBirthDate(parseBirthdate(userForm.getBirthDate()))
				.setCvFilePath(oldUser.getCvFilePath())
				.setCvFileContent(oldUser.getCvFileContent())
				.setAddress(Address.newBuilder()
						.setCountry(userForm.getCountry())
						.setCity(userForm.getCity())
						.setState(userForm.getState())
						.setZipCode(userForm.getZipCode())
						.build())
				.build();
		return user;
	}

	private List<Framework> getFormFrameworks(UpdateUserForm userForm) {
		return userForm.getFrameworks()
				.stream()
				.map(Framework::new)
				.collect(Collectors.toList());
	}
}
