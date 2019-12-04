package cn.stylefeng.guns.modular.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.model.params.QxUserParam;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 用户表控制器
 *
 * @author 
 * @Date 2019-11-30 17:47:46
 */
@Controller
@RequestMapping("/qxUser")
public class QxUserController extends BaseController {

    private String PREFIX = "/note/qxUser";

    @Autowired
    private QxUserService qxUserService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxUser.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxUser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxUser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxUserParam qxUserParam) {
        this.qxUserService.add(qxUserParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(QxUserParam qxUserParam) {
        this.qxUserService.update(qxUserParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(QxUserParam qxUserParam) {
        this.qxUserService.delete(qxUserParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(QxUserParam qxUserParam) {
        QxUser detail = this.qxUserService.getById(qxUserParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2019-11-30
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(QxUserParam qxUserParam) {
    	qxUserParam.setDeleted(false);
        return this.qxUserService.findPageBySpec(qxUserParam);
    }
    
    @ResponseBody
    @RequestMapping("/changeUserStatus")
    public Object changeUserStatus(Long id, String status) {
    	UpdateWrapper<QxUser> updateWrapper = new UpdateWrapper<>();
    	updateWrapper.eq("id", id).set("status", status);
    	this.qxUserService.update(updateWrapper);
    	return ResponseData.success();
    }

}


