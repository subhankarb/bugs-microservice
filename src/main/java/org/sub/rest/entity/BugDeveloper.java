package org.sub.rest.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class BugDeveloper {

    private String developerName;
    private String details;

    public BugDeveloper(){}
    public BugDeveloper(JsonObject json){
        BugDeveloper.fromJson(json, this);
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BugDeveloper.toJson(this, json);
        return json;
    }

    @Override
    public String toString() {
        return "BugDeveloper{" +
                "developerName='" + developerName + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public static void fromJson(JsonObject json, BugDeveloper obj){
        if (json.getValue("developer_name") instanceof String) {
            obj.setDeveloperName((String)json.getValue("developer_name"));
        }
        if (json.getValue("details") instanceof String) {
            obj.setDetails((String)json.getValue("details"));
        }
    }
    public static void toJson(BugDeveloper obj, JsonObject json) {
        if (obj.getDeveloperName() != null) {
            json.put("developer_name", obj.getDeveloperName());
        }
        if (obj.getDetails() != null) {
            json.put("details", obj.getDetails());
        }
    }
}
