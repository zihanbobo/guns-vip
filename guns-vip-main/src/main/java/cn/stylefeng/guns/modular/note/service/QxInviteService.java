package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.dto.QxInviteTo;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.model.result.QxInviteResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
	 * 选中报名人
	 * @param inviteId
	 * @param userId
	 */
	void choose(Long inviteId, Long userId);

}
