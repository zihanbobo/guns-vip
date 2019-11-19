package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxDateType;
import cn.stylefeng.guns.modular.note.model.params.QxDateTypeParam;
import cn.stylefeng.guns.modular.note.model.result.QxDateTypeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2019-11-19
 */
public interface QxDateTypeService extends IService<QxDateType> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-19
     */
    void add(QxDateTypeParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-19
     */
    void delete(QxDateTypeParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-19
     */
    void update(QxDateTypeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-19
     */
    QxDateTypeResult findBySpec(QxDateTypeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-19
     */
    List<QxDateTypeResult> findListBySpec(QxDateTypeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-19
     */
     LayuiPageInfo findPageBySpec(QxDateTypeParam param);

}
