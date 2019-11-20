package cn.stylefeng.guns.modular.note.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.stylefeng.guns.core.ResultGenerator;
import cn.stylefeng.guns.modular.note.entity.QxBlock;
import cn.stylefeng.guns.modular.note.service.QxBlockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/chat")
public class ApiChatController extends ApiBaseController {

	@Resource
	private QxBlockService qxBlockService;
	
	@RequestMapping("/checkBlock")
	public Object check(Long blockUserId) {
		QueryWrapper<QxBlock> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).eq("block_user_id", blockUserId);
		int count = qxBlockService.count(queryWrapper);
		log.info("/api/chat/checkBlock, blockUserId=" + blockUserId);
		return ResultGenerator.genSuccessResult(count > 0);
	}
	
	@RequestMapping("/addBlock")
	public Object addBlock(Long blockUserId) {
		QxBlock qxBlock = new QxBlock();
		qxBlock.setUserId(getRequestUserId());
		qxBlock.setBlockUserId(blockUserId);
		qxBlockService.save(qxBlock);
		log.info("/api/chat/addBlock, blockUserId=" + blockUserId);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/removeBlock")
	public Object removeBlock(Long blockUserId) {
		QueryWrapper<QxBlock> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", getRequestUserId()).eq("block_user_id", blockUserId);
		qxBlockService.remove(queryWrapper);
		log.info("/api/chat/removeBlock, blockUserId=" + blockUserId);
		return ResultGenerator.genSuccessResult();
	}
}
