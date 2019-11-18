package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxInviteComment;
import cn.stylefeng.guns.modular.note.model.params.QxInviteCommentParam;
import cn.stylefeng.guns.modular.note.service.QxInviteCommentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 约单评价表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:05
 */
@Controller
@RequestMapping("/qxInviteComment")
public class QxInviteCommentController extends BaseController {

    private String PREFIX = "/note/qxInviteComment";

    @Autowired
    private QxInviteCommentService qxInviteCommentService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxInviteComment.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxInviteComment_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxInviteComment_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxInviteCommentParam qxInviteCommentParam) {
        this.qxInviteCommentService.add(qxInviteCommentParam);
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
    public ResponseData editItem(QxInviteCommentParam qxInviteCommentParam) {
        this.qxInviteCommentService.update(qxInviteCommentParam);
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
    public ResponseData delete(QxInviteCommentParam qxInviteCommentParam) {
        this.qxInviteCommentService.delete(qxInviteCommentParam);
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
    public ResponseData detail(QxInviteCommentParam qxInviteCommentParam) {
        QxInviteComment detail = this.qxInviteCommentService.getById(qxInviteCommentParam.getId());
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
    public LayuiPageInfo list(QxInviteCommentParam qxInviteCommentParam) {
        return this.qxInviteCommentService.findPageBySpec(qxInviteCommentParam);
    }

}


