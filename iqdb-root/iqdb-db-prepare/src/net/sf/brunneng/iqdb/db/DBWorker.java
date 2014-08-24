/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sf.brunneng.iqdb.db;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.dbmaintain.MainFactory;

/**
 *
 * @author goodg_000
 */
public class DBWorker {      
   
   private static final String BR_LINE = System.getProperty("line.separator");
   
   private static String createActionsDescription() {
      StringBuilder sb = new StringBuilder();
      sb.append(DBAction.CLEAR).append(" - clear all database data and all db objects.").append(BR_LINE);
      sb.append(DBAction.UPDATE).append(" - apply all db maintain patches to database.").append(BR_LINE);
      sb.append(DBAction.RECREATE).append(String.format(" - the same as combination of %s and %s.", 
              DBAction.CLEAR, DBAction.UPDATE)).append(BR_LINE);
      
      return sb.toString();
   }
   
   private static void printUsageAndExit(Options options) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp(DBWorker.class.getName(), options);
      System.exit(-1);
   }
           
   public static void main(String[] args) {
      Options options = new Options();

      Option actionOpt = new Option("a", "action", true, "The action on db, which should be executed." + 
              BR_LINE + " Possible actions: " + BR_LINE + createActionsDescription());
      actionOpt.setRequired(true);
      options.addOption(actionOpt);      
      
      CommandLineParser parser = new GnuParser();
      try {
         CommandLine commandLine = parser.parse(options, args);
         
         String actionStr = commandLine.getOptionValue("action");
         DBAction action = null;
         try {
            action = DBAction.valueOf(actionStr);
         }
         catch (IllegalStateException exc) {
            System.out.println("Unknown action: " + actionStr);
            System.out.println("Possible actions: " + createActionsDescription());
            System.exit(-1);
         }
         
         DBWorker worker = new DBWorker(action, null, null, null);            
         worker.execute();
      }
      catch (ParseException exc) {
         System.out.println("Unable to parse command line arguments. Error: " + exc.getMessage());
         printUsageAndExit(options);
      }
   }
   
   private DBAction action;
   private MainFactory mainFactory;
   
   public DBWorker(DBAction action, URL pathToDefaultConfig, URL pathToConfig, URL pathToScripts) {
      this.action = action;
      
      if (pathToDefaultConfig == null) {
         pathToDefaultConfig = DBWorker.class.getClassLoader().getResource("dbmaintain-default.properties");
      }
      
      if (pathToConfig == null) {
         pathToConfig = DBWorker.class.getClassLoader().getResource("dbmaintain.properties");
      }
      
      if (pathToScripts == null) {
         pathToScripts = DBWorker.class.getClassLoader().getResource("scripts");
      }
      
      mainFactory = createMainFactory(pathToDefaultConfig, pathToConfig, pathToScripts);
   }
   
   private MainFactory createMainFactory(URL pathToDefaultConfig, URL pathToConfig, URL pathToScripts) {
      Properties dbConfigProperties = new Properties();
      try {
         dbConfigProperties.load(pathToDefaultConfig.openStream());
         dbConfigProperties.load(pathToConfig.openStream());
      }
      catch (IOException exc) {
         throw new RuntimeException("Unable to load dbmaintain properties file", exc);
      }
      
      String path = pathToScripts.toString();
      path = path.replace("file:", "");
      
      dbConfigProperties.put("dbMaintainer.script.locations", path);
      
      MainFactory res = new MainFactory(dbConfigProperties);      
      return res;
   }
   
   private void executeClear() {
      System.out.println(String.format("Execution of '%s' action started", DBAction.CLEAR));
            
      mainFactory.createDBClearer().clearDatabase();
      
      System.out.println(String.format("Execution of '%s' action successfully finished", DBAction.CLEAR));
   }
   
   private void executeUpdate() {      
      System.out.println(String.format("Execution of '%s' action started", DBAction.UPDATE));
      
      mainFactory.createDbMaintainer().updateDatabase(false);      
      
      System.out.println(String.format("Execution of '%s' action successfully finished", DBAction.UPDATE));
   }
   
   private void executeRecreate() {
      System.out.println(String.format("Execution of '%s' action started", DBAction.RECREATE));
                  
      executeClear();
      executeUpdate();
      
      System.out.println(String.format("Execution of '%s' action successfully finished", DBAction.RECREATE));
   }
   
   public void execute() {
      switch (action) {
         case CLEAR: {
            executeClear();
            break;
         }
         case UPDATE: {
            executeUpdate();
            break;
         }
         case RECREATE: {
            executeRecreate();
            break;
         }
      }
   }
}
