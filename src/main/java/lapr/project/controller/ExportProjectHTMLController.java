/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.utils.Session;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class ExportProjectHTMLController {
    
    private Project p;

    public ExportProjectHTMLController() {
        this.p = Session.getActiveProject();
    }
    
    
    
}
