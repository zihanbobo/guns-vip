package cn.stylefeng.guns.modular.note.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteReward;
import cn.stylefeng.guns.modular.note.model.params.QxNoteRewardParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteRewardResult;
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
public interface QxNoteRewardService extends IService<QxNoteReward> {

    /**
     * 新增
     *
     * @author 
     * @Date 2019-12-23
     */
    void add(QxNoteRewardParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2019-12-23
     */
    void delete(QxNoteRewardParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2019-12-23
     */
    void update(QxNoteRewardParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    QxNoteRewardResult findBySpec(QxNoteRewardParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxNoteRewardResult> findListBySpec(QxNoteRewardParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2019-12-23
     */
     LayuiPageInfo findPageBySpec(QxNoteRewardParam param);

}
