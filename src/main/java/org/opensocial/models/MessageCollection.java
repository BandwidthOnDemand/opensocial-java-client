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

/**
 * OpenSocial model class representing an messageCollection.
 */
public class MessageCollection extends Model {

  /**
   * Returns the messageCollection's unique identifier.
   */
  public String getId() {
    return getFieldAsString("id");
  }

  /**
   * Returns the messageCollection's title, a string specifying the primary text of an
   * activity.
   */
  public String getTitle() {
    return getFieldAsString("title");
  }


 

  /**
   * Sets the messageCollection's title, a string specifying the primary text of an
   * activity.
   * 
   * @param title
   *          title to set
   */
  public void setTitle(String title) {
    put("title", title);
  }

 
}
