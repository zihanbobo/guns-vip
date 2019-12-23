package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxNoteLike;
import cn.stylefeng.guns.modular.note.model.params.QxNoteLikeParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteLikeResult;
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
public interface QxNoteLikeMapper extends BaseMapper<QxNoteLike> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<QxNoteLikeResult> customList(@Param("paramCondition") QxNoteLikeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxNoteLikeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<QxNoteLikeResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxNoteLikeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-12-23
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxNoteLikeParam paramCondition);

}
