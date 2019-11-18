package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCategory;
import cn.stylefeng.guns.modular.note.model.params.QxCategoryParam;
import cn.stylefeng.guns.modular.note.service.QxCategoryService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 商品品类表控制器
 *
 * @author 
 * @Date 2019-11-18 15:18:05
 */
@Controller
@RequestMapping("/qxCategory")
public class QxCategoryController extends BaseController {

    private String PREFIX = "/note/qxCategory";

    @Autowired
    private QxCategoryService qxCategoryService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxCategory.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxCategory_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxCategory_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxCategoryParam qxCategoryParam) {
        this.qxCategoryService.add(qxCategoryParam);
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
    public ResponseData editItem(QxCategoryParam qxCategoryParam) {
        this.qxCategoryService.update(qxCategoryParam);
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
    public ResponseData delete(QxCategoryParam qxCategoryParam) {
        this.qxCategoryService.delete(qxCategoryParam);
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
    public ResponseData detail(QxCategoryParam qxCategoryParam) {
        QxCategory detail = this.qxCategoryService.getById(qxCategoryParam.getId());
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
    public LayuiPageInfo list(QxCategoryParam qxCategoryParam) {
        return this.qxCategoryService.findPageBySpec(qxCategoryParam);
    }

}


