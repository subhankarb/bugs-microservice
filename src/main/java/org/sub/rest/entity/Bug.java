package org.sub.rest.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
public class Bug {

    private String bugId;
    private String bugDateReported;
    private String bugDateClosed;
    private String bugDescription;
    private String bugReporter;
    private List<BugProblemLog> problemLogs;
    private BugDeveloper bugDeveloper;
    private String module;
    private String moduleName;

    public Bug(){}
    public Bug(JsonObject json){
        Bug.fromJson(json, this);
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getBugDateReported() {
        return bugDateReported;
    }

    public void setBugDateReported(String bugDateReported) {
        this.bugDateReported = bugDateReported;
    }

    public String getBugDateClosed() {
        return bugDateClosed;
    }

    public void setBugDateClosed(String bugDateClosed) {
        this.bugDateClosed = bugDateClosed;
    }

    public String getBugDescription() {
        return bugDescription;
    }

    public void setBugDescription(String bugDescription) {
        this.bugDescription = bugDescription;
    }

    public String getBugReporter() {
        return bugReporter;
    }

    public void setBugReporter(String bugReporter) {
        this.bugReporter = bugReporter;
    }

    public List<BugProblemLog> getProblemLogs() {
        return problemLogs;
    }

    public void setProblemLogs(List<BugProblemLog> problemLogs) {
        this.problemLogs = problemLogs;
    }

    public BugDeveloper getBugDeveloper() {
        return bugDeveloper;
    }

    public void setBugDeveloper(BugDeveloper bugDeveloper) {
        this.bugDeveloper = bugDeveloper;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        Bug.toJson(this, json);
        return json;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "bugId='" + bugId + '\'' +
                ", bugDateReported='" + bugDateReported + '\'' +
                ", bugDateClosed='" + bugDateClosed + '\'' +
                ", bugDescription='" + bugDescription + '\'' +
                ", bugReporter='" + bugReporter + '\'' +
                ", problemLogs=" + problemLogs +
                ", bugDeveloper=" + bugDeveloper +
                ", module='" + module + '\'' +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }

    public static void fromJson(JsonObject json, Bug obj) {
        if (json.getValue("bug_id") instanceof String) {
            obj.setBugId((String)json.getValue("bug_id"));
        }
        if (json.getValue("bug_date_reported") instanceof String) {
            obj.setBugDateReported((String)json.getValue("bug_date_reported"));
        }
        if (json.getValue("bug_date_closed") instanceof Number) {
            obj.setBugDateClosed((String)json.getValue("bug_date_closed"));
        }
        if (json.getValue("bug_description") instanceof String) {
            obj.setBugDescription((String)json.getValue("bug_description"));
        }
        if (json.getValue("bug_reporter") instanceof String) {
            obj.setBugReporter((String)json.getValue("bug_reporter"));
        }
        if (json.getValue("module") instanceof String) {
            obj.setModule((String)json.getValue("module"));
        }
        if (json.getValue("module_name") instanceof String) {
            obj.setModuleName((String)json.getValue("module_name"));
        }
        if (json.getValue("developers") instanceof JsonObject) {
            BugDeveloper bugDeveloper = new BugDeveloper();
            BugDeveloper.fromJson(json.getJsonObject("developers"), new BugDeveloper());
            obj.setBugDeveloper(bugDeveloper);
        }
        if (json.getValue("problem_log") instanceof JsonArray) {
            List<BugProblemLog> bugProblemLogs = new ArrayList<>();
            for (Object o : json.getJsonArray("problem_log")){
                BugProblemLog bugProblemLog = new BugProblemLog();
                BugProblemLog.fromJson((JsonObject) o, bugProblemLog);
                bugProblemLogs.add(bugProblemLog);
            }
            obj.setProblemLogs(bugProblemLogs);
        }
    }

    public static void toJson(Bug obj, JsonObject json) {
        if (obj.getBugId() != null) {
            json.put("bug_id", obj.getBugId());
        }
        if (obj.getBugDateReported() != null) {
            json.put("bug_date_reported", obj.getBugDateReported());
        }
        if (obj.getBugDateClosed() != null) {
            json.put("bug_date_closed", obj.getBugDateClosed());
        }
        if (obj.getBugDescription() != null) {
            json.put("bug_description", obj.getBugDescription());
        }
        if (obj.getBugReporter() != null) {
            json.put("bug_reporter", obj.getBugReporter());
        }
        if (obj.getModule() != null) {
            json.put("module", obj.getModule());
        }
        if (obj.getModuleName() != null) {
            json.put("module_name", obj.getModuleName());
        }
        if (obj.getBugDeveloper() != null) {
            JsonObject bugDeveloper = new JsonObject();
            BugDeveloper.toJson(obj.getBugDeveloper(), bugDeveloper);
            json.put("developers", bugDeveloper);
        }
        if (obj.getProblemLogs() != null) {
            JsonArray problemLogs = new JsonArray();
            for (BugProblemLog bugProblemLog: obj.getProblemLogs()){
                JsonObject bugProblemLogO = new JsonObject();
                BugProblemLog.toJson(bugProblemLog, bugProblemLogO);
                problemLogs.add(bugProblemLogO);
            }
            json.put("problem_log", problemLogs);
        }
    }
}
