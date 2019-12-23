package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlack;
import cn.stylefeng.guns.modular.note.model.params.QxBlackParam;
import cn.stylefeng.guns.modular.note.model.result.QxBlackResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2019-12-23
 */
public interface QxBlackService extends IService<QxBlack> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-12-23
     */
    void add(QxBlackParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-12-23
     */
    void delete(QxBlackParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-12-23
     */
    void update(QxBlackParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    QxBlackResult findBySpec(QxBlackParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxBlackResult> findListBySpec(QxBlackParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
     LayuiPageInfo findPageBySpec(QxBlackParam param);

}
