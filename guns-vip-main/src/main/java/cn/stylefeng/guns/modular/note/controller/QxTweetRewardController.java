package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxTweetReward;
import cn.stylefeng.guns.modular.note.model.params.QxTweetRewardParam;
import cn.stylefeng.guns.modular.note.service.QxTweetRewardService;
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
 * @Date 2019-12-23 13:12:45
 */
@Controller
@RequestMapping("/qxTweetReward")
public class QxTweetRewardController extends BaseController {

    private String PREFIX = "/note/qxTweetReward";

    @Autowired
    private QxTweetRewardService qxTweetRewardService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxTweetReward.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxTweetReward_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxTweetReward_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxTweetRewardParam qxTweetRewardParam) {
        this.qxTweetRewardService.add(qxTweetRewardParam);
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
    public ResponseData editItem(QxTweetRewardParam qxTweetRewardParam) {
        this.qxTweetRewardService.update(qxTweetRewardParam);
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
    public ResponseData delete(QxTweetRewardParam qxTweetRewardParam) {
        this.qxTweetRewardService.delete(qxTweetRewardParam);
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
    public ResponseData detail(QxTweetRewardParam qxTweetRewardParam) {
        QxTweetReward detail = this.qxTweetRewardService.getById(qxTweetRewardParam.getId());
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
    public LayuiPageInfo list(QxTweetRewardParam qxTweetRewardParam) {
        return this.qxTweetRewardService.findPageBySpec(qxTweetRewardParam);
    }

}


