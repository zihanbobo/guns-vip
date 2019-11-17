package cn.stylefeng.guns.core;

import java.io.File;
import java.io.IOException;

import cn.stylefeng.guns.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	
	public static String mergeDeployPath(String relativePath) {
		try {
			String path = new File(".").getCanonicalPath() + File.separator + relativePath;
			return createPath(path).toString();
		} catch (IOException e) {
			throw new ServiceException("创建" + relativePath + "失败！");
		}
	}

	public static File createPath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				throw new ServiceException("创建" + file + "失败！");
			}
			log.info("创建:" + file + "成功！"); 
		}
		return file;
	}
}
