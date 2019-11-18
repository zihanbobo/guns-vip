package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxPlatformLog;
import cn.stylefeng.guns.modular.note.model.params.QxPlatformLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxPlatformLogResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 平台流水表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxPlatformLogService extends IService<QxPlatformLog> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxPlatformLogParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxPlatformLogParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxPlatformLogParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxPlatformLogResult findBySpec(QxPlatformLogParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxPlatformLogResult> findListBySpec(QxPlatformLogParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxPlatformLogParam param);

}
