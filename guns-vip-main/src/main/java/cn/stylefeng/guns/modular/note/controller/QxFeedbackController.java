package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxFeedback;
import cn.stylefeng.guns.modular.note.model.params.QxFeedbackParam;
import cn.stylefeng.guns.modular.note.service.QxFeedbackService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 建议反馈表控制器
 *
 * @author 
 * @Date 2019-11-14 13:32:52
 */
@Controller
@RequestMapping("/qxFeedback")
public class QxFeedbackController extends BaseController {

    private String PREFIX = "/note/qxFeedback";

    @Autowired
    private QxFeedbackService qxFeedbackService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxFeedback.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxFeedback_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxFeedback_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxFeedbackParam qxFeedbackParam) {
        this.qxFeedbackService.add(qxFeedbackParam);
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
    public ResponseData editItem(QxFeedbackParam qxFeedbackParam) {
        this.qxFeedbackService.update(qxFeedbackParam);
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
    public ResponseData delete(QxFeedbackParam qxFeedbackParam) {
        this.qxFeedbackService.delete(qxFeedbackParam);
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
    public ResponseData detail(QxFeedbackParam qxFeedbackParam) {
        QxFeedback detail = this.qxFeedbackService.getById(qxFeedbackParam.getId());
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
    public LayuiPageInfo list(QxFeedbackParam qxFeedbackParam) {
        return this.qxFeedbackService.findPageBySpec(qxFeedbackParam);
    }

}


