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
    private String var;
    private String varDesc;

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

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getVarDesc() {
        return varDesc;
    }

    public void setVarDesc(String varDesc) {
        this.varDesc = varDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (target != null ? !target.equals(relation.target) : relation.target != null) return false;
        if (type != relation.type) return false;
        if (foreignKey != null ? !foreignKey.equals(relation.foreignKey) : relation.foreignKey != null) return false;
        if (var != null ? !var.equals(relation.var) : relation.var != null) return false;
        return varDesc != null ? varDesc.equals(relation.varDesc) : relation.varDesc == null;
    }

    @Override
    public int hashCode() {
        int result = target != null ? target.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (foreignKey != null ? foreignKey.hashCode() : 0);
        result = 31 * result + (var != null ? var.hashCode() : 0);
        result = 31 * result + (varDesc != null ? varDesc.hashCode() : 0);
        return result;
    }
}
