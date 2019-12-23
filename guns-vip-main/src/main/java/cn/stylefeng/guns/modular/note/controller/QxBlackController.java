package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlack;
import cn.stylefeng.guns.modular.note.model.params.QxBlackParam;
import cn.stylefeng.guns.modular.note.service.QxBlackService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author 
 * @Date 2019-12-23 14:20:13
 */
@Controller
@RequestMapping("/qxBlack")
public class QxBlackController extends BaseController {

    private String PREFIX = "/note/qxBlack";

    @Autowired
    private QxBlackService qxBlackService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxBlack.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxBlack_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxBlack_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxBlackParam qxBlackParam) {
        this.qxBlackService.add(qxBlackParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(QxBlackParam qxBlackParam) {
        this.qxBlackService.update(qxBlackParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(QxBlackParam qxBlackParam) {
        this.qxBlackService.delete(qxBlackParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(QxBlackParam qxBlackParam) {
        QxBlack detail = this.qxBlackService.getById(qxBlackParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2019-12-23
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(QxBlackParam qxBlackParam) {
        return this.qxBlackService.findPageBySpec(qxBlackParam);
    }

}


