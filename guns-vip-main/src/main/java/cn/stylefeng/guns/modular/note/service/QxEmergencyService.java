package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxEmergency;
import cn.stylefeng.guns.modular.note.model.params.QxEmergencyParam;
import cn.stylefeng.guns.modular.note.model.result.QxEmergencyResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 紧急联系人 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxEmergencyService extends IService<QxEmergency> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxEmergencyParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxEmergencyParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxEmergencyParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxEmergencyResult findBySpec(QxEmergencyParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxEmergencyResult> findListBySpec(QxEmergencyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxEmergencyParam param);
     
     /**
      * 获取默认紧急联系人
      * @param userId
      * @return
      */
     QxEmergency getDefaultEmergency(Long userId);

}
