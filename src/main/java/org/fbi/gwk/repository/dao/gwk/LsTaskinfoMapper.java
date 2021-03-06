package org.fbi.gwk.repository.dao.gwk;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.fbi.gwk.repository.model.gwk.LsTaskinfo;
import org.fbi.gwk.repository.model.gwk.LsTaskinfoExample;

public interface LsTaskinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int countByExample(LsTaskinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int deleteByExample(LsTaskinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int deleteByPrimaryKey(String taskid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int insert(LsTaskinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int insertSelective(LsTaskinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    List<LsTaskinfo> selectByExample(LsTaskinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    LsTaskinfo selectByPrimaryKey(String taskid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByExampleSelective(@Param("record") LsTaskinfo record, @Param("example") LsTaskinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByExample(@Param("record") LsTaskinfo record, @Param("example") LsTaskinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByPrimaryKeySelective(LsTaskinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.LS_TASKINFO
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByPrimaryKey(LsTaskinfo record);
}