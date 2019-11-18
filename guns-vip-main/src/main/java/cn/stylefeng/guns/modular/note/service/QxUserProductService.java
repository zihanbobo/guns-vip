package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserProduct;
import cn.stylefeng.guns.modular.note.model.params.QxUserProductParam;
import cn.stylefeng.guns.modular.note.model.result.QxUserProductResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 兑换记录表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxUserProductService extends IService<QxUserProduct> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxUserProductParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxUserProductParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxUserProductParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxUserProductResult findBySpec(QxUserProductParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxUserProductResult> findListBySpec(QxUserProductParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxUserProductParam param);

}
