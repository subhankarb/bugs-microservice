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

/** @module vertx-blueprint-bug-js/bug_crud_service */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JBugCRUDService = Java.type('org.sub.rest.BugCRUDService');
var Bug = Java.type('org.sub.rest.entity.Bug');

/**
 @class
*/
var BugCRUDService = function(j_val) {

  var j_bugCRUDService = j_val;
  var that = this;

  /**

   @public
   @param bug {Object} 
   @param resultHandler {function} 
   */
  this.saveBug = function(bug, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_bugCRUDService["saveBug(org.sub.rest.entity.Bug,io.vertx.core.Handler)"](bug != null ? new Bug(new JsonObject(Java.asJSONCompatible(bug))) : null, function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param bugId {string} 
   @param resultHandler {function} 
   */
  this.retrieveBug = function(bugId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_bugCRUDService["retrieveBug(java.lang.String,io.vertx.core.Handler)"](bugId, function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnDataObject(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param bugId {string} 
   @param resultHandler {function} 
   */
  this.removeBug = function(bugId, resultHandler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_bugCRUDService["removeBug(java.lang.String,io.vertx.core.Handler)"](bugId, function(ar) {
      if (ar.succeeded()) {
        resultHandler(null, null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_bugCRUDService;
};

BugCRUDService._jclass = utils.getJavaClass("org.sub.rest.BugCRUDService");
BugCRUDService._jtype = {
  accept: function(obj) {
    return BugCRUDService._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(BugCRUDService.prototype, {});
    BugCRUDService.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
BugCRUDService._create = function(jdel) {
  var obj = Object.create(BugCRUDService.prototype, {});
  BugCRUDService.apply(obj, arguments);
  return obj;
}
module.exports = BugCRUDService;