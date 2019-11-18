package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCooperate;
import cn.stylefeng.guns.modular.note.model.params.QxCooperateParam;
import cn.stylefeng.guns.modular.note.model.result.QxCooperateResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商务合作 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxCooperateService extends IService<QxCooperate> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxCooperateParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxCooperateParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxCooperateParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxCooperateResult findBySpec(QxCooperateParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxCooperateResult> findListBySpec(QxCooperateParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxCooperateParam param);

}
