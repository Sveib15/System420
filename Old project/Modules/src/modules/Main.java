/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Adne
 */
public class Main extends Application 
{    
    private Stage mainScreen;
    private Connection connection;
    private User user;
    private boolean isUser;
    private boolean isModerator;
    private boolean isAdmin;
    
    @Override
    public void start(Stage primaryStage) 
    {
        // Creates a User object, used to store information about the logged in user
        user = new User();
        
        // Connects to database
        connection = connectToDatabase();
        
        // Shows login screen
        showLoginStage();
        
        mainScreen = primaryStage;  
    }
    
    public ScrollPane getModulOverview()
    {
        // Creates scrollpane and a HBox used for storing one VBox for each column in the approvals database
        ScrollPane overview = new ScrollPane();
        HBox h = new HBox();
        h.setAlignment(Pos.TOP_LEFT);
        overview.setContent(h);
        
        try
        {
            // Sends statement to database, selects all
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select*from approvals");
            // Stores metadata
            ResultSetMetaData rsmd = rs.getMetaData();
            // stores number of columns
            int numbOfColumns = rsmd.getColumnCount();
            
            // loops thru columns
            for(int i = 1; i <= numbOfColumns; i++)
            {
                // Creates a VBox for each column
                VBox v = new VBox();
                v.setAlignment(Pos.TOP_LEFT);
                // Gets column name
                Label colName = new Label(rsmd.getColumnName(i));
                colName.setPadding(new Insets(10, 10, 10, 10));
                
                // If column name equals "email", write "navn"
                if(rsmd.getColumnName(i).equals("email"))
                {
                    colName.setText("Navn");
                }
                // Styles and adds to vbox
                colName.setStyle("-fx-text-fill:black; -fx-font-size:17px; -fx-font-weight:bold;");
                v.getChildren().add(colName);
                
                // Row counter
                int rowNumb = 0;
                
                // loops thru rows
                while(rs.next())
                {
                    // Reads object from the current row at column i
                    Label txt = new Label((String)rs.getObject(i));
                    txt.setPadding(new Insets(10,10,10,10));
                    
                    // If the coulumn is email, we find the first and lastname of the user from the user database
                    if(rsmd.getColumnName(i).equals("email"))
                    {
                        //Sends statement to user database, selects firstname and lastname based on email(primary key)
                        Statement state2 = connection.createStatement();
                        ResultSet rs2 = state2.executeQuery("select firstname, lastname from users where email= '" + rs.getObject("email") + "';");
                        while(rs2.next())
                        {
                            //Sets text
                            txt.setText((String)rs2.getObject("firstname") + " " + rs2.getObject("lastname"));
                        }
                    }
                    
                    // Checks if row count is odd or even, decides color of the text
                    if(rowNumb%2==0)
                    {
                        txt.setStyle("-fx-text-fill:#e6e6e6; -fx-font-size:15px; -fx-font-weight:bold;");
                    }
                    else
                    {
                        txt.setStyle("-fx-text-fill:#262626; -fx-font-size:15px; -fx-font-weight:bold;");
                    }
                    
                    // Adds the text to the vbox
                    v.getChildren().add(txt);
                    rowNumb++;
                }
                
                // Adds the vbox to the hbox
                h.getChildren().add(v);
            }
        }
        catch(SQLException e)
        {
            
        }
        
        // Returns the scrollpane
        return overview;    
    }
    
