package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCooperate;
import cn.stylefeng.guns.modular.note.model.params.QxCooperateParam;
import cn.stylefeng.guns.modular.note.service.QxCooperateService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 商务合作控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:05
 */
@Controller
@RequestMapping("/qxCooperate")
public class QxCooperateController extends BaseController {

    private String PREFIX = "/note/qxCooperate";

    @Autowired
    private QxCooperateService qxCooperateService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxCooperate.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxCooperate_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxCooperate_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxCooperateParam qxCooperateParam) {
        this.qxCooperateService.add(qxCooperateParam);
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
    public ResponseData editItem(QxCooperateParam qxCooperateParam) {
        this.qxCooperateService.update(qxCooperateParam);
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
    public ResponseData delete(QxCooperateParam qxCooperateParam) {
        this.qxCooperateService.delete(qxCooperateParam);
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
    public ResponseData detail(QxCooperateParam qxCooperateParam) {
        QxCooperate detail = this.qxCooperateService.getById(qxCooperateParam.getId());
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
    public LayuiPageInfo list(QxCooperateParam qxCooperateParam) {
        return this.qxCooperateService.findPageBySpec(qxCooperateParam);
    }

}


