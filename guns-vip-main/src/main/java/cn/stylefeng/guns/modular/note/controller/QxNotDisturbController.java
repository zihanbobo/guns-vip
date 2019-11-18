package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNotDisturb;
import cn.stylefeng.guns.modular.note.model.params.QxNotDisturbParam;
import cn.stylefeng.guns.modular.note.service.QxNotDisturbService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 消息免打扰表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:05
 */
@Controller
@RequestMapping("/qxNotDisturb")
public class QxNotDisturbController extends BaseController {

    private String PREFIX = "/note/qxNotDisturb";

    @Autowired
    private QxNotDisturbService qxNotDisturbService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxNotDisturb.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxNotDisturb_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxNotDisturb_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxNotDisturbParam qxNotDisturbParam) {
        this.qxNotDisturbService.add(qxNotDisturbParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(QxNotDisturbParam qxNotDisturbParam) {
        this.qxNotDisturbService.update(qxNotDisturbParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(QxNotDisturbParam qxNotDisturbParam) {
        this.qxNotDisturbService.delete(qxNotDisturbParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(QxNotDisturbParam qxNotDisturbParam) {
        QxNotDisturb detail = this.qxNotDisturbService.getById(qxNotDisturbParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2019-11-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(QxNotDisturbParam qxNotDisturbParam) {
        return this.qxNotDisturbService.findPageBySpec(qxNotDisturbParam);
    }

}


