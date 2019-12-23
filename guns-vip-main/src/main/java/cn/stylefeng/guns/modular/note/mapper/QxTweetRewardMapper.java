package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxTweetReward;
import cn.stylefeng.guns.modular.note.model.params.QxTweetRewardParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetRewardResult;
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
public interface QxTweetRewardMapper extends BaseMapper<QxTweetReward> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxTweetRewardResult> customList(@Param("paramCondition") QxTweetRewardParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxTweetRewardParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<QxTweetRewardResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxTweetRewardParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxTweetRewardParam paramCondition);

}
