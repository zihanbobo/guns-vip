package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteComment;
import cn.stylefeng.guns.modular.note.model.params.QxNoteCommentParam;
import cn.stylefeng.guns.modular.note.service.QxNoteCommentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 私密日记评论表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:06
 */
@Controller
@RequestMapping("/qxNoteComment")
public class QxNoteCommentController extends BaseController {

    private String PREFIX = "/note/qxNoteComment";

    @Autowired
    private QxNoteCommentService qxNoteCommentService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxNoteComment.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxNoteComment_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxNoteComment_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxNoteCommentParam qxNoteCommentParam) {
        this.qxNoteCommentService.add(qxNoteCommentParam);
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
    public ResponseData editItem(QxNoteCommentParam qxNoteCommentParam) {
        this.qxNoteCommentService.update(qxNoteCommentParam);
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
    public ResponseData delete(QxNoteCommentParam qxNoteCommentParam) {
        this.qxNoteCommentService.delete(qxNoteCommentParam);
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
    public ResponseData detail(QxNoteCommentParam qxNoteCommentParam) {
        QxNoteComment detail = this.qxNoteCommentService.getById(qxNoteCommentParam.getId());
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
    public LayuiPageInfo list(QxNoteCommentParam qxNoteCommentParam) {
        return this.qxNoteCommentService.findPageBySpec(qxNoteCommentParam);
    }

}


