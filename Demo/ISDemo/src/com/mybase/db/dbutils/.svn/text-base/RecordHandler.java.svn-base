package com.mybase.db.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

import com.mybase.db.Record;

/**
 * 
 * RecordHandler
 * DBUtils转换类型扩展
 * @author Mr.liuyong
 * RecordHandler implementation that converts the first
 * ResultSet row into a Record. This class is thread safe.
 *
 */
public class RecordHandler implements ResultSetHandler<Record> {

    /**
     * 将单ResultSet转换为Record
     * @param rs
     * @return
     * @throws SQLException
     */
    @Override
    public Record handle(ResultSet rs) throws SQLException {
    	return Record.generateFrom(rs.next()?rs:null);
    }

}