    //Creates the main scene and returns it
    public Scene getMainScene()
    {
        // Button for creating a modul
        Button btn = new Button("Opprett modul");
        btn.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                createModule();
            }
        });
        
        // Button for showing users profile
        Button btn1 = new Button("Min profil");
        btn1.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        btn1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                user.showProfile();
            }
        });
        
        // Buttons without functions
        Button btn2 = new Button("Vurder modul");
        btn2.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        
        Button btn3 = new Button("Rediger modul");
        btn3.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        
        Button btn4 = new Button("Lever modul");
        btn4.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        
        TextField search = new TextField();
        search.setPromptText("Søk");
        
        // Horizontal box: students choices
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(7, 7, 7, 7));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(btn1, btn4, search);
        hbox.setStyle("-fx-background-color:#404040;");
        
        // Vertical box: holds admins choices
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(btn, btn2, btn3);
        vbox.setStyle("-fx-background-color:#404040;");
        
        // Creates gridpane and sets style
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color:#666666;");
        
        // If the user is a admin, adds the horizontal box to the borderpane
        if(isAdmin)
        {
            bp.setRight(vbox);
        }
        
        // If the user is a student, adds the vertical box to the borderpane
        if(isUser)
        {
            bp.setTop(hbox);
        }
        
        // Sets the center of the borderpane to the moduloverview scrollpane and requests focus
        bp.setCenter(getModulOverview());
        bp.requestFocus();
        // Creates a scene
        Scene root = new Scene(bp, 900,500);
        // Links stylesheet to scene
        root.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        return root;
    }
    
    public void showLoginStage()
    {
        Stage login = new Stage();
        
        // Creates gridpane, sets alignment, style and gaps
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setStyle("-fx-background-color:#404040;");
        gp.setHgap(10);
        gp.setVgap(10);
        
        // Label for showing error message when password or email is incorrect
        Label msg = new Label();
        msg.setStyle("-fx-text-fill:red; -fx-text-weight:bold; -fx-font-size:15px;");
        
        // Text Fields for user intput: email and password
        TextField emailfield = new TextField();
        emailfield.setPromptText("E-post");
        PasswordField password = new PasswordField();
        password.setPromptText("Passord");
        
        // Login button
        Button loginbtn = new Button("Logg inn");
        loginbtn.setStyle("-fx-text-fill:white; -fx-font-weight:bold; -fx-background-color:black;");
        loginbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                try
                {
                    // Selects all passwords where the email vaule equal to the users input in the email field
                    PreparedStatement ps = connection.prepareStatement("select password from users where email=?;");
                    ps.setString(1, emailfield.getText());
                    ResultSet rs = ps.executeQuery();
                    boolean correct = false;
                    String firstname;
                    String lastname;
                    String email;
                    String rights;
                    
                    // Loops thru the result of the query executed above, AKA the passwords
                    while(rs.next())
                    {
                        String output = (String) rs.getObject("password");
                        
                        //If the ouput(password) equals the user input in the password field
                        if(output.equals(password.getText()))
                        {
                            correct = true;
                            email = emailfield.getText();
                            user.setEmail(emailfield.getText());
                            
                            // Selects rights based on the users email
                            ps = connection.prepareStatement("select rights from users where email=?");
                            ps.setString(1, email);
                            rs = ps.executeQuery();
                            while(rs.next())
                            {
                                rights = (String) rs.getObject("rights");
                                user.setRights(rights);
                                
                                // Sets the users rights
                                if(rights.equals("admin"))
                                {
                                    isAdmin = true;
                                }
                                else if(rights.equals("moderator"))
                                {
                                    isModerator = true;
                                }
                                else
                                {
                                    isUser = true;
                                }
                            }                     
                            
                            correct = true;
                            
                            // Selects firstname based on users email
                            ps = connection.prepareStatement("select firstname from users where email=?");
                            ps.setString(1, email);
                            rs = ps.executeQuery();                      
                            while(rs.next())
                            {
                                firstname = (String) rs.getObject("firstname");
                                user.setFirstName(firstname);
                            }
                            
                            // Selects lastname base on users email
                            ps = connection.prepareStatement("select lastname from users where email=?");
                            ps.setString(1, email);
                            rs = ps.executeQuery();
                            while(rs.next())
                            {
                                lastname = (String) rs.getObject("lastname");
                                user.setLastname(lastname);
                            }                           
                        }
                    }
                    
                    // If email and password matches
                    if(correct)
                    {
                        // Shows mainscreen and hides loginscren
                        mainScreen.setScene(getMainScene());
                        mainScreen.show();
                        login.hide(); 
                    }
                    else
                    {
                        // Shows error message and clears inputfields
                        msg.setText("Feil E-post eller passord");
                        emailfield.setText("");
                        password.setText("");
                        gp.requestFocus();
                    }
                }
                catch(SQLException ex)
                {
                    
                }
            }
        });
        
        // Adds components to gridpane
        gp.add(msg, 0, 0);
        gp.add(emailfield, 0, 1);
        gp.add(password, 0, 2);
        gp.add(loginbtn, 0, 3);
        
        // Creates scene, adds to stage and shows it
        Scene scene = new Scene(gp, 300, 250);
        login.setScene(scene);
        login.show();
        gp.requestFocus();
    }
    
    public Connection connectToDatabase()
    {
        Connection connection = null;  
        try 
        {  
            // mysql database driver
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);  
            
            // database url, username and password
            String url = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false";
            String username = "root";  
            String password = "root";  
            
            // creates connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) 
        {  
            e.printStackTrace();
        } catch (SQLException e) 
        {  
            e.printStackTrace();
        }
        
        return connection;
    }

    public void createModule()
    {
        // Creates stage and pane
        Stage addModuleStage = new Stage();
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setStyle("-fx-background-color:#404040;");
        
        //Checkboxes
        CheckBox teachercb = new CheckBox("Foreleser");
        teachercb.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        teachercb.setSelected(true);
        CheckBox helpercb = new CheckBox("Hjelpelærer");
        helpercb.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        CheckBox studentcb = new CheckBox("Student");
        studentcb.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        
        //Labels
        Label tf1l = new Label("Navn: ");
        tf1l.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        Label tf2l = new Label("Beskrivelse: ");
        tf2l.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        Label tf3l = new Label("Læremål: ");
        tf3l.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        Label tf4l = new Label("Ressurser: ");
        tf4l.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        Label cbl = new Label("Kan vurderes av:");
        cbl.setStyle("-fx-text-fill:white; -fx-font-size:14px; -fx-font-weight:bold;");
        
        //Text inputs
        TextField tf1 = new TextField();
        TextArea tf2 = new TextArea();
        tf2.setPrefHeight(100);
        tf2.setPrefWidth(400);
        tf2.setWrapText(true);
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        tf1.setPrefWidth(400);
        tf2.setPrefWidth(400);
        tf3.setPrefWidth(400);
        tf4.setPrefWidth(400);
        tf3.setPromptText("Legg til læremål her");
        tf4.setPromptText("Legg til ressurs her");
        
        //Scrollpane for requirements(læremål)
        ScrollPane scroll1 = new ScrollPane();
        VBox vbox = new VBox();
        scroll1.setMaxWidth(400);
        scroll1.setMinWidth(400);
        scroll1.setMaxHeight(100);
        scroll1.setMinHeight(100);
        vbox.setAlignment(Pos.BASELINE_LEFT);
        scroll1.setContent(vbox);
        
        //Scrollpane for resources
        ScrollPane scroll2 = new ScrollPane();
        VBox vbox2 = new VBox();
        scroll2.setMaxWidth(400);
        scroll2.setMinWidth(400);
        scroll2.setMaxHeight(100);
        scroll2.setMinHeight(100);
        vbox2.setAlignment(Pos.BASELINE_LEFT);
        scroll2.setContent(vbox2);
        
        // Lists for requirements and resources
        ArrayList<String> requirements = new ArrayList<String>();
        ArrayList<String> resources = new ArrayList<String>();
        
        // Button for adding requirement
        Button btn1 = new Button("+");
        btn1.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        btn1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                // Add text from textfield3 to requirements list 
                requirements.add(tf3.getText());
                
                // Creates label
                Label r = new Label("- " + tf3.getText());
                r.setStyle("-fx-font-size:13px; -fx-font-weight:bold;");
                
                // Adds label to vbox
                vbox.getChildren().add(r);
                
                // Scrolls all the way down
                scroll1.setVvalue(1.0);
                
                // Clears textfield and requests focus
                tf3.setText("");
                tf3.requestFocus();
            }
        });
        
        // Button for adding resource
        Button btn2 = new Button("+");
        btn2.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        btn2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                // Add text from textfield4 to resource list
                resources.add(tf4.getText());
                
                // Creates and styles label
                Label r = new Label("- " + tf4.getText());
                r.setStyle("-fx-font-size:13px; -fx-font-weight:bold;");
                
                // Adds label to vbox
                vbox2.getChildren().add(r);
                
                // Scrolls all the way down
                scroll2.setVvalue(1.0);
                
                // Clears textfield and requests focus
                tf4.setText("");
                tf4.requestFocus();
            }
        });
        
        // Button for creating the modul
        Button btn = new Button("Opprett");
        btn.setStyle("-fx-background-color:black; -fx-text-fill:white; -fx-font-weight:bold;");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
                @Override
                public void handle(ActionEvent e)
                {                     
                    try
                    {  
                        // Creats statement
                        Statement state = connection.createStatement();
                        
                        // Gets data from the textfields
                        String name = tf1.getText();
                        String desc = tf2.getText();
                        
                        // Adds all the strings in the resources list to one long string
                        String res = "";
                        for(String s: resources)
                        {
                            res += (s + "%");
                        }
                        
                        // Adds all the strings in the requirements(læremål) list to one long string
                        String req = "";
                        for(String s: requirements)
                        {
                            req += (s + "%");
                        }
                        
                        // Decides rights by what checkboxes are selected
                        int rights = 0;
                        if(teachercb.isSelected() == true)
                        {
                            rights = 3;
                        }
                        if(helpercb.isSelected() == true)
                        {
                            rights = 2;
                        }
                        if(studentcb.isSelected() == true)
                        {
                            rights = 1;
                        }
                                             
                        // Inserts the data into modul database
                        state.executeUpdate("INSERT INTO modul(id, description, requirements, resources, rights) " +  "VALUES('" + name + "', '"+ desc + "', '" + req + "', '" + res + "', '" + rights + "');");
                        
                        // Adds a column for the new modul in the approvals database
                        state.executeUpdate("ALTER TABLE approvals ADD "+name+" varchar(100);");
                        
                        // Hides the stage
                        addModuleStage.hide();
                        
                        // Refreshes mainscreen by building the scene again
                        mainScreen.setScene(getMainScene());
                        
                    }
                    catch(SQLException s)
                    {
                        s.printStackTrace();
                    }
                }
        });
        
        //Adds textfields and their labels
        gp.add(tf1, 1,0);
        gp.add(tf1l, 0, 0);
        gp.add(tf2, 1,1);
        gp.add(tf2l, 0, 1);
        gp.add(scroll1, 1, 2);
        gp.add(tf3, 1, 3);
        gp.add(tf3l, 0, 2);
        gp.add(tf4l, 0, 4);
        gp.add(scroll2, 1, 4);
        gp.add(tf4, 1, 5);
        
        //Adds buttons
        gp.add(btn, 2, 9);
        gp.add(btn1, 2, 3);
        gp.add(btn2, 2, 5);
        //Adds label for checkboxes
        gp.add(cbl, 0, 6);
        //Adds checkboxes
        gp.add(teachercb, 0, 7);
        gp.add(helpercb, 0, 8);
        gp.add(studentcb, 0, 9);
        
        //Creats scene, adds to stage and shows the stage
        Scene scene = new Scene(gp, 650, 600);
        addModuleStage.setScene(scene);
        addModuleStage.setTitle("Opprett modul");
        addModuleStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {   
        launch(args);
    }   
}
