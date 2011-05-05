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
 * OpenSocial model class representing an message.
 */
public class Message extends Model {

  /**
   * Returns the message's unique identifier.
   */
  public String getId() {
    return getFieldAsString("id");
  }

  /**
   * Returns the message's body, a string specifying an optional expanded
   * version of an message.
   */
  public String getBody() {
    return getFieldAsString("body");
  }

  /**
   * Returns the message's body ID, a string specifying the body template
   * message ID in the accompanying gadget specification.
   */
  public String getBodyId() {
    return getFieldAsString("bodyId");
  }

  /**
   * Returns the message's title, a string specifying the primary text of an
   * message.
   */
  public String getTitle() {
    return getFieldAsString("title");
  }

  /**
   * Returns the message's title ID, a string specifying the title template
   * message ID in the accompanying gadget specification.
   */
  public String getTitleId() {
    return getFieldAsString("titleId");
  }

  /**
   * Sets the message's body, a string specifying an optional expanded version
   * of an message
   * 
   * @param body
   *          body to set
   */
  public void setBody(String body) {
    put("body", body);
  }

  /**
   * Sets the message's body ID, a string specifying the body template message
   * ID in the accompanying gadget specification.
   * 
   * @param bodyId
   *          body ID to set
   */
  public void setBodyId(String bodyId) {
    put("bodyId", bodyId);
  }

  /**
   * Sets the message's title, a string specifying the primary text of an
   * message.
   * 
   * @param title
   *          title to set
   */
  public void setTitle(String title) {
    put("title", title);
  }

  /**
   * Sets the message's title ID, a string specifying the title template
   * message ID in the accompanying gadget specification.
   * 
   * @param titleId
   *          title ID to set
   */
  public void setTitleId(String titleId) {
    put("titleId", titleId);
  }

  public void setMessageCollectionId(String messageCollectionId) {
    super.addToListField("collectionIds", messageCollectionId);
  }
  
  public void setRecipient(String recipient) {
    super.addToListField("recipients", recipient);
  }
}
