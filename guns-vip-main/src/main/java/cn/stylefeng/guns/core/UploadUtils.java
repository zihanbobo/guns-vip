package cn.stylefeng.guns.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.stylefeng.guns.core.exception.ServiceException;

public class UploadUtils {

	public static String uploadFile(MultipartFile file, String absolutePath) throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
		String extension = FilenameUtils.getExtension(fileName);
		String name = DateUtils.getCurrentTime(DateUtils.YYYY_MM_DD_HH_MM_SS_SSS_PATTERN) + UUIDGenerator.get32UUID()
				+ "." + extension;
		String path = absolutePath + File.separator + name;
		File filePath = new File(path);
		if (filePath.exists()) {
			if (!filePath.delete()) {
				throw new ServiceException("删除" + filePath + "失败！");
			}
		}
		file.transferTo(filePath);
		return name;
	}

	public static String getFileName(String filePath) {
		return filePath.substring(filePath.lastIndexOf("/") + 1);
	}

	public static String getFileNameWithoutSubfix(String filePath) {
		return filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
	}

	public static void main(String[] args) {
		String path = "/Users/a1111/sourceCode/upload/whgj/1/hptFiles.png";
		System.out.println(getFileNameWithoutSubfix(path));
	}
}
