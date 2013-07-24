package com.beta.dataface;

/**
 * Created by trentonknight on 7/24/13.
 */
public class LittleConstructor {

    int _id;
    String _objectName;
    String _content;

    public LittleConstructor(){

    }
    //constructor
    public LittleConstructor(int id, String objectName, String content){
        this._id = id;
        this._objectName = objectName;
        this._content = content;
    }
    //constructor
    public LittleConstructor(String objectName, String content){
        this._objectName = objectName;
        this._content = content;
    }
    //get id
    public int getID(){
        return this._id;
    }
    //set id
    public void setID(int id){
        this._id = id;
    }
    //get object name
    public String getOB(){
        return this._objectName;
    }
    //set object name
    public void setOB(String objectName){
        this._objectName = objectName;
    }
    //get content
    public String getContent(){
        return this._content;
    }
    //set content
    public void setContent(String content){
        this._content = content;
    }

}
