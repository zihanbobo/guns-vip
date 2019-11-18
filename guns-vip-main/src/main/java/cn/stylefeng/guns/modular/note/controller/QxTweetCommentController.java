package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweetComment;
import cn.stylefeng.guns.modular.note.model.params.QxTweetCommentParam;
import cn.stylefeng.guns.modular.note.service.QxTweetCommentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 推文评论表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:06
 */
@Controller
@RequestMapping("/qxTweetComment")
public class QxTweetCommentController extends BaseController {

    private String PREFIX = "/note/qxTweetComment";

    @Autowired
    private QxTweetCommentService qxTweetCommentService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxTweetComment.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxTweetComment_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxTweetComment_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxTweetCommentParam qxTweetCommentParam) {
        this.qxTweetCommentService.add(qxTweetCommentParam);
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
    public ResponseData editItem(QxTweetCommentParam qxTweetCommentParam) {
        this.qxTweetCommentService.update(qxTweetCommentParam);
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
    public ResponseData delete(QxTweetCommentParam qxTweetCommentParam) {
        this.qxTweetCommentService.delete(qxTweetCommentParam);
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
    public ResponseData detail(QxTweetCommentParam qxTweetCommentParam) {
        QxTweetComment detail = this.qxTweetCommentService.getById(qxTweetCommentParam.getId());
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
    public LayuiPageInfo list(QxTweetCommentParam qxTweetCommentParam) {
        return this.qxTweetCommentService.findPageBySpec(qxTweetCommentParam);
    }

}


