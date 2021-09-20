package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


public class TweetsResource extends ServerResource {
	
	   /** Backend.*/
    private Backend backend_;

    /** Utilisateur géré par cette resource.*/
    private ArrayList <Tweet> tweets_;
    
    private User user_;
    


    /**
     * Constructor.
     * Call for every single user request.
     */
    public TweetsResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }


    /**
    *
    * Returns the list of all the tweets from a specific user
    *
    * @return  JSON representation of the tweets
    * @throws JSONException
    */
   @Get("json")
   public Representation getTweets() throws JSONException
   {
       String userIdString = (String) getRequest().getAttributes().get("userId");
       int userId = Integer.valueOf(userIdString);
       user_ = backend_.getDatabase().getUser(userId);
       
       ArrayList<Tweet> tweets = user_.getTweets();
       Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

       if (tweets != null) {
           for (Tweet tweet : tweets)       
           {
               JSONObject current = new JSONObject();
               current.put("tweet", tweet.getTweetCont());
               jsonTweets.add(current);

           }
           JSONArray jsonArray = new JSONArray(jsonTweets);
           return new JsonRepresentation(jsonArray);
       }
        JSONObject tweetObject = new JSONObject();
        return new JsonRepresentation(tweetObject);
    

   }
   
   @Post("json")
   public Representation createTweet(JsonRepresentation representation)
       throws Exception
   {
	   
       String userIdString = (String) getRequest().getAttributes().get("userId");
       int userId = Integer.valueOf(userIdString);
       user_ = backend_.getDatabase().getUser(userId);
              
	   
       JSONObject object = representation.getJsonObject();
       String tweet = object.getString("tweet");
       
       Tweet t_ = new Tweet(tweet);
       
       user_.getTweets().add(t_);
        
       // Save the user
      // User user = backend_.getDatabase().createUser(name, age);

       // generate result
       JSONObject resultObject = new JSONObject();
       resultObject.put("name", user_.getName());
       resultObject.put("age", user_.getAge());
       resultObject.put("id", user_.getId());
       resultObject.put("tweets", user_.getTweets());

       JsonRepresentation result = new JsonRepresentation(resultObject);
       return result;
   }


}

