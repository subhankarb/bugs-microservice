package org.sub.rest.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class BugProblemLog {
    private String logDate;
    private String logDescription;
    private String logWork;

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public String getLogWork() {
        return logWork;
    }

    public void setLogWork(String logWork) {
        this.logWork = logWork;
    }

    @Override
    public String toString() {
        return "BugProblemLog{" +
                "logDate='" + logDate + '\'' +
                ", logDescription='" + logDescription + '\'' +
                ", logWork='" + logWork + '\'' +
                '}';
    }

    public static void fromJson(JsonObject json, BugProblemLog obj){
        if (json.getValue("log_date") instanceof String) {
            obj.setLogDate((String)json.getValue("log_date"));
        }
        if (json.getValue("log_description") instanceof String) {
            obj.setLogDescription((String)json.getValue("log_description"));
        }
        if (json.getValue("log_work") instanceof String) {
            obj.setLogWork((String)json.getValue("log_work"));
        }
    }
    public static void toJson(BugProblemLog obj, JsonObject json) {
        if (obj.getLogDate() != null) {
            json.put("log_date", obj.getLogDate());
        }
        if (obj.getLogDescription() != null) {
            json.put("log_description", obj.getLogDescription());
        }
        if (obj.getLogWork() != null) {
            json.put("log_work", obj.getLogWork());
        }
    }
}
