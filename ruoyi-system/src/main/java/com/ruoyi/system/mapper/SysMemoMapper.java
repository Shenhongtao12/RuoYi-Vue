package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.SysMemo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 备忘录Mapper接口
 * 
 * @author aaron
 * @date 2022-07-23
 */
public interface SysMemoMapper extends BaseMapper<SysMemo>
{
    /**
     * 查询备忘录
     * 
     * @param noticeId 备忘录主键
     * @return 备忘录
     */
    @DataSource(value = DataSourceType.SLAVE)
    public SysMemo selectSysMemoByNoticeId(Long noticeId);

    /**
     * 查询备忘录列表
     * 
     * @param sysMemo 备忘录切换到SLAVE数据源
     * @return 备忘录集合
     */
    @DataSource(value = DataSourceType.SLAVE)
    public List<SysMemo> selectSysMemoList(SysMemo sysMemo);

    /**
     * 新增备忘录
     * 
     * @param sysMemo 备忘录
     * @return 结果
     */
    public int insertSysMemo(SysMemo sysMemo);

    /**
     * 修改备忘录
     * 
     * @param sysMemo 备忘录
     * @return 结果
     */
    public int updateSysMemo(SysMemo sysMemo);

    /**
     * 删除备忘录
     * 
     * @param noticeId 备忘录主键
     * @return 结果
     */
    public int deleteSysMemoByNoticeId(Long noticeId);

    /**
     * 批量删除备忘录
     * 
     * @param noticeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMemoByNoticeIds(Long[] noticeIds);

    @Select("select inventory from sys_memo where notice_id = #{id} limit 1")
    public Integer getInventoryById(Integer id);

    @Update("update sys_memo set inventory = (inventory - #{num}) where notice_id = #{id}")
    public int updateInventorybyId(@Param("id") Integer id, @Param("num") Integer num);
}
