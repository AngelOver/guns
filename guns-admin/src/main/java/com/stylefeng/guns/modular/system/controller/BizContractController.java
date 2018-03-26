package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.BizContract;
import com.stylefeng.guns.modular.system.service.IBizContractService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 15:33:06
 */
@Controller
@RequestMapping("/bizContract")
public class BizContractController extends BaseController {

    private String PREFIX = "/system/bizContract/";

    @Autowired
    private IBizContractService bizContractService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizContract.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizContract_add")
    public String bizContractAdd() {
        return PREFIX + "bizContract_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizContract_update/{bizContractId}")
    public String bizContractUpdate(@PathVariable Integer bizContractId, Model model) {
        BizContract bizContract = bizContractService.selectById(bizContractId);
        model.addAttribute("item",bizContract);
        LogObjectHolder.me().set(bizContract);
        return PREFIX + "bizContract_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizContractService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizContract bizContract) {
        bizContractService.insert(bizContract);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizContractId) {
        bizContractService.deleteById(bizContractId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizContract bizContract) {
        bizContractService.updateById(bizContract);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizContractId}")
    @ResponseBody
    public Object detail(@PathVariable("bizContractId") Integer bizContractId) {
        return bizContractService.selectById(bizContractId);
    }
}
