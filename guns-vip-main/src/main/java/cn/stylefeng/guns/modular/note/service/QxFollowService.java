package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxFollow;
import cn.stylefeng.guns.modular.note.model.params.QxFollowParam;
import cn.stylefeng.guns.modular.note.model.result.QxFollowResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户关注关系表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxFollowService extends IService<QxFollow> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxFollowParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxFollowParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxFollowParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxFollowResult findBySpec(QxFollowParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxFollowResult> findListBySpec(QxFollowParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxFollowParam param);

     /**
      * 关注用户
      * @param followerId
      * @param followeeId
      */
	void follow(Long followerId, long followeeId);

}
