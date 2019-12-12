package cn.stylefeng.guns.modular.note.rest;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.stylefeng.guns.core.FileUtil;
import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.core.UploadUtils;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.core.notice.AliyunGreen;

@RestController
@RequestMapping("/api/file")
public class ApiFileController extends ApiBaseController {

	@Resource
	private AliyunGreen aliyunGreen;
	
	@PostMapping("/upload")
	public Object uploadImage(HttpServletRequest request, @RequestParam(required = true, name = "file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new ServiceException("上传文件为空");
			}
			String absolutePath = configEntity.getAbsoluteUploadPath() + File.separator + configEntity.getImagesPath();
			FileUtil.createPath(absolutePath);
			String name = UploadUtils.uploadFile(file, absolutePath);
			String relativePath = configEntity.getImagesPath() + File.separator + name;
			String imageUrl = getImageUrl(request, relativePath);
			aliyunGreen.checkImage(imageUrl);
			return ResultGenerator.genSuccessResult(relativePath);
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@PostMapping("/checkText")
	public Object checkText(String text) {
		aliyunGreen.checkText(text);
		return ResultGenerator.genSuccessResult();
	}
}
