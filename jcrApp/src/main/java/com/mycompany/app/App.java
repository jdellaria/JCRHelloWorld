package com.mycompany.app;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.Node;
import javax.jcr.SimpleCredentials;
import org.apache.jackrabbit.commons.JcrUtils;

import java.util.*;

import javax.jcr.*;
import java.io.FileInputStream;

import javax.jcr.query.*;
//import org.apache.jackrabbit.oak.jcr.Jcr;
//import org.apache.jackrabbit.oak.jcr.RepositoryFactoryImpl;
//import org.apache.jackrabbit.rmi.repository.URLRemoteRepository;
/**
 * Hello world!
 *
 */
public class App
{


    public static void main(String[] args)  throws Exception { //DeleteNode
        // Jackrabbit repository URL
        // Repository URL for the Jackrabbit Oak server
        String repositoryURL = "http://localhost:8080/server"; // Replace with your server URL
        //String repositoryURL = "http://localhost:4502/crx/server"; // Adjust this to match your Jackrabbit server URL


        // Set up connection parameters
//        Repository repository = new getRepository().getRepository(repositoryUrl);
        javax.jcr.Repository repository = null;

//        Repository repository = "http://localhost:8080/server"; /* obtain repository instance */;
        repository = JcrUtils.getRepository(repositoryURL);
        // Establish a session with the server
        try {
            // Provide credentials if needed (username and password)
            String username = "admin";
            String password = "admin";
            SimpleCredentials credentials = new SimpleCredentials(username, password.toCharArray());

            Session session = repository.login(credentials);
            Node rootNode = session.getRootNode();

            // Example: Output the root node path
            System.out.println("Root node path: " + rootNode.getPath());
//            rootNode.setProperty("message", "Hello, Jackrabbit!"); // Setting properties


//            String nodePath = "/.DS_Store"; ///._.DS_Store
//            String nodePath = "/content/dam/music/images/1984.jpg/jcr:content/metadata"; //
            String nodePath = "/hello/jon.txt"; //

            // Check if the node exists before attempting to delete
            if (session.nodeExists(nodePath)) {
              System.out.println("Node exists");
                // Get the node and its parent
                Node node = session.getNode(nodePath);
                Node parentNode = node.getParent();

//                node.setProperty("project", "HelloThere"); // Setting properties
                // Delete the node
//                node.remove();

                // Save the changes
                session.save();
                dump(node);
                System.out.println("Node deleted successfully.");
            } else {
                System.out.println("Node does not exist.");
            }
//            nodeHello.remove();
//            Node world = nodeHello.addNode("world");

            session.save();
            session.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
      }

//static Repository repository;
//public class JackrabbitSearchExample {
    public static void mainXPATH(String[] args) throws Exception {  //XPATH Query Node
        // Set up the JCR session
        String repositoryURL = "http://localhost:8080/server"; // Adjust this to match your Jackrabbit server URL
//          String repositoryURL = "http://localhost:4502/crx/server"; // Adjust this to match your Jackrabbit server URL
        javax.jcr.Repository repository = null;

//        Repository repository = "http://localhost:8080/server"; /* obtain repository instance */;
        repository = JcrUtils.getRepository(repositoryURL);

                  // Jackrabbit login credentials
        String username = "admin";
        String password = "admin";

        Session session = null;
        try {


          // Create a repository connection
//          Repository repository = JcrUtils.getRepository(repositoryURL);
          session = repository.login(new SimpleCredentials(username, password.toCharArray()));

          // Specify the XPath query
//          String xpathQuery = "/jcr:root/content//*[jcr:contains(., 'hello')]";
//         String xpathQuery = "/jcr:root//*[jcr:contains(., '*')]";
         String xpathQuery = "/jcr:root//*[jcr:contains(.,'Hello, Jackrabbit!')]"; // Specific property contains
//          String xpathQuery = "//*[jcr:contains(., 'hello/')]";


          // Obtain the QueryManager
          QueryManager queryManager = session.getWorkspace().getQueryManager();

          // Create and execute the query
          Query query = queryManager.createQuery(xpathQuery, Query.XPATH);

/*
          QueryResult result = query.execute();

          // Iterate through the result nodes
          NodeIterator nodeIterator = result.getNodes();
          while (nodeIterator.hasNext()) {
              Node node = nodeIterator.nextNode();
              // Do something with the matched nodes
              System.out.println("Matched Node: " + node.getPath());
          }




           session = repository.login(new SimpleCredentials(username, password.toCharArray()));
            // Create a query manager
            QueryManager queryManager = session.getWorkspace().getQueryManager();

            // Define a query statement (XPath, SQL, or JCR-SQL2)
            // String queryString = "*" ;
            //String queryString = elementNode[@propertyName = 'jcr:primaryType']";
//            String queryString = "/jcr:system/jcr:nodeTypes";
            String queryString = "SELECT * FROM [nt:base] WHERE ISDESCENDANTNODE('/hello')";
//            String queryString = "SELECT * FROM [nt:base]";

           System.out.println("Creating Query");
//            javax.jcr.query.Query query = queryManager.createQuery(queryString, Query.XPATH);

            javax.jcr.query.Query query = queryManager.createQuery(queryString, javax.jcr.query.Query.JCR_SQL2);

            // Example JCR-SQL2 query
*/


            // Execute the query
            System.out.println("Executing Query");

            QueryResult result = query.execute();

            // Process the query result
            NodeIterator nodeIterator = result.getNodes();
            while (nodeIterator.hasNext()) {
                Node node = nodeIterator.nextNode();
                // Process the matched nodes
                System.out.println("Node found: " + node.getPath());
            }
            System.out.println("Application Exit");

        } catch (RepositoryException e) {
            e.printStackTrace();
        }finally {
          if (session != null) {
              session.logout();
          }
      }

    }

//public class JackrabbitSearchExample {
    public static void mainJCR_SQL2(String[] args) throws Exception {  //JCR_SQL2 Query Node
        // Set up the JCR session
        String repositoryURL = "http://localhost:8080/server"; // Adjust this to match your Jackrabbit server URL
//          String repositoryURL = "http://localhost:4502/crx/server"; // Adjust this to match your Jackrabbit server URL
        javax.jcr.Repository repository = null;

//        Repository repository = "http://localhost:8080/server"; /* obtain repository instance */;
        repository = JcrUtils.getRepository(repositoryURL);

                  // Jackrabbit login credentials
        String username = "admin";
        String password = "admin";

        Session session = null;
        try {

           session = repository.login(new SimpleCredentials(username, password.toCharArray()));
            // Create a query manager
            QueryManager queryManager = session.getWorkspace().getQueryManager();

            // Define a query statement (XPath, SQL, or JCR-SQL2)
            // String queryString = "*" ;
            //String queryString = elementNode[@propertyName = 'jcr:primaryType']";
//            String queryString = "/jcr:system/jcr:nodeTypes";
            String queryString = "SELECT * FROM [nt:base] WHERE ISDESCENDANTNODE('/hello')";
//            String queryString = "SELECT * FROM [nt:base]";

           System.out.println("Creating Query");
//            javax.jcr.query.Query query = queryManager.createQuery(queryString, Query.XPATH);

            javax.jcr.query.Query query = queryManager.createQuery(queryString, javax.jcr.query.Query.JCR_SQL2);

            // Example JCR-SQL2 query



            // Execute the query
            System.out.println("Executing Query");

            QueryResult result = query.execute();

            // Process the query result
            NodeIterator nodeIterator = result.getNodes();
            while (nodeIterator.hasNext()) {
                Node node = nodeIterator.nextNode();
                // Process the matched nodes
                System.out.println("Node found: " + node.getPath());
            }
            System.out.println("Application Exit");

        } catch (RepositoryException e) {
            e.printStackTrace();
        }finally {
          if (session != null) {
              session.logout();
          }
      }

    }
//}


      public static void mainOrig(String[] args)  throws Exception {
          // Jackrabbit repository URL
          String repositoryURL = "http://localhost:8080/server"; // Adjust this to match your Jackrabbit server URL
//          String repositoryURL = "http://localhost:4502/crx/server"; // Adjust this to match your Jackrabbit server URL
          javax.jcr.Repository repository = null;

          // Jackrabbit login credentials
          String username = "admin";
          String password = "admin";

          // Initialize the repository

          Map<String, String> map = new HashMap<String, String>();

  //        map.put("1", "http://localhost:8080/");




          System.out.println("getting repository");
          System.out.println(repositoryURL);

          repository = JcrUtils.getRepository(repositoryURL);

          // Establish a session
          Session session = null;
          try {
            System.out.println("logining in");

              session = repository.login(new SimpleCredentials(username, password.toCharArray()));

              // Perform operations - For example, creating a node
                System.out.println("get root node");
              Node rootNode = session.getRootNode();
//              Node helloNode = rootNode.addNode("hello");
//              helloNode.setProperty("message", "Hello, Jackrabbit!");
//              session.save();
//              System.out.println("Node 'hello' created successfully.");
/*              Node nodeHello = rootNode.getNode("hello");
              Node world = nodeHello.addNode("world");
              session.save();
              System.out.println("Node 'hello' informarion: ");
              System.out.println(nodeHello.getPath());
              System.out.println("Node 'world' informarion: ");
              System.out.println(world.getPath());


              String user = session.getUserID();
              String name = repository.getDescriptor(Repository.REP_NAME_DESC);
              System.out.println( "Logged in as " + user + " to a " + name + " repository.");

              dump(rootNode);*/

              dumpNodeNames(rootNode);

          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              if (session != null) {
                  session.logout();
              }
          }
      }
      /** Recursively outputs the contents of the given node. */
private static void dumpNodeNames(Node node) throws RepositoryException {
    // First output the node path
    System.out.println(node.getName());
    dump(node);
    NodeIterator nodes = node.getNodes();
    while (nodes.hasNext()) {
        dumpNodeNames(nodes.nextNode());
    }


  }


        /** Recursively outputs the contents of the given node. */
  private static void dump(Node node) throws RepositoryException {
      // First output the node path
      String str= "This#string%contains^special*characters&.";
  str = str.replaceAll("[^a-zA-Z0-9]", " ");
      System.out.println("Path: " + node.getPath());

      // Skip the virtual (and large!) jcr:system subtree
      if (node.getName().equals("jcr:system")) {
         return;
      }

      // Then output the properties
      PropertyIterator properties = node.getProperties();
      System.out.println("Getting Properties");
      while (properties.hasNext()) {
        System.out.println("Properties");
          Property property = properties.nextProperty();
          if (property.getDefinition().isMultiple()) {
              // A multi-valued property, print all values
              Value[] values = property.getValues();
              for (int i = 0; i < values.length; i++) {
                str= values[i] .getString();
                str = str.replaceAll("[^\\x20-\\x7E]", "");
                  System.out.println(
                  property.getPath() + " = " + str);
              }
          } else {
              // A single-valued property
              str = property.getString().replaceAll("[^\\x20-\\x7E]", "");
              System.out.println(property.getPath() + " = " + str);
          }
      }

      // Finally output all the child nodes recursively
      NodeIterator nodes = node.getNodes();
      while (nodes.hasNext()) {
          dump(nodes.nextNode());
      }
  }


      /** Recursively outputs the contents of the given node. */
private static void dumpx(Node node) throws RepositoryException {
    // First output the node path
    System.out.println(node.getPath());

    // Skip the virtual (and large!) jcr:system subtree
    if (node.getName().equals("jcr:system")) {
        return;
    }

    // Then output the properties
    PropertyIterator properties = node.getProperties();
    while (properties.hasNext()) {
        Property property = properties.nextProperty();
        if (property.getDefinition().isMultiple()) {
            // A multi-valued property, print all values
            Value[] values = property.getValues();
            for (int i = 0; i < values.length; i++) {
                System.out.println(
                property.getPath() + " = " + values[i] .getString());
            }
        } else {
            // A single-valued property
            System.out.println(
            property.getPath() + " = " + property.getString());
        }
    }

    // Finally output all the child nodes recursively
    NodeIterator nodes = node.getNodes();
    while (nodes.hasNext()) {
        dump(nodes.nextNode());
    }
}

}
