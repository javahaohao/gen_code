package com.github.javahao.db;

import com.github.javahao.config.CoreConfig;
import com.github.javahao.config.TableConfig;
import com.github.javahao.entity.Column;
import com.github.javahao.entity.Table;
import com.github.javahao.util.FreeMarkerUtil;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * usedfor：生成表以及字段查询
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class TableQuery {
    private static TableQuery tq = new TableQuery();
    public static TableQuery getInstance(){
        return tq;
    }
    /**
     * 按照配置获取生成表的基本信息
     * @param tc 生成表的配置
     * @return 返回结果
     */
    public Table getTable(TableConfig tc){
        Table table = new Table();
        try{
            java.sql.Connection conn = Connection.getInstance().getConnection();
            DatabaseMetaData dmd = conn.getMetaData();

            ResultSet tables = dmd.getTables(tc.getCatalog(), tc.getSchema(), tc
                    .getTableName(), tc.getTypes());
            while (tables.next())
            {
                table.setTableName(tables.getString("TABLE_NAME"));
                table.setTableComment(tables.getString("REMARKS"));

                List keys = new ArrayList();
                ResultSet priKeys = dmd.getPrimaryKeys(tc.getCatalog(), tc
                        .getSchema(), table.getTableName());
                while (priKeys.next()) {
                    keys.add(priKeys.getString("COLUMN_NAME"));
                }

                ResultSet columns = dmd.getColumns(tc.getCatalog(), tc
                        .getSchema(), tc.getTableName(), "%");
                while (columns.next()) {
                    Column c = new Column();
                    c.setColumnName(columns.getString("COLUMN_NAME"));
                    c.setPrimary(keys.contains(c.getColumnName()));
                    c.setDataType(columns.getString("TYPE_NAME"));
                    c.setNullable(columns.getInt("NULLABLE")!=0);
                    c.setColumnComment(columns.getString("REMARKS"));
                    c.setColumnLength(columns.getLong("COLUMN_SIZE"));
                    table.addColumns(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Connection.getInstance().close();
        }
        if(table!=null)
            //将配置应用到表中
            table.setTableConfig(tc);
        return table;
    }
}
