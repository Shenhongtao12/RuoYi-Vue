package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysMemo;
import com.ruoyi.system.service.ISysMemoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 备忘录Controller
 * 
 * @author aaron
 * @date 2022-07-23
 */
@RestController
@RequestMapping("/system/memo")
public class SysMemoController extends BaseController
{
    @Autowired
    private ISysMemoService sysMemoService;

    /**
     * 查询备忘录列表
     */
    @PreAuthorize("@ss.hasPermi('system:memo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMemo sysMemo)
    {
        startPage();
        List<SysMemo> list = sysMemoService.selectSysMemoList(sysMemo);
        return getDataTable(list);
    }

    /**
     * 导出备忘录列表
     */
    @PreAuthorize("@ss.hasPermi('system:memo:export')")
    @Log(title = "备忘录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMemo sysMemo)
    {
        List<SysMemo> list = sysMemoService.selectSysMemoList(sysMemo);
        ExcelUtil<SysMemo> util = new ExcelUtil<SysMemo>(SysMemo.class);
        util.exportExcel(response, list, "备忘录数据");
    }

    /**
     * 获取备忘录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:memo:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId)
    {
        return AjaxResult.success(sysMemoService.selectSysMemoByNoticeId(noticeId));
    }

    /**
     * 新增备忘录
     */
    @PreAuthorize("@ss.hasPermi('system:memo:add')")
    @Log(title = "备忘录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMemo sysMemo)
    {
        return toAjax(sysMemoService.insertSysMemo(sysMemo));
    }

    /**
     * 修改备忘录
     */
    @PreAuthorize("@ss.hasPermi('system:memo:edit')")
    @Log(title = "备忘录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMemo sysMemo)
    {
        return toAjax(sysMemoService.updateSysMemo(sysMemo));
    }

    /**
     * 删除备忘录
     */
    @PreAuthorize("@ss.hasPermi('system:memo:remove')")
    @Log(title = "备忘录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(sysMemoService.deleteSysMemoByNoticeIds(noticeIds));
    }

    @PostMapping("updateInventory")
    public AjaxResult updateInventoryById(@RequestBody Map<String, Integer> map) {
        return sysMemoService.updateInventoryById(map.get("id"), map.get("num"));
    }
}
