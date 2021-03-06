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

package org.opensocial.online;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.opensocial.Client;
import org.opensocial.Request;
import org.opensocial.RequestException;
import org.opensocial.Response;
import org.opensocial.auth.OAuth2LeggedScheme;
import org.opensocial.models.Person;
import org.opensocial.providers.GoogleProvider;
import org.opensocial.providers.MySpaceProvider;
import org.opensocial.providers.OrkutProvider;
import org.opensocial.services.PeopleService;

public class PeopleTestIntegration {

  private static final String ORKUT_KEY = "orkut.com:623061448914";
  private static final String ORKUT_SECRET = "uynAeXiWTisflWX99KU1D2q5";
  private static final String ORKUT_ID = "03067092798963641994";

  private static final String GOOGLE_KEY = "google.com:249475676706";
  private static final String GOOGLE_SECRET = "fWPcoVP6DOLVqZOF2HH+ihU2";
  private static final String GOOGLE_ID = "101911127807751034357";

  private static final String MYSPACE_KEY = "http://www.myspace.com/495182150";
  private static final String MYSPACE_SECRET = "20ab52223e684594a8050a8bfd4b06693ba9c9183ee24e1987be87746b1b03f8";
  private static final String MYSPACE_ID = "495184236";

  @Test
  public void retrieveSelfFromOrkutUsingRpc() throws RequestException,
      IOException {
    retrieveSelfFromOrkut(false);
  }

  @Test
  public void retrieveSelfFromOrkutUsingRest() throws RequestException,
      IOException {
    retrieveSelfFromOrkut(true);
  }

  private void retrieveSelfFromOrkut(boolean useRest) throws RequestException,
      IOException {

    Client client = new Client(new OrkutProvider(useRest),
        new OAuth2LeggedScheme(ORKUT_KEY, ORKUT_SECRET, ORKUT_ID));
    Request request = PeopleService.getViewer();
    Response response = client.send(request);

    Person self = response.getEntry();
    assertTrue(self.getId() != null);
    assertTrue(self.getDisplayName() != null);

  }

  @Test
  public void retrieveFriendsFromOrkutUsingRpc() throws RequestException, IOException {
    retrieveFriendsFromOrkut(false);
  }

  @Test
  public void retrieveFriendsFromOrkutUsingRest() throws RequestException, IOException {
    retrieveFriendsFromOrkut(true);
  }

  private void retrieveFriendsFromOrkut(boolean useRest) throws RequestException, IOException {

    Client client = new Client(new OrkutProvider(useRest),
        new OAuth2LeggedScheme(ORKUT_KEY, ORKUT_SECRET, ORKUT_ID));
    Request request = PeopleService.getFriends();
    Response response = client.send(request);

    Person self = response.getEntry();
    assertTrue(self.getId() != null);
    assertTrue(self.getDisplayName() != null);

  }

  @Test
  public void retrieveSelfFromGoogle() {
    try {
      Client client = new Client(new GoogleProvider(), new OAuth2LeggedScheme(
          GOOGLE_KEY, GOOGLE_SECRET, GOOGLE_ID));
      Request request = PeopleService.getViewer();
      Response response = client.send(request);

      Person self = response.getEntry();
      assertTrue(self.getId() != null);
      assertTrue(self.getDisplayName() != null);
    } catch (Exception e) {
      fail("Exception occurred while processing request");
    }
  }

  @Test
  public void retrieveSelfFromMySpace() {
    try {
      Client client = new Client(new MySpaceProvider(), new OAuth2LeggedScheme(
          MYSPACE_KEY, MYSPACE_SECRET, MYSPACE_ID));
      Request request = PeopleService.getViewer();
      Response response = client.send(request);

      Person self = response.getEntry();
      assertTrue(self.getId() != null);
      assertTrue(self.getDisplayName() != null);
    } catch (Exception e) {
      fail("Exception occurred while processing request");
    }
  }
}
