package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxAlert;
import cn.stylefeng.guns.modular.note.model.params.QxAlertParam;
import cn.stylefeng.guns.modular.note.model.result.QxAlertResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 约单报警记录 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxAlertService extends IService<QxAlert> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxAlertParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxAlertParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxAlertParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxAlertResult findBySpec(QxAlertParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxAlertResult> findListBySpec(QxAlertParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxAlertParam param);

}
