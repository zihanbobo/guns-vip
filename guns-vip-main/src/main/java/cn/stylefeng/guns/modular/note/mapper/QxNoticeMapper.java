package cn.stylefeng.guns.modular.note.mapper;

import cn.stylefeng.guns.modular.note.entity.QxNotice;
import cn.stylefeng.guns.modular.note.model.params.QxNoticeParam;
import cn.stylefeng.guns.modular.note.model.result.QxNoticeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统通知表  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-11-16
 */
public interface QxNoticeMapper extends BaseMapper<QxNotice> {

    /**
     * 获取列表
     *
     * @author 
     * @Date 2019-11-16
     */
    List<QxNoticeResult> customList(@Param("paramCondition") QxNoticeParam paramCondition);

    /**
     * 获取map列表
     *
     * @author 
     * @Date 2019-11-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") QxNoticeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author 
     * @Date 2019-11-16
     */
    Page<QxNoticeResult> customPageList(@Param("page") Page page, @Param("paramCondition") QxNoticeParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author 
     * @Date 2019-11-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") QxNoticeParam paramCondition);

}
