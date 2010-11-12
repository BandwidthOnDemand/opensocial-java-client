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

import org.junit.BeforeClass;
import org.junit.Test;
import org.opensocial.Client;
import org.opensocial.Request;
import org.opensocial.RequestException;
import org.opensocial.Response;
import org.opensocial.auth.OAuth2LeggedScheme;
import org.opensocial.models.AppData;
import org.opensocial.providers.OrkutSandboxProvider;
import org.opensocial.services.AppDataService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AppDataTestIntegration {

  private static final String ORKUT_KEY = "orkut.com:623061448914";
  private static final String ORKUT_SECRET = "uynAeXiWTisflWX99KU1D2q5";
  private static final String ORKUT_ID = "03067092798963641994";

  @BeforeClass
  public static void setup() throws RequestException, IOException {
    Client client = new Client(new OrkutSandboxProvider(),
        new OAuth2LeggedScheme(ORKUT_KEY, ORKUT_SECRET, ORKUT_ID));

    Request request = AppDataService.updateAppData("java", "rocks");
    client.send(request);

  }

  @Test
  public void retrieveFromOrkutUsingRpc() {
    retrieveFromOrkut(false);
  }

  @Test
  public void retrieveFromOrkutUsingRest() {
    retrieveFromOrkut(true);
  }

  private void retrieveFromOrkut(boolean useRest) {
    try {
      Client client = new Client(new OrkutSandboxProvider(useRest),
          new OAuth2LeggedScheme(ORKUT_KEY, ORKUT_SECRET, ORKUT_ID));
      Request request = AppDataService.getAppData();
      Response response = client.send(request);

      AppData data = response.getEntry();
      assertTrue(data.hasField(ORKUT_ID));
    } catch (Exception e) {
      fail("Exception occurred while processing request");
    }
  }

  @Test
  public void singleUpdateAndRetrieveFromOrkutUsingRpc() throws RequestException, IOException {
    singleUpdateAndRetrieveFromOrkut(false);
  }

  @Test
  public void singleUpdateAndRetrieveFromOrkutUsingRest() throws RequestException, IOException {
    singleUpdateAndRetrieveFromOrkut(true);
  }

  private void singleUpdateAndRetrieveFromOrkut(boolean useRest) throws RequestException, IOException {
    Random generator = new Random();
    String randomValue = String.valueOf(generator.nextInt());

    Client client = new Client(new OrkutSandboxProvider(useRest),
        new OAuth2LeggedScheme(ORKUT_KEY, ORKUT_SECRET, ORKUT_ID));

   
      Request request = AppDataService.updateAppData("key", randomValue);
      client.send(request);
   

    
       request = AppDataService.getAppData();
      Response response = client.send(request);

      AppData data = response.getEntry();
      assertTrue(data.getDataForUser(ORKUT_ID, "key").equals(randomValue));
    
       request = AppDataService.deleteAppData("key");
      client.send(request);
    
       request = AppDataService.getAppData();
       response = client.send(request);

       data = response.getEntry();
      assertTrue(data.hasField(ORKUT_ID));
      assertTrue(data.getDataForUser(ORKUT_ID, "key") == null);
      assertTrue(data.getDataForUser(ORKUT_ID, "java") != null);
    
  }

  @Test
  public void multiUpdateRetrieveAndDeleteFromOrkutUsingRpc() throws RequestException, IOException {
    multiUpdateRetrieveAndDeleteFromOrkut(false);
  }

  @Test
  public void multiUpdateRetrieveAndDeleteFromOrkutUsingRest() throws RequestException, IOException {
    multiUpdateRetrieveAndDeleteFromOrkut(true);
  }

  private void multiUpdateRetrieveAndDeleteFromOrkut(boolean useRest) throws RequestException, IOException {
    Random generator = new Random();
    String randomValue1 = String.valueOf(generator.nextInt());
    String randomValue2 = String.valueOf(generator.nextInt());

    Client client = new Client(new OrkutSandboxProvider(useRest),
        new OAuth2LeggedScheme(ORKUT_KEY, ORKUT_SECRET, ORKUT_ID));

    Map<String, String> data = new HashMap<String, String>();
    data.put("key1", randomValue1);
    data.put("key2", randomValue2);

    Request request = AppDataService.updateAppData(data);
    client.send(request);

     request = AppDataService.getAppData();
    Response response = client.send(request);

    AppData appData = response.getEntry();
    assertTrue(appData.getDataForUser(ORKUT_ID, "key1").equals(randomValue1));
    assertTrue(appData.getDataForUser(ORKUT_ID, "key2").equals(randomValue2));

     request = AppDataService.deleteAppData(new String[] { "key1",
        "key2" });
    client.send(request);

     request = AppDataService.getAppData();
     response = client.send(request);

     data = response.getEntry();
    assertTrue(appData.hasField(ORKUT_ID));
    assertTrue(appData.getDataForUser(ORKUT_ID, "key1") != null);
    assertTrue(appData.getDataForUser(ORKUT_ID, "key2") != null);
    assertTrue(appData.getDataForUser(ORKUT_ID, "java") != null);

  }
}
