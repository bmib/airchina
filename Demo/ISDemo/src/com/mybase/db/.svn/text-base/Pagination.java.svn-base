/**  
 * @title Pagination.java
 * @package com.mybase.db
 * @date 2013-3-22 下午09:34:53
 */ 
package com.mybase.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 分页对象
 * 含分页信息,分页查询相关SQL生成及翻页参数
 * @author Mr.liuyong
 */
public class Pagination {
	
	//每页记录条数
	private int m_iPageSize ;
	//当前页码
	private int m_iCurrerntPageNo ;
	//总页数
	private int m_iTotalPageCount ;
	//总记录数
	private int m_iTotalRecordCount ;
	//编码后的分页URL和param
	private String m_sEncodingUrlAndParam ;
	//查询SQL
	private String m_sSql;
	//总记录条数SQL
	private String m_sCountSql;
	//分页SQL
	private String m_sPageSql;
	
	
	/**
	 * 构造函数 PagingInfo.
	 */
	public Pagination(){}
	
	/**
	 * 构造函数 PagingInfo.
	 * @param request
	 * @param defaultPageSize
	 */
	public Pagination(int pageNo,int pageSize,String encodeUrlAndParam){
		m_iCurrerntPageNo = pageNo;
		m_iPageSize = pageSize;
		m_sEncodingUrlAndParam=encodeUrlAndParam;
	}
	
	/**
	 * 设置每页记录条数
	 * @param iPageSize
	 */
	public void setPageSize(int iPageSize){
		m_iPageSize = iPageSize;
	}
	
	/**
	 * 获取每页记录条数
	 * @return int
	 */
	public int getPageSize(){
		return m_iPageSize;
	}
	
	/**
	 * 设置当前页码
	 * @param iPageNo
	 */
	public void setCurrentPageNo(int iPageNo){
		m_iCurrerntPageNo = iPageNo;
	}
	
	/**
	 * 获取当前页码
	 * @return int
	 */
	public int getCurrentPageNo(){
		return m_iCurrerntPageNo;
	}
	
	/**
	 * 设置总记录数
	 * @param iRecordCount
	 */
	public void setTotalRecordCount(int iRecordCount){
		m_iTotalRecordCount = iRecordCount;
		if(m_iPageSize < 0){
			m_iTotalPageCount = 1;
		}else{
			m_iTotalPageCount = iRecordCount / m_iPageSize ;
			if(m_iTotalPageCount * m_iPageSize < iRecordCount){
				m_iTotalPageCount += 1;
			}
		}
	}
	
	/**
	 * 获取总页数
	 * @return int
	 */
	public int getTotalPageCount(){
		return m_iTotalPageCount;
	}
	
	/**
	 * 获取记录总数
	 * @return int
	 */
	public int getTotalRecordCount(){
		return m_iTotalRecordCount;
	}
	
	/**
	 * 获取Base64编码后的URL和param字符串
	 * @return String
	 */
	public String getEncodingUrlAndParam() {
		return m_sEncodingUrlAndParam;
	}
	
	/**
	 * 设置编码后的URL和param字符串
	 * @param sEncodingUrlAndParam
	 */
	public void setEncodingUrlAndParam(String sEncodingUrlAndParam) {
		m_sEncodingUrlAndParam = sEncodingUrlAndParam;
	}
	
	/**
	 * 获取查询SQL
	 * @return the m_sSql
	 */
	public String getSql() {
		return m_sSql;
	}
	
	/**
	 * 获取总记录条数SQL
	 * @return
	 */
	public String getCountSql(){
		return m_sCountSql;
	}
	
	/**
	 * 获取分页SQL
	 * @return
	 */
	public String getPageSql(){
		return m_sPageSql;
	}

	/**
	 * 设置查询SQL
	 * @param mSSql the m_sSql to set
	 */
	public void setSql(String sql) {
		m_sSql = sql;
	}
	
	/**
	 * 设置查询总记录条数SQL
	 * @param countSql
	 */
	public void setCountSql(String countSql) {
		m_sCountSql = countSql;
	}
	
	/**
	 * 设置分页SQL
	 * @param pageSql
	 */
	public void setPageSql(String pageSql) {
		m_sPageSql = pageSql;
	}

	/**
	 * 是否第一页
	 * @return boolean
	 */
	public boolean isFirstPage(){
		return m_iCurrerntPageNo <= 1;
	}
	
	/**
	 * 是否最后一页
	 * @return boolean
	 */
	public boolean isLastPage(){
		return m_iCurrerntPageNo >= m_iTotalPageCount;
	}
	
	/**
	 * 丰富分页信息,包括记录总条数，查询Sql，查询总条数Sql，查询分页Sql
	 * conn不会被关闭
	 * @param sql
	 * @param Pagination
	 * @return
	 * @throws SQLException 
	 */
	protected void richInfo(Connection conn,String sql,Object... params) throws SQLException{
		MyQueryRunner mqr=new MyQueryRunner();
		//查询记录总条数
		String countSql="SELECT COUNT(1) AS MYBASECC FROM("+sql+") MYBASETT";
		Record record=new Record();
		if(params==null){
			record=mqr.queryRecord(conn, countSql);
		}else{
			record=mqr.queryRecord(conn, countSql, params);
		}
		//拼装分页SQL
		StringBuffer sb=new StringBuffer();
		if(this.getPageSize()!=-1){
			int startIndex=(this.getCurrentPageNo()-1)*this.getPageSize();
			int endIndex=startIndex+this.getPageSize()-1;
			String databaseProductName=conn.getMetaData().getDatabaseProductName().toUpperCase();
			if(databaseProductName.indexOf("ORACLE") != -1){
				startIndex=startIndex+1;
				endIndex=endIndex+1;
				sb.append("SELECT * FROM (SELECT ROW_.*, ROWNUM ROWNUM_ FROM (")
				.append(sql).append(") ROW_ WHERE ROWNUM <= ").append(endIndex).append(") WHERE ROWNUM_ >= ").append(startIndex);
			}else if(databaseProductName.indexOf("MYSQL") != -1){
	        	sb.append(sql).append(" limit ").append(startIndex).append(",").append(this.getPageSize());
	        }else if(databaseProductName.indexOf("SQL") != -1 ){
	        	//TODO
	        }
		}else{
			//pageSize为-1时，默认代表不分页，查询全部
			sb.append(sql);
		}
		this.setSql(sql);
		this.setCountSql(countSql);
		this.setTotalRecordCount(record.getInt("MYBASECC"));
		this.setPageSql(sb.toString());
	}
}
