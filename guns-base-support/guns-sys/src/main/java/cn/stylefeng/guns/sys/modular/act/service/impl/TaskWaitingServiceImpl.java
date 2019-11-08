package cn.stylefeng.guns.sys.modular.act.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.act.entity.Task;
import cn.stylefeng.guns.sys.modular.act.mapper.TaskWaitingMapper;
import cn.stylefeng.guns.sys.modular.act.model.params.TaskParam;
import cn.stylefeng.guns.sys.modular.act.model.result.TaskResult;
import cn.stylefeng.guns.sys.modular.act.service.TaskWaitingService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-08-19
 */
@Service
public class TaskWaitingServiceImpl extends ServiceImpl<TaskWaitingMapper, Task> implements TaskWaitingService {

    @Override
    public void add(TaskParam param) {
        Task entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public String getUserRoleString() {
        String account = LoginContextHolder.getContext().getUser().getAccount();
        String userRoleString = this.baseMapper.getUserRoleString(account);
        StringBuilder result = new StringBuilder("(");

        if (ToolUtil.isEmpty(userRoleString)) {
            return String.valueOf(result.append("''").append(")"));
        }

        if (userRoleString.contains(",")) {
            String[] split = userRoleString.trim().split(",");
            for (int i = 0; i < split.length; i++) {
                if (i < split.length - 1) {
                    result.append("'").append(split[i]).append("'").append(",");
                } else {
                    result.append("'").append(split[i]).append("'").append(")");
                }
            }
            return String.valueOf(result);
        } else if (ToolUtil.isNotEmpty(userRoleString)) {
            return String.valueOf(result.append("'").append(userRoleString).append("'").append(")"));
        }
        return "('')";
    }

    @Override
    public void delete(TaskParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(TaskParam param) {
        Task oldEntity = getOldEntity(param);
        Task newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TaskResult findBySpec(TaskParam param) {
        return null;
    }

    @Override
    public List<TaskResult> findListBySpec(TaskParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TaskParam param) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("keywords", param.getKeywords());
        paramMap.put("lastStart", param.getLastStart());
        paramMap.put("lastEnd", param.getLastEnd());
        if (ToolUtil.isNotEmpty(param.getLastStart())) {
            paramMap.put("lastStart", param.getLastStart() + "00:00:00");
        }
        if (ToolUtil.isNotEmpty(param.getLastEnd())) {
            paramMap.put("lastEnd", param.getLastEnd() + "00:00:00");
        }

        paramMap.put("USERNAME", LoginContextHolder.getContext().getUser().getAccount());
        String userRoleString = this.getUserRoleString();
        paramMap.put("RNUMBERS", userRoleString);

        Page pageContext = getPageContext();
        List<Map<String, Object>> maps = this.baseMapper.datalistPage(pageContext, paramMap);
        pageContext.setRecords(maps);

        return LayuiPageFactory.createPageInfo(pageContext);
    }

    private Serializable getKey(TaskParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Task getOldEntity(TaskParam param) {
        return this.getById(getKey(param));
    }

    private Task getEntity(TaskParam param) {
        Task entity = new Task();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
