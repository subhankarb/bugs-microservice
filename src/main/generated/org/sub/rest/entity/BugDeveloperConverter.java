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
 * Converter for {@link org.sub.rest.entity.BugDeveloper}.
 *
 * NOTE: This class has been automatically generated from the {@link org.sub.rest.entity.BugDeveloper} original class using Vert.x codegen.
 */
public class BugDeveloperConverter {

  public static void fromJson(JsonObject json, BugDeveloper obj) {
    if (json.getValue("details") instanceof String) {
      obj.setDetails((String)json.getValue("details"));
    }
    if (json.getValue("developerName") instanceof String) {
      obj.setDeveloperName((String)json.getValue("developerName"));
    }
  }

  public static void toJson(BugDeveloper obj, JsonObject json) {
    if (obj.getDetails() != null) {
      json.put("details", obj.getDetails());
    }
    if (obj.getDeveloperName() != null) {
      json.put("developerName", obj.getDeveloperName());
    }
  }
}