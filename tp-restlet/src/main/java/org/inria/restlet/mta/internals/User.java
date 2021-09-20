package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * User
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class User
{

    /** Internal id of the user.*/
    private int id_;

    /** Name of the user.*/
    private String name_;

    /** Age of the user.*/
    private int age_;

    /** To be added: collection of tweets of the user. */
    private ArrayList<Tweet> tweets_;  

    public User(String name, int age) //add collection<Tweet> tweets
    {
        name_ = name;
        age_ = age;
        tweets_ = new ArrayList<Tweet>();
    }

    public String getName()
    {
        return name_;
    }

    public void setName(String name)
    {
        name_ = name;
    }

    public int getAge()
    {
        return age_;
    }

    public void setAge(int age)
    {
        age_ = age;
    }

    public int getId()
    {
        return id_;
    }

    public void setId(int id)
    {
        id_ = id;
    }
    
    //MY CODE
    
	public ArrayList<Tweet> getTweets() 
	{
		return tweets_; 
	}
	 

}
