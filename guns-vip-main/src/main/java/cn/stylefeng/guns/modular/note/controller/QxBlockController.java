package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxBlock;
import cn.stylefeng.guns.modular.note.model.params.QxBlockParam;
import cn.stylefeng.guns.modular.note.service.QxBlockService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 消息免打扰表控制器
 *
 * @author 
 * @Date 2019-11-20 17:07:49
 */
@Controller
@RequestMapping("/qxBlock")
public class QxBlockController extends BaseController {

    private String PREFIX = "/note/qxBlock";

    @Autowired
    private QxBlockService qxBlockService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-20
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxBlock.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-20
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxBlock_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-20
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxBlock_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-20
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxBlockParam qxBlockParam) {
        this.qxBlockService.add(qxBlockParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 
     * @Date 2019-11-20
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(QxBlockParam qxBlockParam) {
        this.qxBlockService.update(qxBlockParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 
     * @Date 2019-11-20
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(QxBlockParam qxBlockParam) {
        this.qxBlockService.delete(qxBlockParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 
     * @Date 2019-11-20
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(QxBlockParam qxBlockParam) {
        QxBlock detail = this.qxBlockService.getById(qxBlockParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2019-11-20
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(QxBlockParam qxBlockParam) {
        return this.qxBlockService.findPageBySpec(qxBlockParam);
    }

}


