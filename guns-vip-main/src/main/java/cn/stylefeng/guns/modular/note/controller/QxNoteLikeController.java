package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxNoteLike;
import cn.stylefeng.guns.modular.note.model.params.QxNoteLikeParam;
import cn.stylefeng.guns.modular.note.service.QxNoteLikeService;
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
 * @Date 2019-12-23 12:35:46
 */
@Controller
@RequestMapping("/qxNoteLike")
public class QxNoteLikeController extends BaseController {

    private String PREFIX = "/note/qxNoteLike";

    @Autowired
    private QxNoteLikeService qxNoteLikeService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxNoteLike.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxNoteLike_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxNoteLike_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-12-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxNoteLikeParam qxNoteLikeParam) {
        this.qxNoteLikeService.add(qxNoteLikeParam);
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
    public ResponseData editItem(QxNoteLikeParam qxNoteLikeParam) {
        this.qxNoteLikeService.update(qxNoteLikeParam);
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
    public ResponseData delete(QxNoteLikeParam qxNoteLikeParam) {
        this.qxNoteLikeService.delete(qxNoteLikeParam);
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
    public ResponseData detail(QxNoteLikeParam qxNoteLikeParam) {
        QxNoteLike detail = this.qxNoteLikeService.getById(qxNoteLikeParam.getId());
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
    public LayuiPageInfo list(QxNoteLikeParam qxNoteLikeParam) {
        return this.qxNoteLikeService.findPageBySpec(qxNoteLikeParam);
    }

}


