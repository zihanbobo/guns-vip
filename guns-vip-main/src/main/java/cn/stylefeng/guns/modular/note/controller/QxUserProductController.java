package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxUserProduct;
import cn.stylefeng.guns.modular.note.model.params.QxUserProductParam;
import cn.stylefeng.guns.modular.note.service.QxUserProductService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 兑换记录表控制器
 *
 * @author 
 * @Date 2019-11-30 17:47:46
 */
@Controller
@RequestMapping("/qxUserProduct")
public class QxUserProductController extends BaseController {

    private String PREFIX = "/note/qxUserProduct";

    @Autowired
    private QxUserProductService qxUserProductService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxUserProduct.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxUserProduct_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxUserProduct_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxUserProductParam qxUserProductParam) {
        this.qxUserProductService.add(qxUserProductParam);
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
    public ResponseData editItem(QxUserProductParam qxUserProductParam) {
        this.qxUserProductService.update(qxUserProductParam);
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
    public ResponseData delete(QxUserProductParam qxUserProductParam) {
        this.qxUserProductService.delete(qxUserProductParam);
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
    public ResponseData detail(QxUserProductParam qxUserProductParam) {
        QxUserProduct detail = this.qxUserProductService.getById(qxUserProductParam.getId());
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
    public LayuiPageInfo list(QxUserProductParam qxUserProductParam) {
        return this.qxUserProductService.findPageBySpec(qxUserProductParam);
    }
        
    @ResponseBody
    @RequestMapping("/deliver")
    public ResponseData deliver(Long id) {
    	this.qxUserProductService.deliver(id);
    	return ResponseData.success();
    }

}


