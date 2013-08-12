package com.beta.dataface;

/**
 * Created by trentonknight on 7/24/13.
 */
public class ConstructorOne {

    int _id;
    String _objectName;
    String _content;

    public ConstructorOne(){

    }
    //constructor
    public ConstructorOne(int id, String objectName, String content){
        this._id = id;
        this._objectName = objectName;
        this._content = content;
    }
    //constructor
    public ConstructorOne(String objectName, String content){
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
    //get get_uti_two_menu
    public String getContent(){
        return this._content;
    }
    //set get_uti_two_menu
    public void setContent(String content){
        this._content = content;
    }

}
