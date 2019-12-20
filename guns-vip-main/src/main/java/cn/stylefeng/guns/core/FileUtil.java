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
	

    /**
     * 判断文件大小
     *
     * @param len
     *            文件长度
     * @param size
     *            限制大小
     * @param unit
     *            限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
