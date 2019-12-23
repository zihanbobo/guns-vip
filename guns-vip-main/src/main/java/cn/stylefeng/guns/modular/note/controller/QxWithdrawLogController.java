package cn.stylefeng.guns.modular.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.constant.ProjectConstants.WITHDRAW_STATUS;
import cn.stylefeng.guns.core.exception.ServiceException;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.entity.QxWithdrawLog;
import cn.stylefeng.guns.modular.note.model.params.QxWithdrawLogParam;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import cn.stylefeng.guns.modular.note.service.QxWithdrawLogService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 平台提现表控制器
 *
 * @author 
 * @Date 2019-11-30 17:47:46
 */
@Controller
@RequestMapping("/qxWithdrawLog")
public class QxWithdrawLogController extends BaseController {

    private String PREFIX = "/note/qxWithdrawLog";

    @Autowired
    private QxWithdrawLogService qxWithdrawLogService;
    
    @Autowired
    private QxUserService qxUserService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxWithdrawLog.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxWithdrawLog_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxWithdrawLog_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxWithdrawLogParam qxWithdrawLogParam) {
        this.qxWithdrawLogService.add(qxWithdrawLogParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(QxWithdrawLogParam qxWithdrawLogParam) {
        this.qxWithdrawLogService.update(qxWithdrawLogParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(QxWithdrawLogParam qxWithdrawLogParam) {
        this.qxWithdrawLogService.delete(qxWithdrawLogParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(QxWithdrawLogParam qxWithdrawLogParam) {
        QxWithdrawLog detail = this.qxWithdrawLogService.getById(qxWithdrawLogParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2019-11-30
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(QxWithdrawLogParam qxWithdrawLogParam) {
        return this.qxWithdrawLogService.findPageBySpec(qxWithdrawLogParam);
    }

    @RequestMapping("/handleWithdraw")
    @ResponseBody
    public ResponseData handleWithdraw(Long id) {
    	QxWithdrawLog withdrawLog = this.qxWithdrawLogService.getById(id);
    	if (withdrawLog.getStatus().equals(WITHDRAW_STATUS.WAIT_OUT)) {
    		QxUser user = qxUserService.getById(withdrawLog.getUserId());
        	this.qxWithdrawLogService.updateWithdrawSuccess(withdrawLog, user, withdrawLog.getCoinCount());
        	return ResponseData.success();
    	} else {
    		throw new ServiceException("不能操作提现");
    	}
    }
}


