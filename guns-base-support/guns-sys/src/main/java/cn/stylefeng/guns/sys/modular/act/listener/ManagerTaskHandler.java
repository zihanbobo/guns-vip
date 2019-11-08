package cn.stylefeng.guns.sys.modular.act.listener;

import cn.stylefeng.guns.sys.core.act.cache.TempAssignCache;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 指定下一任务待办人
 *
 * @author fengshuonan
 * @Date 2019-08-28 15:39
 */
public class ManagerTaskHandler implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        TempAssignCache.set(delegateTask.getId());

    }

}

