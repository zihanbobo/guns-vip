package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxTweetLike;
import cn.stylefeng.guns.modular.note.model.params.QxTweetLikeParam;
import cn.stylefeng.guns.modular.note.model.result.QxTweetLikeResult;
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
public interface QxTweetLikeMapper extends BaseMapper<QxTweetLike> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxTweetLikeResult> customList(@Param("paramCondition") QxTweetLikeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxTweetLikeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<QxTweetLikeResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxTweetLikeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxTweetLikeParam paramCondition);

}
