package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNote;
import cn.stylefeng.guns.modular.note.model.params.QxNoteParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteResult;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 私密日记 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxNoteService extends IService<QxNote> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxNoteParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxNoteParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxNoteParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxNoteResult findBySpec(QxNoteParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxNoteResult> findListBySpec(QxNoteParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxNoteParam param);

 	/**
 	 * 打赏日记
 	 * @param requestUserId
 	 * @param userId
 	 * @param noteId
 	 * @param giftId
 	 */
 	void rewardNote(Long requestUserId, Long userId, Long noteId, Long giftId);

 	/**
 	 * 解锁日记
 	 * @param requestUserId
 	 * @param userId
 	 * @param noteId
 	 * @param giftId
 	 */
 	void unlockNote(Long requestUserId, Long userId, Long noteId, Long giftId);

 	/**
 	 * 点赞日记
 	 * @param requestUserId
 	 * @param noteId
 	 * @return 
 	 */
	QxNote like(Long requestUserId, Long noteId);

	/**
	 * 获得日记打赏用户
	 * @param page
	 * @param noteId
	 */
	Page rewardUsers(Page page, Long noteId);

	/**
	 * 列出日记列表
	 * @param page
	 * @param requestUserId
	 * @param keywords
	 */
	Page listNotes(Page page, Long requestUserId, String keywords);

	/**
	 * 我的关注的人日记
	 * @param page
	 * @param requestUserId
	 * @param keywords 
	 * @return
	 */
	Page followList(Page page, Long requestUserId, String keywords);
}
