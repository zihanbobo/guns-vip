package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxAlert;
import cn.stylefeng.guns.modular.note.model.params.QxAlertParam;
import cn.stylefeng.guns.modular.note.service.QxAlertService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 约单报警记录控制器
 *
 * @author 
 * @Date 2019-11-14 13:32:52
 */
@Controller
@RequestMapping("/qxAlert")
public class QxAlertController extends BaseController {

    private String PREFIX = "/note/qxAlert";

    @Autowired
    private QxAlertService qxAlertService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxAlert.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxAlert_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxAlert_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxAlertParam qxAlertParam) {
        this.qxAlertService.add(qxAlertParam);
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
    public ResponseData editItem(QxAlertParam qxAlertParam) {
        this.qxAlertService.update(qxAlertParam);
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
    public ResponseData delete(QxAlertParam qxAlertParam) {
        this.qxAlertService.delete(qxAlertParam);
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
    public ResponseData detail(QxAlertParam qxAlertParam) {
        QxAlert detail = this.qxAlertService.getById(qxAlertParam.getId());
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
    public LayuiPageInfo list(QxAlertParam qxAlertParam) {
        return this.qxAlertService.findPageBySpec(qxAlertParam);
    }

}


