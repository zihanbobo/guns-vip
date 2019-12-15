package cn.stylefeng.guns.modular.note.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.dto.QxTweetCommentTo;
import cn.stylefeng.guns.modular.note.entity.QxTweet;
import cn.stylefeng.guns.modular.note.entity.QxTweetComment;
import cn.stylefeng.guns.modular.note.mapper.QxTweetCommentMapper;
import cn.stylefeng.guns.modular.note.mapper.QxTweetMapper;
import cn.stylefeng.guns.modular.note.model.params.QxTweetCommentParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetCommentResult;
import  cn.stylefeng.guns.modular.note.service.QxTweetCommentService;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 推文评论表 服务实现类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
@Service
public class QxTweetCommentServiceImpl extends ServiceImpl<QxTweetCommentMapper, QxTweetComment> implements QxTweetCommentService {

	@Resource
	private QxTweetMapper qxTweetMapper;
	
    @Override
    public void add(QxTweetCommentParam param){
        QxTweetComment entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(QxTweetCommentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(QxTweetCommentParam param){
        QxTweetComment oldEntity = getOldEntity(param);
        QxTweetComment newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public QxTweetCommentResult findBySpec(QxTweetCommentParam param){
        return null;
    }

    @Override
    public List<QxTweetCommentResult> findListBySpec(QxTweetCommentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(QxTweetCommentParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(QxTweetCommentParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private QxTweetComment getOldEntity(QxTweetCommentParam param) {
        return this.getById(getKey(param));
    }

    private QxTweetComment getEntity(QxTweetCommentParam param) {
        QxTweetComment entity = new QxTweetComment();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

	@Override
	public void addComment(Long requestUserId, QxTweetCommentTo commentTo) {
		QxTweetComment tweetComment = new QxTweetComment();
		BeanUtils.copyProperties(commentTo, tweetComment);
		tweetComment.setCreatedBy(requestUserId);
		this.baseMapper.insert(tweetComment);
		// 推文评论数量+1
		QxTweet tweet = qxTweetMapper.selectById(commentTo.getTweetId());
		tweet.setCommentCount(tweet.getCommentCount()+1);
		qxTweetMapper.updateById(tweet);
	}

}
