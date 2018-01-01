package com.github.javahao.entity;

/**
 * usedfor：
 * Created by javahao on 2017/12/29.
 * auth：JavaHao
 */
public class Relation {

    private String target;
    private RelationEnum type;
    private String foreignKey;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public RelationEnum getType() {
        return type;
    }

    public void setType(String type) {
        this.type = RelationEnum.valueOf(type);
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relation)) return false;

        Relation relation = (Relation) o;

        if (target != null ? !target.equals(relation.target) : relation.target != null) return false;
        if (type != relation.type) return false;
        return foreignKey != null ? foreignKey.equals(relation.foreignKey) : relation.foreignKey == null;
    }

    @Override
    public int hashCode() {
        int result = target != null ? target.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (foreignKey != null ? foreignKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "target='" + target + '\'' +
                ", type=" + type +
                ", foreignKey='" + foreignKey + '\'' +
                '}';
    }
}
