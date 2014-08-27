package controllers;

import java.util.List;

import play.*;
import play.api.libs.json.JsResult;
import play.mvc.*;
import views.html.*;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Parent;
import play.db.ebean.Model.Finder;

import play.db.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready ok?."));
    }

    /*
		login page
    */
	public static Result login(String username,String password) {
		
		Parent parent1 = new Parent();
        parent1.name = username;
        parent1.save();
		
     // 2番目を保存
        Parent parent2 = new Parent();
        parent2.name = "代々木太郎";
        parent2.save();
        
     // 現在のParentを全検索して表示
        Finder<Long, Parent> finder = new Finder<Long, Parent>(Long.class, Parent.class);
        List<Parent> parents = finder.all();
 
        StringBuilder msg = new StringBuilder();
        for (Parent parent : parents) {
            msg.append(parent.toString()).append("\n");
        }
        
        System.out.println(msg.toString());
        
		String title = "login page";
		return ok(login.render("login page" + username));
	}
	
	/*
	 * get user info
	 */
	public static Result getUserInfo() {
		ObjectNode result = Json.newObject();
		  result.put("username", "liusongran");
		  System.out.println(result.get("username"));
		  return ok(Json.toJson(result));
	}

}
