package cn.stylefeng.guns.modular.note.rest;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.stylefeng.guns.core.FileUtil;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.UploadUtils;
import cn.stylefeng.guns.core.exception.ServiceException;

@RestController
@RequestMapping("/api/file")
public class ApiFileController extends ApiBaseController {

	@RequestMapping("/upload")
	public Object uploadImage(@RequestParam(required = true, name = "file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new ServiceException("上传文件为空");
			}
			String absolutePath = configEntity.getAbsoluteUploadPath() + File.separator + configEntity.getImagesPath();
			FileUtil.createPath(absolutePath);
			String name = UploadUtils.uploadFile(file, absolutePath);
			String relativePath = configEntity.getImagesPath() + File.separator + name;
			return ResultGenerator.genSuccessResult(relativePath);
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
