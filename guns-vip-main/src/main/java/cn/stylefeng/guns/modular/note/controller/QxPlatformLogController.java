package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxPlatformLog;
import cn.stylefeng.guns.modular.note.model.params.QxPlatformLogParam;
import cn.stylefeng.guns.modular.note.service.QxPlatformLogService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 平台流水表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:06
 */
@Controller
@RequestMapping("/qxPlatformLog")
public class QxPlatformLogController extends BaseController {

    private String PREFIX = "/note/qxPlatformLog";

    @Autowired
    private QxPlatformLogService qxPlatformLogService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxPlatformLog.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxPlatformLog_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxPlatformLog_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxPlatformLogParam qxPlatformLogParam) {
        this.qxPlatformLogService.add(qxPlatformLogParam);
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
    public ResponseData editItem(QxPlatformLogParam qxPlatformLogParam) {
        this.qxPlatformLogService.update(qxPlatformLogParam);
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
    public ResponseData delete(QxPlatformLogParam qxPlatformLogParam) {
        this.qxPlatformLogService.delete(qxPlatformLogParam);
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
    public ResponseData detail(QxPlatformLogParam qxPlatformLogParam) {
        QxPlatformLog detail = this.qxPlatformLogService.getById(qxPlatformLogParam.getId());
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
    public LayuiPageInfo list(QxPlatformLogParam qxPlatformLogParam) {
        return this.qxPlatformLogService.findPageBySpec(qxPlatformLogParam);
    }

}


