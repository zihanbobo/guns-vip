package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNotDisturb;
import cn.stylefeng.guns.modular.note.model.params.QxNotDisturbParam;
import cn.stylefeng.guns.modular.note.model.result.QxNotDisturbResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 消息免打扰表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-14
 */
public interface QxNotDisturbService extends IService<QxNotDisturb> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-14
     */
    void add(QxNotDisturbParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-14
     */
    void delete(QxNotDisturbParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-14
     */
    void update(QxNotDisturbParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    QxNotDisturbResult findBySpec(QxNotDisturbParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
    List<QxNotDisturbResult> findListBySpec(QxNotDisturbParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-14
     */
     LayuiPageInfo findPageBySpec(QxNotDisturbParam param);

}
