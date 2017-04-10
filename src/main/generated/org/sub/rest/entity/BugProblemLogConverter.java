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

package org.sub.rest.entity;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link org.sub.rest.entity.BugProblemLog}.
 *
 * NOTE: This class has been automatically generated from the {@link org.sub.rest.entity.BugProblemLog} original class using Vert.x codegen.
 */
public class BugProblemLogConverter {

  public static void fromJson(JsonObject json, BugProblemLog obj) {
    if (json.getValue("logDate") instanceof String) {
      obj.setLogDate((String)json.getValue("logDate"));
    }
    if (json.getValue("logDescription") instanceof String) {
      obj.setLogDescription((String)json.getValue("logDescription"));
    }
    if (json.getValue("logWork") instanceof String) {
      obj.setLogWork((String)json.getValue("logWork"));
    }
  }

  public static void toJson(BugProblemLog obj, JsonObject json) {
    if (obj.getLogDate() != null) {
      json.put("logDate", obj.getLogDate());
    }
    if (obj.getLogDescription() != null) {
      json.put("logDescription", obj.getLogDescription());
    }
    if (obj.getLogWork() != null) {
      json.put("logWork", obj.getLogWork());
    }
  }
}