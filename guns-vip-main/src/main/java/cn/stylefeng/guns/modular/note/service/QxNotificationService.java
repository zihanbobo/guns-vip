package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNotification;
import cn.stylefeng.guns.modular.note.model.params.QxNotificationParam;
import cn.stylefeng.guns.modular.note.model.result.QxNotificationResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统通知表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
public interface QxNotificationService extends IService<QxNotification> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-14
     */
    void add(QxNotificationParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-14
     */
    void delete(QxNotificationParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-14
     */
    void update(QxNotificationParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    QxNotificationResult findBySpec(QxNotificationParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    List<QxNotificationResult> findListBySpec(QxNotificationParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
     LayuiPageInfo findPageBySpec(QxNotificationParam param);

}
