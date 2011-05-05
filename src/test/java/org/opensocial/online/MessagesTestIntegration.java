package org.opensocial.online;

import java.io.IOException;

import org.junit.Test;
import org.opensocial.Client;
import org.opensocial.Request;
import org.opensocial.RequestException;
import org.opensocial.Response;
import org.opensocial.auth.AuthScheme;
import org.opensocial.auth.OAuth2LeggedScheme;
import org.opensocial.models.Message;
import org.opensocial.models.MessageCollection;
import org.opensocial.models.Model;
import org.opensocial.providers.Provider;
import org.opensocial.providers.ShindigProvider;
import org.opensocial.services.MessagesService;

public class MessagesTestIntegration {

  @Test
  public void testCreateMessage() throws RequestException, IOException {
    Client client = getShindigClient();

    MessageCollection messageCollection = new MessageCollection();
    messageCollection.setTitle("sentToTeam7");
    Request request = MessagesService.createMessageCollection(messageCollection);
    Response send = client.send(request);
    MessageCollection collection = send.getEntry();
    String id = collection.getId();
    
    Message message = new Message();
    message.setTitle("dummytitle");
    message.setBody("body");

    message.setField("senderId", "sentByPerson");

    message.setRecipient("sentToPerson");

    message.setMessageCollectionId(id);

    message.setField("appUrl", "sentByServiceProvider");

    request = MessagesService.createMessage(message);
    Response send2 = client.send(request);
    Model entry = send2.getEntry();
  }

  private Client getShindigClient() {
    Provider provider = new ShindigProvider();
    String oauthKey = "does-not-matter";
    String oauthSecret = "mysecret";
    String restEndpoint = "http://localhost:8080/social/rest/";
    provider.setRestEndpoint(restEndpoint);
    provider.setRpcEndpoint(null);

    AuthScheme scheme = new OAuth2LeggedScheme(oauthKey, oauthSecret,
        "john.doe");

    Client client = new Client(provider, scheme);
    return client;
  }

}
