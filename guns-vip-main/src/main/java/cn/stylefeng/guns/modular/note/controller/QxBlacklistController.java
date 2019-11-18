package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlacklist;
import cn.stylefeng.guns.modular.note.model.params.QxBlacklistParam;
import cn.stylefeng.guns.modular.note.service.QxBlacklistService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 黑名单表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:05
 */
@Controller
@RequestMapping("/qxBlacklist")
public class QxBlacklistController extends BaseController {

    private String PREFIX = "/note/qxBlacklist";

    @Autowired
    private QxBlacklistService qxBlacklistService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxBlacklist.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxBlacklist_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxBlacklist_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxBlacklistParam qxBlacklistParam) {
        this.qxBlacklistService.add(qxBlacklistParam);
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
    public ResponseData editItem(QxBlacklistParam qxBlacklistParam) {
        this.qxBlacklistService.update(qxBlacklistParam);
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
    public ResponseData delete(QxBlacklistParam qxBlacklistParam) {
        this.qxBlacklistService.delete(qxBlacklistParam);
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
    public ResponseData detail(QxBlacklistParam qxBlacklistParam) {
        QxBlacklist detail = this.qxBlacklistService.getById(qxBlacklistParam.getId());
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
    public LayuiPageInfo list(QxBlacklistParam qxBlacklistParam) {
        return this.qxBlacklistService.findPageBySpec(qxBlacklistParam);
    }

}


