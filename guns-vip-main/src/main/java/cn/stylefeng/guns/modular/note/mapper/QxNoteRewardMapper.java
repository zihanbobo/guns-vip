package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxNoteReward;
import cn.stylefeng.guns.modular.note.model.params.QxNoteRewardParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteRewardResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-12-23
 */
public interface QxNoteRewardMapper extends BaseMapper<QxNoteReward> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxNoteRewardResult> customList(@Param("paramCondition") QxNoteRewardParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxNoteRewardParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<QxNoteRewardResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxNoteRewardParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxNoteRewardParam paramCondition);

}
