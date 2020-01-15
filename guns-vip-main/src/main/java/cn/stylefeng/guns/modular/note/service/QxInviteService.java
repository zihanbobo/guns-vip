package cn.stylefeng.guns.modular.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.dto.QxInviteCommentTo;
import cn.stylefeng.guns.modular.note.dto.QxInviteQueryTo;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteResult;
import cn.stylefeng.guns.modular.note.pojo.QxInviteSearchPojo;
import cn.stylefeng.guns.modular.note.pojo.QxInviteUserPojo;

/**
 * <p>
 * 约单表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxInviteService extends IService<QxInvite> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxInviteParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxInviteParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxInviteParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxInviteResult findBySpec(QxInviteParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxInviteResult> findListBySpec(QxInviteParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxInviteParam param);

     /**
      * 发起邀请
      * @param requestUserId
      * @param inviteTo
      */
	void addInvite(Long requestUserId, QxInviteTo inviteTo);

	/**
	 * 报名约单
	 * @param currentUserId
	 * @param inviteId
	 */
	void apply(Long currentUserId, Long inviteId);

	/**
	 * 列出约单所有报名人
	 * @param inviteId
	 * @return
	 */
	List<QxInviteUserPojo> applicants(Long inviteId);
	
	/**
	 * 选中报名人
	 * @param inviteId
	 * @param userId
	 */
	void choose(Long inviteId, Long userId);

	/**
	 * 同意邀请
	 * @param inviteId
	 */
	void agree(Long inviteId);

	/**
	 * 拒绝邀请
	 * @param inviteId
	 */
	void reject(Long inviteId);

	/**
	 * 获得当前约单
	 * @param requestUserId
	 * @return
	 */
	List<QxInvite> getCurrentInvites(Page page, Long requestUserId);

	/**
	 * 确认约单开始
	 * @param inviteId
	 * @param requestUserId
	 */
	void start(Long inviteId, Long requestUserId);

	/**
	 * 确认约单结束
	 * @param inviteId
	 * @param requestUserId
	 */
	void finish(Long inviteId, Long requestUserId);

	/**
	 * 投诉约单
	 * @param inviteId
	 * @param reason
	 */
	void complaint(Long inviteId, Long userId, String reason);

	/**
	 * 评价约单
	 * @param commentTo
	 */
	void comment(Long userId, QxInviteCommentTo commentTo);

	/**
	 * 紧急联系
	 * @param invite
	 * @param string
	 */
	void alert(Long userId, String emergencyContact, QxInvite invite);

	/**
	 * 搜索约单列表
	 * @param inviteQueryTo
	 * @return
	 */
	Page<List<QxInviteSearchPojo>> search(Page page, QxInviteQueryTo inviteQueryTo);

	/**
	 * 我发起的约单
	 * @param id
	 * @return
	 */
	int getMyInviteCount(Long userId);

	/**
	 * 邀请我的约单
	 * @param id
	 * @return
	 */
	int getInviteMeCount(Long userId);

	/**
	 * 取消约单
	 * @param requestUserId
	 * @param inviteId
	 */
	void cancel(Long requestUserId, Long inviteId);

	/**
	 * 获取约单详情
	 * @param id
	 * @return
	 */
	QxInviteSearchPojo getInviteById(Long id);

	/**
	 * 关闭未配对约单
	 */
	void closeWaitInvite();

}
