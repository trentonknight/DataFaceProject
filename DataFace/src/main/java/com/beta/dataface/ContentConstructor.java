package com.beta.dataface;

/**
 * Created by trentonknight on 8/11/13.
 */
public class ContentConstructor {

int _id;
    String _parent;
    String _cubename;
    String _cube;

    public ContentConstructor(){

    }
    public ContentConstructor(int id, String parent, String cubename, String cube){
        this._id = id;
        this._parent = parent;
        this._cubename = cubename;
        this._cube = cube;
    }
    public ContentConstructor(String parent, String cubename, String cube){
        this._parent = parent;
        this._cubename = cubename;
        this._cube = cube;
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }
    public String getParent(){
        return this._parent;
    }
    public void setParent(String parent){
        this._parent = parent;
    }
    public String getCubeName(){
        return this._cubename;
    }
    public void setCubeName(String cubename){
        this._cubename = cubename;
    }
    public String getCube(){
        return this._cube;
    }
    public void setCube(String cube){
        this._cube = cube;
    }


}
