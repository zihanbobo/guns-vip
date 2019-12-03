package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteOperate;
import cn.stylefeng.guns.modular.note.model.params.QxInviteOperateParam;
import cn.stylefeng.guns.modular.note.service.QxInviteOperateService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 约单操作表控制器
 *
 * @author 
 * @Date 2019-11-30 17:47:46
 */
@Controller
@RequestMapping("/qxInviteOperate")
public class QxInviteOperateController extends BaseController {

    private String PREFIX = "/note/qxInviteOperate";

    @Autowired
    private QxInviteOperateService qxInviteOperateService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxInviteOperate.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxInviteOperate_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxInviteOperate_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxInviteOperateParam qxInviteOperateParam) {
        this.qxInviteOperateService.add(qxInviteOperateParam);
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
    public ResponseData editItem(QxInviteOperateParam qxInviteOperateParam) {
        this.qxInviteOperateService.update(qxInviteOperateParam);
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
    public ResponseData delete(QxInviteOperateParam qxInviteOperateParam) {
        this.qxInviteOperateService.delete(qxInviteOperateParam);
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
    public ResponseData detail(QxInviteOperateParam qxInviteOperateParam) {
        QxInviteOperate detail = this.qxInviteOperateService.getById(qxInviteOperateParam.getId());
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
    public LayuiPageInfo list(QxInviteOperateParam qxInviteOperateParam) {
        return this.qxInviteOperateService.findPageBySpec(qxInviteOperateParam);
    }

}


