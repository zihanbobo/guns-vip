package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCategory;
import cn.stylefeng.guns.modular.note.model.params.QxCategoryParam;
import cn.stylefeng.guns.modular.note.model.result.QxCategoryResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品品类表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxCategoryService extends IService<QxCategory> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxCategoryParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxCategoryParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxCategoryParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxCategoryResult findBySpec(QxCategoryParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxCategoryResult> findListBySpec(QxCategoryParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxCategoryParam param);

}
