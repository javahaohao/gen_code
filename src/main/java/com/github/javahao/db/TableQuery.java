package com.github.javahao.db;

import com.github.javahao.config.CoreConfig;
import com.github.javahao.config.TableConfig;
import com.github.javahao.entity.Column;
import com.github.javahao.entity.Table;
import com.github.javahao.util.FreeMarkerUtil;

import java.sql.*;
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
     * 按照配置获取生成表的基本信息，并关闭连接池
     * @param tc 生成表的配置
     * @return 返回结果
     */
    public Table getTable(TableConfig tc){
        return getTable(tc,true);
    }
    /**
     * 按照配置获取生成表的基本信息
     * @param tc 生成表的配置
     * @param close 是否关闭连接池
     * @return 返回结果
     */
    public Table getTable(TableConfig tc,boolean close){
        Table table = new Table();
        try{
            java.sql.Connection conn = Connection.getInstance().getConnection();
            DatabaseMetaData dmd = conn.getMetaData();

            ResultSet tables = dmd.getTables(tc.getCatalog(), tc.getSchema(), tc
                    .getTableName(), tc.getTypes());
//            printItems(tables);
            while (tables.next())
            {
                table.setTableCat(tables.getString("TABLE_CAT"));
                table.setTableSchema(tables.getString("TABLE_SCHEM"));
                table.setTableType(tables.getString("TABLE_TYPE"));
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
//                printItems(columns);
                while (columns.next()) {
                    Column c = new Column();
                    c.setColumnName(columns.getString("COLUMN_NAME"));
                    c.setPrimary(keys.contains(c.getColumnName()));
                    c.setDataType(columns.getString("TYPE_NAME"));
                    c.setNullable(CoreConfig.YES.equals(columns.getString("NULLABLE")));
                    c.setColumnComment(columns.getString("REMARKS"));
                    c.setColumnLength(columns.getLong("COLUMN_SIZE"));
                    c.setOrdinalPosition(Integer.parseInt(columns.getString("ORDINAL_POSITION")));
                    c.setAutoincrement(CoreConfig.YES.equals(columns.getString("IS_AUTOINCREMENT")));
                    c.setGeneratedColumn(CoreConfig.YES.equals(columns.getString("IS_GENERATEDCOLUMN")));
                    table.addColumns(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(close)
                Connection.getInstance().close();
        }
        if(table!=null)
            //将配置应用到表中
            table.setTableConfig(tc);
        return table;
    }

    public void printItems(ResultSet rs){
        ResultSetMetaData rm = null;
        try {
            rm = rs.getMetaData();
            int columnNum = rm.getColumnCount();
            for (int i=1;i<=columnNum;i++){
                System.out.println(i+"\t"+rm.getColumnName(i));
            }
        }catch (Exception e){

        }
    }
}
