package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserNote;
import cn.stylefeng.guns.modular.note.model.params.QxUserNoteParam;
import cn.stylefeng.guns.modular.note.service.QxUserNoteService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户付费日记关系表控制器
 *
 * @author 
 * @Date 2019-11-14 13:32:53
 */
@Controller
@RequestMapping("/qxUserNote")
public class QxUserNoteController extends BaseController {

    private String PREFIX = "/note/qxUserNote";

    @Autowired
    private QxUserNoteService qxUserNoteService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxUserNote.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxUserNote_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxUserNote_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxUserNoteParam qxUserNoteParam) {
        this.qxUserNoteService.add(qxUserNoteParam);
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
    public ResponseData editItem(QxUserNoteParam qxUserNoteParam) {
        this.qxUserNoteService.update(qxUserNoteParam);
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
    public ResponseData delete(QxUserNoteParam qxUserNoteParam) {
        this.qxUserNoteService.delete(qxUserNoteParam);
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
    public ResponseData detail(QxUserNoteParam qxUserNoteParam) {
        QxUserNote detail = this.qxUserNoteService.getById(qxUserNoteParam.getId());
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
    public LayuiPageInfo list(QxUserNoteParam qxUserNoteParam) {
        return this.qxUserNoteService.findPageBySpec(qxUserNoteParam);
    }

}


