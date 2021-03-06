package org.fbi.gwk.repository.dao.gwk;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.fbi.gwk.repository.model.gwk.OdsbCrdCrtAcct;
import org.fbi.gwk.repository.model.gwk.OdsbCrdCrtAcctExample;
import org.fbi.gwk.repository.model.gwk.OdsbCrdCrtAcctKey;

public interface OdsbCrdCrtAcctMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int countByExample(OdsbCrdCrtAcctExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int deleteByExample(OdsbCrdCrtAcctExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int deleteByPrimaryKey(OdsbCrdCrtAcctKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int insert(OdsbCrdCrtAcct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int insertSelective(OdsbCrdCrtAcct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    List<OdsbCrdCrtAcct> selectByExample(OdsbCrdCrtAcctExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    OdsbCrdCrtAcct selectByPrimaryKey(OdsbCrdCrtAcctKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByExampleSelective(@Param("record") OdsbCrdCrtAcct record, @Param("example") OdsbCrdCrtAcctExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByExample(@Param("record") OdsbCrdCrtAcct record, @Param("example") OdsbCrdCrtAcctExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByPrimaryKeySelective(OdsbCrdCrtAcct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GWK.ODSB_CRD_CRT_ACCT
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    int updateByPrimaryKey(OdsbCrdCrtAcct record);
}