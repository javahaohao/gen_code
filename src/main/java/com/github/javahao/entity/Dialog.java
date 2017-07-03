package com.github.javahao.entity;

/**
 * usedfor：数据库方言实体类
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class Dialog {
    /**
     * 方言名称
     */
    private String name;
    /**
     * 驱动类
     */
    private String driver;
    /**
     * 连接url
     */
    private String url;
    /**
     * 连接数据库用户名
     */
    private String username;
    /**
     * 连接数据库密码
     */
    private String password;

    public Dialog() {
    }

    public Dialog(String name, String driver, String url, String username, String password) {
        this.name = name;
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password==null?"":password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Dialog{" +
                "name='" + name + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
