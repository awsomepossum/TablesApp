package com.msushanth.tablesapp.BusinessLogicLayer.DispatchClasses;

import com.msushanth.tablesapp.BusinessLogicLayer.ControllerClasses.SearchController;
import com.msushanth.tablesapp.Interfaces.Search.SearchInterface;

/**
 * Created by Sushanth on 11/10/17.
 * Implemented by YinlongQian on 11/19/17
 */

public class SearchDispatch implements SearchInterface{
    private String[] idList;
    private String[] lastNameList;
    private String[] firstNameList;
    private String[][] tagMatrix;

    public SearchDispatch(int inputNum){
        this.idList = new String[inputNum];
        this.lastNameList = new String[inputNum];
        this.firstNameList = new String[inputNum];
        this.tagMatrix = new String[inputNum][5];
    }

    @Override
    public void searchEngine(int num){

    }

    @Override
    public String[] getIdList(){
        return this.idList;
    }

    @Override
    public String[] getLastNameList(){
        return this.lastNameList;
    }

    @Override
    public String[] getFirstNameList(){
        return this.firstNameList;
    }

    @Override
    public String[][] getTagMatrix(){
        return this.tagMatrix;
    }
}
