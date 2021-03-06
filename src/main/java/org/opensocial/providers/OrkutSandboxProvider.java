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

package org.opensocial.providers;

public class OrkutSandboxProvider extends Provider {

  public OrkutSandboxProvider() {
    this(false);
  }

  public OrkutSandboxProvider(boolean useRest) {
    super();

    setName("Orkut sandbox");
    setVersion("0.8");
    setRestEndpoint("http://sandbox.orkut.com/social/rest/");
    if (!useRest) {
      setRpcEndpoint("http://sandbox.orkut.com/social/rpc/");
    }
    setAuthorizeUrl("https://www.google.com/accounts/OAuthAuthorizeToken");
    setAccessTokenUrl("https://www.google.com/accounts/OAuthGetAccessToken");
    setRequestTokenUrl("https://www.google.com/accounts/OAuthGetRequestToken");
    addRequestTokenParameter("scope",
        "http://sandbox.orkut.gmodules.com/social/rpc");
  }
}
