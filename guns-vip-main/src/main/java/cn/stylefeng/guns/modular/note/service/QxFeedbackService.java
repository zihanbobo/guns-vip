package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxFeedback;
import cn.stylefeng.guns.modular.note.model.params.QxFeedbackParam;
import cn.stylefeng.guns.modular.note.model.result.QxFeedbackResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 建议反馈表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxFeedbackService extends IService<QxFeedback> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxFeedbackParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxFeedbackParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxFeedbackParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxFeedbackResult findBySpec(QxFeedbackParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxFeedbackResult> findListBySpec(QxFeedbackParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxFeedbackParam param);

}
