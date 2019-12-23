package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxNote;
import cn.stylefeng.guns.modular.note.model.params.QxNoteParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoteResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 私密日记 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-18
 */
public interface QxNoteMapper extends BaseMapper<QxNote> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<QxNoteResult> customList(@Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<QxNoteResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxNoteParam paramCondition);

    /**
     * 获得打赏用户列表
     * @param page
     * @param noteId
     * @return
     */
	Page rewardUsers(@Param("page") Page page, @Param("noteId") Long noteId);

}
