package org.artofsolving.jodconverter.sample.web;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class WebappContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    WebappContext.init(event.getServletContext());
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    WebappContext.destroy(event.getServletContext());
  }

}
