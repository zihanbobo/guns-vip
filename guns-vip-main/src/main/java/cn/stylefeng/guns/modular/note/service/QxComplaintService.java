package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxComplaint;
import cn.stylefeng.guns.modular.note.model.params.QxComplaintParam;
import cn.stylefeng.guns.modular.note.model.result.QxComplaintResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 约单投诉表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxComplaintService extends IService<QxComplaint> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxComplaintParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxComplaintParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxComplaintParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxComplaintResult findBySpec(QxComplaintParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxComplaintResult> findListBySpec(QxComplaintParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxComplaintParam param);

}
