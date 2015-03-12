package org.fbi.gwk.repository.model.gwk;

public class LsSysinfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GWK.LS_SYSINFO.AREACODE
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    private String areacode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GWK.LS_SYSINFO.BDGAGENCYVERSION
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    private Long bdgagencyversion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GWK.LS_SYSINFO.BUSINESSDATE
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    private String businessdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GWK.LS_SYSINFO.AREACODE
     *
     * @return the value of GWK.LS_SYSINFO.AREACODE
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GWK.LS_SYSINFO.AREACODE
     *
     * @param areacode the value for GWK.LS_SYSINFO.AREACODE
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GWK.LS_SYSINFO.BDGAGENCYVERSION
     *
     * @return the value of GWK.LS_SYSINFO.BDGAGENCYVERSION
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public Long getBdgagencyversion() {
        return bdgagencyversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GWK.LS_SYSINFO.BDGAGENCYVERSION
     *
     * @param bdgagencyversion the value for GWK.LS_SYSINFO.BDGAGENCYVERSION
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void setBdgagencyversion(Long bdgagencyversion) {
        this.bdgagencyversion = bdgagencyversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GWK.LS_SYSINFO.BUSINESSDATE
     *
     * @return the value of GWK.LS_SYSINFO.BUSINESSDATE
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public String getBusinessdate() {
        return businessdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GWK.LS_SYSINFO.BUSINESSDATE
     *
     * @param businessdate the value for GWK.LS_SYSINFO.BUSINESSDATE
     *
     * @mbggenerated Mon Mar 09 17:29:36 CST 2015
     */
    public void setBusinessdate(String businessdate) {
        this.businessdate = businessdate == null ? null : businessdate.trim();
    }
}