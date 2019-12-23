package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweetLike;
import cn.stylefeng.guns.modular.note.model.params.QxTweetLikeParam;
import cn.stylefeng.guns.modular.note.service.QxTweetLikeService;
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
 * @Date 2019-12-23 12:35:47
 */
@Controller
@RequestMapping("/qxTweetLike")
public class QxTweetLikeController extends BaseController {

    private String PREFIX = "/note/qxTweetLike";

    @Autowired
    private QxTweetLikeService qxTweetLikeService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxTweetLike.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxTweetLike_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxTweetLike_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxTweetLikeParam qxTweetLikeParam) {
        this.qxTweetLikeService.add(qxTweetLikeParam);
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
    public ResponseData editItem(QxTweetLikeParam qxTweetLikeParam) {
        this.qxTweetLikeService.update(qxTweetLikeParam);
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
    public ResponseData delete(QxTweetLikeParam qxTweetLikeParam) {
        this.qxTweetLikeService.delete(qxTweetLikeParam);
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
    public ResponseData detail(QxTweetLikeParam qxTweetLikeParam) {
        QxTweetLike detail = this.qxTweetLikeService.getById(qxTweetLikeParam.getId());
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
    public LayuiPageInfo list(QxTweetLikeParam qxTweetLikeParam) {
        return this.qxTweetLikeService.findPageBySpec(qxTweetLikeParam);
    }

}


