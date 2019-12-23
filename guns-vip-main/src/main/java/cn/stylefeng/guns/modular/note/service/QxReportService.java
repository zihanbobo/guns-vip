package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxReport;
import cn.stylefeng.guns.modular.note.model.params.QxReportParam;
import cn.stylefeng.guns.modular.note.model.result.QxReportResult;
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
public interface QxReportService extends IService<QxReport> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-12-23
     */
    void add(QxReportParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-12-23
     */
    void delete(QxReportParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-12-23
     */
    void update(QxReportParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    QxReportResult findBySpec(QxReportParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxReportResult> findListBySpec(QxReportParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
     LayuiPageInfo findPageBySpec(QxReportParam param);

}
