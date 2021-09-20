package org.inria.restlet.mta.internals;

public class Tweet {
	
    /** Internal id of the tweet.*/
  //  private int id_;

    /** Tweet content.*/
    private String tweetCont_;

    /** user id.*/
  //  private int userId_;



public Tweet(String tweetCont) 
	{
	tweetCont_ = tweetCont;
	}

public String getTweetCont()
{
    return tweetCont_;
}

public void setTweetCont(String tweetCont)
{
	tweetCont_ = tweetCont;
}

}