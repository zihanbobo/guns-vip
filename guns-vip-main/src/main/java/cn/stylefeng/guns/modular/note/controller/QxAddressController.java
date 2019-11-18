package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxAddress;
import cn.stylefeng.guns.modular.note.model.params.QxAddressParam;
import cn.stylefeng.guns.modular.note.service.QxAddressService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 收货地址表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:04
 */
@Controller
@RequestMapping("/qxAddress")
public class QxAddressController extends BaseController {

    private String PREFIX = "/note/qxAddress";

    @Autowired
    private QxAddressService qxAddressService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxAddress.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxAddress_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxAddress_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxAddressParam qxAddressParam) {
        this.qxAddressService.add(qxAddressParam);
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
    public ResponseData editItem(QxAddressParam qxAddressParam) {
        this.qxAddressService.update(qxAddressParam);
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
    public ResponseData delete(QxAddressParam qxAddressParam) {
        this.qxAddressService.delete(qxAddressParam);
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
    public ResponseData detail(QxAddressParam qxAddressParam) {
        QxAddress detail = this.qxAddressService.getById(qxAddressParam.getId());
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
    public LayuiPageInfo list(QxAddressParam qxAddressParam) {
        return this.qxAddressService.findPageBySpec(qxAddressParam);
    }

}


