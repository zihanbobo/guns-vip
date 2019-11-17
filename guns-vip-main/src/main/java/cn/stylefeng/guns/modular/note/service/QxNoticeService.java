package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNotice;
import cn.stylefeng.guns.modular.note.model.params.QxNoticeParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoticeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统通知表  服务类
 * </p>
 *
 * @author 
 * @since 2019-11-16
 */
public interface QxNoticeService extends IService<QxNotice> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-16
     */
    void add(QxNoticeParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-16
     */
    void delete(QxNoticeParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-16
     */
    void update(QxNoticeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-16
     */
    QxNoticeResult findBySpec(QxNoticeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-16
     */
    List<QxNoticeResult> findListBySpec(QxNoticeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-16
     */
     LayuiPageInfo findPageBySpec(QxNoticeParam param);

}
