/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.sub.bug.entity;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link org.sub.bug.entity.Bug}.
 *
 * NOTE: This class has been automatically generated from the {@link org.sub.bug.entity.Bug} original class using Vert.x codegen.
 */
public class BugConverter {

  public static void fromJson(JsonObject json, Bug obj) {
    if (json.getValue("bugDateClosed") instanceof String) {
      obj.setBugDateClosed((String)json.getValue("bugDateClosed"));
    }
    if (json.getValue("bugDateReported") instanceof String) {
      obj.setBugDateReported((String)json.getValue("bugDateReported"));
    }
    if (json.getValue("bugDescription") instanceof String) {
      obj.setBugDescription((String)json.getValue("bugDescription"));
    }
    if (json.getValue("bugDeveloper") instanceof JsonObject) {
      obj.setBugDeveloper(new org.sub.bug.entity.BugDeveloper((JsonObject)json.getValue("bugDeveloper")));
    }
    if (json.getValue("bugId") instanceof String) {
      obj.setBugId((String)json.getValue("bugId"));
    }
    if (json.getValue("bugReporter") instanceof String) {
      obj.setBugReporter((String)json.getValue("bugReporter"));
    }
    if (json.getValue("module") instanceof String) {
      obj.setModule((String)json.getValue("module"));
    }
    if (json.getValue("moduleName") instanceof String) {
      obj.setModuleName((String)json.getValue("moduleName"));
    }
    if (json.getValue("problemLogs") instanceof JsonArray) {
      java.util.ArrayList<org.sub.bug.entity.BugProblemLog> list = new java.util.ArrayList<>();
      json.getJsonArray("problemLogs").forEach( item -> {
        if (item instanceof JsonObject)
          list.add(new org.sub.bug.entity.BugProblemLog((JsonObject)item));
      });
      obj.setProblemLogs(list);
    }
  }

  public static void toJson(Bug obj, JsonObject json) {
    if (obj.getBugDateClosed() != null) {
      json.put("bugDateClosed", obj.getBugDateClosed());
    }
    if (obj.getBugDateReported() != null) {
      json.put("bugDateReported", obj.getBugDateReported());
    }
    if (obj.getBugDescription() != null) {
      json.put("bugDescription", obj.getBugDescription());
    }
    if (obj.getBugDeveloper() != null) {
      json.put("bugDeveloper", obj.getBugDeveloper().toJson());
    }
    if (obj.getBugId() != null) {
      json.put("bugId", obj.getBugId());
    }
    if (obj.getBugReporter() != null) {
      json.put("bugReporter", obj.getBugReporter());
    }
    if (obj.getModule() != null) {
      json.put("module", obj.getModule());
    }
    if (obj.getModuleName() != null) {
      json.put("moduleName", obj.getModuleName());
    }
    if (obj.getProblemLogs() != null) {
      JsonArray array = new JsonArray();
      obj.getProblemLogs().forEach(item -> array.add(item.toJson()));
      json.put("problemLogs", array);
    }
  }
}