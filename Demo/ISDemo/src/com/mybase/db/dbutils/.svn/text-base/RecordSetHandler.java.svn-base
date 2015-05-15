package com.mybase.db.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

import com.mybase.db.RecordSet;

/**
 * 
 * RecordSetHandler
 * DBUtils转换类型扩展
 * @author Mr.liuyong
 * RecordSetHandler implementation that converts a
 * ResultSet into a RecordSet.This class is thread safe.
 * 
 */
public class RecordSetHandler implements ResultSetHandler<RecordSet> {

	/**
	 * 将ResultSet转换为RecordSet
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	@Override
    public RecordSet handle(ResultSet rs) throws SQLException {
        return RecordSet.generateFrom(rs);
    }

}
