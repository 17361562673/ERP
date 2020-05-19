/**
 * @ClassName ConsoleLog
 * @Authror zhouzhiqiang
 * @Date 2020/4/1 2:09
 * @description
 * @version 1.0
 */
package erp.model;

import java.sql.Timestamp;
import java.util.Objects;


public class ConsoleLog {
    private Integer logId;
    private Integer entityId;
    private String tableName;
    private String optType;
    private Timestamp optTime;
    private Integer empId;
    private String note;
    private Emp emp;

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Timestamp getOptTime() {
        return optTime;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleLog that = (ConsoleLog) o;
        return logId == that.logId &&
                Objects.equals(entityId, that.entityId) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(optType, that.optType) &&
                Objects.equals(optTime, that.optTime) &&
                Objects.equals(empId, that.empId) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, entityId, tableName, optType, optTime, empId, note);
    }
}
