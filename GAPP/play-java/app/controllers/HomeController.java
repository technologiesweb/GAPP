package controllers;

import javax.inject.*;
import play.*;
import play.mvc.*;

import services.Counter;

public class HomeController extends Controller {
  
public static Result index() {
  return redirect(routes.Application.tasks());
}
  
  public static Result tasks() {
    return TODO;
  }
  
  public static Result newTask() {
    return TODO;
  }
  
  public static Result deleteTask(Long id) {
    return TODO;
  }
  
}