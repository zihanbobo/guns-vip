package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserSocial;
import cn.stylefeng.guns.modular.note.model.params.QxUserSocialParam;
import cn.stylefeng.guns.modular.note.service.QxUserSocialService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户社交账号表控制器
 *
 * @author 
 * @Date 2019-11-14 13:32:53
 */
@Controller
@RequestMapping("/qxUserSocial")
public class QxUserSocialController extends BaseController {

    private String PREFIX = "/note/qxUserSocial";

    @Autowired
    private QxUserSocialService qxUserSocialService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxUserSocial.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxUserSocial_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxUserSocial_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-14
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxUserSocialParam qxUserSocialParam) {
        this.qxUserSocialService.add(qxUserSocialParam);
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
    public ResponseData editItem(QxUserSocialParam qxUserSocialParam) {
        this.qxUserSocialService.update(qxUserSocialParam);
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
    public ResponseData delete(QxUserSocialParam qxUserSocialParam) {
        this.qxUserSocialService.delete(qxUserSocialParam);
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
    public ResponseData detail(QxUserSocialParam qxUserSocialParam) {
        QxUserSocial detail = this.qxUserSocialService.getById(qxUserSocialParam.getId());
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
    public LayuiPageInfo list(QxUserSocialParam qxUserSocialParam) {
        return this.qxUserSocialService.findPageBySpec(qxUserSocialParam);
    }

}


