package com.scriptrix.engine.project;

public class CreateProjectException extends Exception {
  public CreateProjectException(String s) {
    super(s);
  }
  public CreateProjectException(Throwable e) {
    super(e);
  }
  public CreateProjectException(String s, Throwable e) {
    super(s, e);
  }
}
