package cn.stylefeng.guns.modular.note.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.dvo.QxInviteAdminVo;
import cn.stylefeng.guns.modular.note.entity.QxInvite;
import cn.stylefeng.guns.modular.note.entity.QxUser;
import cn.stylefeng.guns.modular.note.model.params.QxInviteParam;
import cn.stylefeng.guns.modular.note.service.QxInviteService;
import cn.stylefeng.guns.modular.note.service.QxUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 约单表控制器
 *
 * @author 
 * @Date 2019-11-30 17:47:46
 */
@Controller
@RequestMapping("/qxInvite")
public class QxInviteController extends BaseController {

    private String PREFIX = "/note/qxInvite";

    @Autowired
    private QxInviteService qxInviteService;
    
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
        return PREFIX + "/qxInvite.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxInvite_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxInvite_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxInviteParam qxInviteParam) {
        this.qxInviteService.add(qxInviteParam);
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
    public ResponseData editItem(QxInviteParam qxInviteParam) {
        this.qxInviteService.update(qxInviteParam);
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
    public ResponseData delete(QxInviteParam qxInviteParam) {
        this.qxInviteService.delete(qxInviteParam);
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
    public ResponseData detail(QxInviteParam qxInviteParam) {
        QxInvite detail = this.qxInviteService.getById(qxInviteParam.getId());
        QxUser inviter = qxUserService.getById(detail.getInviter());
        QxUser invitee = null;
        if (detail.getInvitee() != null) {
        	invitee = qxUserService.getById(detail.getInvitee());
        }
        QxInviteAdminVo vo = new QxInviteAdminVo();
        BeanUtils.copyProperties(detail, vo);
        vo.setInviterName(inviter.getNickname());
        vo.setInviterMobile(inviter.getMobile());
        if (invitee != null) {
            vo.setInviteeName(invitee.getNickname());
            vo.setInviteeMobile(invitee.getMobile());
        }

        return ResponseData.success(vo);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2019-11-30
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(QxInviteParam qxInviteParam) {
        return this.qxInviteService.findPageBySpec(qxInviteParam);
    }

}


