package com.example.pokemonproject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.sql.*;

public class PokemonController {


    @FXML
    private Label Evolutions;


    @FXML
    private VBox infoVbox;
    @FXML
    private Button DescriptionButton;

    @FXML
    private Label Height;

    @FXML
    private ImageView img;


    @FXML
    private Label Type;

    @FXML
    private Label Weight;

    @FXML
    private Button addToFavButton;

    @FXML
    private Label NameLabel;


    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    private Pokemon pokemon;

    public void setData(Pokemon pokemon)
    {
//        Image image = new Image(getClass().getResourceAsStream(pokemon.getImgSrc()));
//        img.setImage(image);


        String imgSrc = pokemon.getImgSrc();
        System.out.println("Attempting to load image from path: " + imgSrc);

        if (pokemon.getImgSrc() == null) {
            // Log or handle the error appropriately
            System.err.println("Image source is null for Pokemon: " + pokemon.getType());
            return; // Exit the method early to avoid NullPointerException
        }

        // Load image resource
        InputStream inputStream = getClass().getResourceAsStream(pokemon.getImgSrc());


        // Check if inputStream is null (resource not found)
        if (inputStream == null) {
            // Log or handle the error appropriately
            System.err.println("Image resource not found for Pokemon: " + pokemon.getType());
            return; // Exit the method early to avoid NullPointerException
        }

        // Create image
        Image image = new Image(inputStream);


     //   image = new Image(getClass().getClassLoader().getResource(pokemon.getImgSrc()).toString())


        img.setImage(image);
        img.setPreserveRatio(false);
        Type.setText(pokemon.getType());
        Height.setText(pokemon.getHeight());
        Weight.setText(pokemon.getWeight());
        //Description.setText(pokemon.getDescription());
        NameLabel.setText(pokemon.getName());
        //Evolutions.setText(pokemon.getEvolution());
        String e = "";
        for(String es : pokemon.getEvolutions())
        {
            e += es;
            e += "\n";
        }
        Evolutions.setText(e);


        if(pokemon.getisFavourite())
        {
            addToFavButton.setText("❤");
        }
        else
        {
            addToFavButton.setText("♡");
        }

    }
    public void addToFave()
    {
        pokemon.setFavourite(!pokemon.getisFavourite());
        System.out.println("ID is = " + pokemon.getID());
        int id = pokemon.getID();
        if(addToFavButton.getText().equals("♡"))
        {
            addToFavButton.setText("❤");
        }
        else
        {
            addToFavButton.setText("♡");
        }

        try{

            String url = "jdbc:mysql://127.0.0.1:3306/javafxdb";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM javafxdb.pokemoinfo";
            query= "UPDATE javafxdb.pokemoinfo SET isFav = ? WHERE id = ?";

            PreparedStatement pstatement = connection.prepareStatement(query);

            int val = pokemon.getisFavourite() ? 1 : 0;
            pstatement.setInt(1 , val);
            pstatement.setInt(2, id);
            pstatement.executeUpdate();

            query = "SELECT * FROM javafxdb.pokemoinfo";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                System.out.println(resultSet.getString("isFav"));


               // resultSet = statement.executeQuery(query);

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void DescriptionButtonClick()
    {
        String content = pokemon.getDescription();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Description");
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Add a close button
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // Show the dialog box and wait for it to be closed
        alert.showAndWait();
    }
}
