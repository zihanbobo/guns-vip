package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCoinOrder;
import cn.stylefeng.guns.modular.note.model.params.QxCoinOrderParam;
import cn.stylefeng.guns.modular.note.service.QxCoinOrderService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户金币订单表控制器
 *
 * @author 
 * @Date 2019-11-30 17:47:46
 */
@Controller
@RequestMapping("/qxCoinOrder")
public class QxCoinOrderController extends BaseController {

    private String PREFIX = "/note/qxCoinOrder";

    @Autowired
    private QxCoinOrderService qxCoinOrderService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxCoinOrder.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxCoinOrder_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxCoinOrder_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxCoinOrderParam qxCoinOrderParam) {
        this.qxCoinOrderService.add(qxCoinOrderParam);
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
    public ResponseData editItem(QxCoinOrderParam qxCoinOrderParam) {
        this.qxCoinOrderService.update(qxCoinOrderParam);
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
    public ResponseData delete(QxCoinOrderParam qxCoinOrderParam) {
        this.qxCoinOrderService.delete(qxCoinOrderParam);
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
    public ResponseData detail(QxCoinOrderParam qxCoinOrderParam) {
        QxCoinOrder detail = this.qxCoinOrderService.getById(qxCoinOrderParam.getId());
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
    public LayuiPageInfo list(QxCoinOrderParam qxCoinOrderParam) {
        return this.qxCoinOrderService.findPageBySpec(qxCoinOrderParam);
    }

}


