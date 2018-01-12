/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.utils.DatabaseConnection;
import lapr.project.utils.SQLConnection;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class DataBaseConnectionController {

    public SQLConnection connectToDatabase(String username, String password, String url) {
        return new DatabaseConnection().loginDatabase(username, password, url);
    }

}
