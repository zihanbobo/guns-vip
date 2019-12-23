package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteReward;
import cn.stylefeng.guns.modular.note.model.params.QxNoteRewardParam;
import cn.stylefeng.guns.modular.note.service.QxNoteRewardService;
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
@RequestMapping("/qxNoteReward")
public class QxNoteRewardController extends BaseController {

    private String PREFIX = "/note/qxNoteReward";

    @Autowired
    private QxNoteRewardService qxNoteRewardService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxNoteReward.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxNoteReward_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxNoteReward_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxNoteRewardParam qxNoteRewardParam) {
        this.qxNoteRewardService.add(qxNoteRewardParam);
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
    public ResponseData editItem(QxNoteRewardParam qxNoteRewardParam) {
        this.qxNoteRewardService.update(qxNoteRewardParam);
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
    public ResponseData delete(QxNoteRewardParam qxNoteRewardParam) {
        this.qxNoteRewardService.delete(qxNoteRewardParam);
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
    public ResponseData detail(QxNoteRewardParam qxNoteRewardParam) {
        QxNoteReward detail = this.qxNoteRewardService.getById(qxNoteRewardParam.getId());
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
    public LayuiPageInfo list(QxNoteRewardParam qxNoteRewardParam) {
        return this.qxNoteRewardService.findPageBySpec(qxNoteRewardParam);
    }

}


