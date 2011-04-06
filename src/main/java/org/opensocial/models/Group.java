/* Copyright (c) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensocial.models;

import java.util.Map;

/**
 * OpenSocial model class representing a group, which is used to tag or
 * categorize people and their relationships. For reference:
 * http://www.opensocial.org/Technical-Resources/opensocial-spec-v09/REST-API.html#rfc.section.3.3
 *
 * @author Jason Cooper
 */
public class Group extends Model {

  /**
   * Returns the group's unique identifier.
   */
  public String getId() {
    Object field = getField("id");
    try {
      Map id = (Map) field;
      return id != null ? (String) id.get("groupId") : null;
    } catch (ClassCastException e) {
      return getFieldAsString("id");
    }
  }

  /**
   * Returns the group's title or user-defined name.
   */
  public String getTitle() {
    return getFieldAsString("title");
  }
}
