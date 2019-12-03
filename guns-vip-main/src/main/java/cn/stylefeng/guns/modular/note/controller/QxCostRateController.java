package cn.stylefeng.guns.modular.note.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.note.entity.QxCostRate;
import cn.stylefeng.guns.modular.note.model.params.QxCostRateParam;
import cn.stylefeng.guns.modular.note.service.QxCostRateService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 费用比例表控制器
 *
 * @author 
 * @Date 2019-11-30 17:47:46
 */
@Controller
@RequestMapping("/qxCostRate")
public class QxCostRateController extends BaseController {

    private String PREFIX = "/note/qxCostRate";

    @Autowired
    private QxCostRateService qxCostRateService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/qxCostRate.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/qxCostRate_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/qxCostRate_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2019-11-30
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(QxCostRateParam qxCostRateParam) {
        this.qxCostRateService.add(qxCostRateParam);
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
    public ResponseData editItem(QxCostRateParam qxCostRateParam) {
        this.qxCostRateService.update(qxCostRateParam);
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
    public ResponseData delete(QxCostRateParam qxCostRateParam) {
        this.qxCostRateService.delete(qxCostRateParam);
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
    public ResponseData detail(QxCostRateParam qxCostRateParam) {
        QxCostRate detail = this.qxCostRateService.getById(qxCostRateParam.getId());
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
    public LayuiPageInfo list(QxCostRateParam qxCostRateParam) {
        return this.qxCostRateService.findPageBySpec(qxCostRateParam);
    }

}


