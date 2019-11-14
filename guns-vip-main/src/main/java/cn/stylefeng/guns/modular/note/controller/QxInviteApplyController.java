package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteApply;
import cn.stylefeng.guns.modular.note.model.params.QxInviteApplyParam;
import cn.stylefeng.guns.modular.note.service.QxInviteApplyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 约单报名控制器
 *
 * @author 
 * @Date 2019-11-14 13:32:52
 */
@Controller
@RequestMapping("/qxInviteApply")
public class QxInviteApplyController extends BaseController {

    private String PREFIX = "/note/qxInviteApply";

    @Autowired
    private QxInviteApplyService qxInviteApplyService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxInviteApply.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxInviteApply_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxInviteApply_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxInviteApplyParam qxInviteApplyParam) {
        this.qxInviteApplyService.add(qxInviteApplyParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(QxInviteApplyParam qxInviteApplyParam) {
        this.qxInviteApplyService.update(qxInviteApplyParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(QxInviteApplyParam qxInviteApplyParam) {
        this.qxInviteApplyService.delete(qxInviteApplyParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(QxInviteApplyParam qxInviteApplyParam) {
        QxInviteApply detail = this.qxInviteApplyService.getById(qxInviteApplyParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2019-11-14
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(QxInviteApplyParam qxInviteApplyParam) {
        return this.qxInviteApplyService.findPageBySpec(qxInviteApplyParam);
    }

}


