package com.endava.user.management.web.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class FileUtil {
	private ServletContext context;

	public FileUtil(ServletContext context) {
		this.context = context;
	}

	private String saveFile(String baseDir, String namePrefix, Part filePart) {
		try {
			return trySaveFile(baseDir, namePrefix, filePart);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String trySaveFile(String baseDir, String namePrefix, Part filePart) throws IOException {
		String fileName = Long.toString(new Date().getTime()) + (int) (Math.random() * Integer.MAX_VALUE);
		String fileWebPath = namePrefix + File.separator + fileName + File.separator + filePart.getSubmittedFileName();
		String filePath = context.getRealPath(baseDir) + File.separator + fileWebPath;
		File file = new File(filePath);
		File parent = file.getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
			out.write(getFileBytes(filePart));
		}
		return fileWebPath;
	}

	private byte[] getFileBytes(Part filePart) throws IOException {
		InputStream in = filePart.getInputStream();
		byte[] buffer = new byte[4096];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int bytesRead;
		while ((bytesRead = in.read(buffer)) != -1)
			out.write(buffer, 0, bytesRead);
		return out.toByteArray();
	}

	public String persistCvFile(Part filePart) {
		String namePrefix = UUID.randomUUID().toString();
		return saveFile("/resources/files/cv", namePrefix, filePart);
	}
}
