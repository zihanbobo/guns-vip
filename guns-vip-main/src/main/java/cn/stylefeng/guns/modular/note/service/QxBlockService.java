package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlock;
import cn.stylefeng.guns.modular.note.model.params.QxBlockParam;
import cn.stylefeng.guns.modular.note.model.result.QxBlockResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 消息免打扰表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-20
 */
public interface QxBlockService extends IService<QxBlock> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-20
     */
    void add(QxBlockParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-20
     */
    void delete(QxBlockParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-20
     */
    void update(QxBlockParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-20
     */
    QxBlockResult findBySpec(QxBlockParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-20
     */
    List<QxBlockResult> findListBySpec(QxBlockParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-20
     */
     LayuiPageInfo findPageBySpec(QxBlockParam param);

}
