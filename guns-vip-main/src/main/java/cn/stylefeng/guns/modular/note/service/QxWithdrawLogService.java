package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxWithdrawLog;
import cn.stylefeng.guns.modular.note.model.params.QxWithdrawLogParam;
import cn.stylefeng.guns.modular.note.model.result.QxWithdrawLogResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 平台提现表 服务类
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxWithdrawLogService extends IService<QxWithdrawLog> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-11-18
     */
    void add(QxWithdrawLogParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-11-18
     */
    void delete(QxWithdrawLogParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-11-18
     */
    void update(QxWithdrawLogParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    QxWithdrawLogResult findBySpec(QxWithdrawLogParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxWithdrawLogResult> findListBySpec(QxWithdrawLogParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-11-18
     */
     LayuiPageInfo findPageBySpec(QxWithdrawLogParam param);

}
