package cn.stylefeng.guns.modular.note.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCostRate;
import cn.stylefeng.guns.modular.note.model.params.QxCostRateParam;
import cn.stylefeng.guns.modular.note.model.result.QxCostRateResult;

/**
 * <p>
 * 费用比例表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-28
 */
public interface QxCostRateService extends IService<QxCostRate> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-28
     */
    void add(QxCostRateParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-28
     */
    void delete(QxCostRateParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-28
     */
    void update(QxCostRateParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-28
     */
    QxCostRateResult findBySpec(QxCostRateParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-28
     */
    List<QxCostRateResult> findListBySpec(QxCostRateParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-28
     */
     LayuiPageInfo findPageBySpec(QxCostRateParam param);
     
     /**
      * 根据类型，获取比例
      * @param type
      * @return
      */
     BigDecimal getRateByType(String type);

}
