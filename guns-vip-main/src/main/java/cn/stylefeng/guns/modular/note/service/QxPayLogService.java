package cn.stylefeng.guns.modular.note.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxPayLog;
import cn.stylefeng.guns.modular.note.model.params.QxPayLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxPayLogResult;

/**
 * <p>
 * 用户支付流水表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxPayLogService extends IService<QxPayLog> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxPayLogParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxPayLogParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxPayLogParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxPayLogResult findBySpec(QxPayLogParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxPayLogResult> findListBySpec(QxPayLogParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxPayLogParam param);
}
