package com.example.john.assignfour2017johnbukley;

/**
 * Created by John on 03/05/2017.
 */
public class Pizza
{
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private int mPizzaNameId;
    private int mPizzaDescId;

    /**
     * Constant value that represents no image was provided for this word
     */
    private static final int NO_IMAGE_PROVIDED = -1;


    public Pizza(int imageResourceId, int pizzaNameId, int pizzaDescId)
    {

        mImageResourceId = imageResourceId;
        mPizzaNameId = pizzaNameId;
        mPizzaDescId = pizzaDescId;

    }

    /**
     * Return the image resource ID of the pizza picture
     */
    public int getImageResourceId()
    {
        return mImageResourceId;
    }

    public boolean hasImage()
    {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


    public int getPizzaNameId()
    {
        return mPizzaNameId;
    }

    public int getPizzaDescId()
    {
        return mPizzaDescId;
    }
}
