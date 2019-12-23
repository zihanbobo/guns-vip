package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxReport;
import cn.stylefeng.guns.modular.note.model.params.QxReportParam;
import cn.stylefeng.guns.modular.note.service.QxReportService;
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
 * @Date 2019-12-23 14:31:21
 */
@Controller
@RequestMapping("/qxReport")
public class QxReportController extends BaseController {

    private String PREFIX = "/note/qxReport";

    @Autowired
    private QxReportService qxReportService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxReport.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxReport_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxReport_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxReportParam qxReportParam) {
        this.qxReportService.add(qxReportParam);
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
    public ResponseData editItem(QxReportParam qxReportParam) {
        this.qxReportService.update(qxReportParam);
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
    public ResponseData delete(QxReportParam qxReportParam) {
        this.qxReportService.delete(qxReportParam);
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
    public ResponseData detail(QxReportParam qxReportParam) {
        QxReport detail = this.qxReportService.getById(qxReportParam.getId());
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
    public LayuiPageInfo list(QxReportParam qxReportParam) {
        return this.qxReportService.findPageBySpec(qxReportParam);
    }

}


