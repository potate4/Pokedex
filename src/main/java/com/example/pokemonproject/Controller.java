package com.example.pokemonproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private GridPane pokemonsGrid;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchButton;

    public List <Pokemon> pokemons;

    @FXML
    private ChoiceBox<String> filterBy;

    private String[] ops = {"Name", "Type"};

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        filterBy.getItems().addAll(ops);
        pokemons = new ArrayList<>(getPokemonsFromDB());
       // System.out.println("ARRAY SIZE : " + pokemons.size());
        int column = 0;
        int row = 1;
        for(Pokemon pokemon : pokemons)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pokemonFXML.fxml"));

            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PokemonController pokemonController = fxmlLoader.getController();
            pokemonController.setPokemon(pokemon);
            pokemonController.setData(pokemon);
            if(column == 3)
            {
                column = 0;
                ++row;
            }
            pokemonsGrid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(20));
        }
    }

    public String getFilterType() {

        String mytype = filterBy.getValue();
        return  mytype;
    }
    public List<Pokemon> getPokemons()
    {
        List<Pokemon> ls = new ArrayList<>();
        Pokemon pokemon = new Pokemon();
        pokemon.setType("Type1");
        pokemon.setDescription("Des1");
        pokemon.setHeight("H1");
        pokemon.setWeight("W1");
        pokemon.setEvolution(("E1"));
        pokemon.setImgSrc("/img/pokemon3.jpg");


        ls.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setType("Type2");
        pokemon.setDescription("Des2");
        pokemon.setHeight("H2");
        pokemon.setWeight("W2");
        pokemon.setEvolution(("E2"));
        pokemon.setImgSrc("/img/pokemon3.jpg");

        ls.add(pokemon);



        pokemon = new Pokemon();
        pokemon.setType("Type2");
        pokemon.setDescription("Des2");
        pokemon.setHeight("H2");
        pokemon.setWeight("W2");
        pokemon.setEvolution(("E2"));
        pokemon.setImgSrc("/img/pokemon3.jpg");

        ls.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setType("Type2");
        pokemon.setDescription("Des2");
        pokemon.setHeight("H2");
        pokemon.setWeight("W2");
        pokemon.setEvolution(("E2"));
        pokemon.setImgSrc("/img/pokemon3.jpg");

        ls.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setType("Type2");
        pokemon.setDescription("Des2");
        pokemon.setHeight("H2");
        pokemon.setWeight("W2");
        pokemon.setEvolution(("E2"));
        pokemon.setImgSrc("/img/pokemon3.jpg");

        ls.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setType("Type2");
        pokemon.setDescription("Des2");
        pokemon.setHeight("H2");
        pokemon.setWeight("W2");
        pokemon.setEvolution(("E2"));
        pokemon.setImgSrc("/img/pokemon3.jpg");

        ls.add(pokemon);

        pokemon = new Pokemon();
        pokemon.setType("Type2");
        pokemon.setDescription("Des2");
        pokemon.setHeight("H2");
        pokemon.setWeight("W2");
        pokemon.setEvolution(("E2"));
        pokemon.setImgSrc("/img/pokemon3.jpg");

        ls.add(pokemon);


        return ls;


    }


    public List<Pokemon> getPokemonsFromDB()
    {
        List<Pokemon> ls = new ArrayList<>();
        Pokemon pokemon = new Pokemon();

        try {

            String url = "jdbc:mysql://127.0.0.1:3306/javafxdb";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM javafxdb.pokemoinfo";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                pokemon.setID(resultSet.getInt("id"));
                pokemon.setType(resultSet.getString("type"));
                pokemon.setName(resultSet.getString("name"));

                pokemon.setDescription(resultSet.getString("description"));
                pokemon.setHeight(resultSet.getString("height"));
                pokemon.setWeight(resultSet.getString("weight"));
                pokemon.setEvolution((resultSet.getString("evolution")));
                pokemon.setEvolutions(getListOfEvols(resultSet.getInt("id")));
                pokemon.setImgSrc(resultSet.getString("imgSrc"));
                pokemon.setFavourite(resultSet.getBoolean("isFav"));
                ls.add(pokemon);
                pokemon = new Pokemon();


            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ls;
    }

    public List<String> getListOfEvols(int id)
    {
        List<String> evols = new ArrayList<>();

        try {

            String url = "jdbc:mysql://127.0.0.1:3306/javafxdb";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM javafxdb.evolutionsinfo";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if(resultSet.getInt("pokemonID") == id)
                {
                    evols.add(resultSet.getString("evolutions"));
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return evols;
    }


    public List<Pokemon> getPokemonsFromDBByType(String type)
    {
        List<Pokemon> ls = new ArrayList<>();
        Pokemon pokemon = new Pokemon();


        try{

            String url = "jdbc:mysql://127.0.0.1:3306/javafxdb";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM javafxdb.pokemoinfo";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                pokemon.setID(resultSet.getInt("id"));

                pokemon.setType(resultSet.getString("type"));
                pokemon.setName(resultSet.getString("name"));

                pokemon.setDescription(resultSet.getString("description"));
                pokemon.setHeight(resultSet.getString("height"));
                pokemon.setWeight(resultSet.getString("weight"));
                pokemon.setEvolution((resultSet.getString("evolution")));
                pokemon.setEvolutions(getListOfEvols(resultSet.getInt("id")));

                pokemon.setImgSrc(resultSet.getString("imgSrc"));
                pokemon.setFavourite(resultSet.getBoolean("isFav"));
                if(resultSet.getString("type").equals(type))
                {
                    ls.add(pokemon);
                }

                pokemon = new Pokemon();

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ls;
    }



    public List<Pokemon> getPokemonsFromDBByName(String name)
    {
        List<Pokemon> ls = new ArrayList<>();
        Pokemon pokemon = new Pokemon();


        try{

            String url = "jdbc:mysql://127.0.0.1:3306/javafxdb";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM javafxdb.pokemoinfo";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                pokemon.setID(resultSet.getInt("id"));

                pokemon.setType(resultSet.getString("type"));
                pokemon.setName(resultSet.getString("name"));

                pokemon.setDescription(resultSet.getString("description"));
                pokemon.setHeight(resultSet.getString("height"));
                pokemon.setWeight(resultSet.getString("weight"));
                pokemon.setEvolution((resultSet.getString("evolution")));
                pokemon.setEvolutions(getListOfEvols(resultSet.getInt("id")));

                pokemon.setImgSrc(resultSet.getString("imgSrc"));
                pokemon.setFavourite(resultSet.getBoolean("isFav"));
                if(resultSet.getString("name").equals(name))
                {
                    ls.add(pokemon);
                }

                pokemon = new Pokemon();

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ls;
    }


    public List<Pokemon> getPokemonsFromDBFavs()
    {
        List<Pokemon> ls = new ArrayList<>();
        Pokemon pokemon = new Pokemon();


        try{

            String url = "jdbc:mysql://127.0.0.1:3306/javafxdb";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM javafxdb.pokemoinfo";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                pokemon.setID(resultSet.getInt("id"));

                pokemon.setType(resultSet.getString("type"));
                pokemon.setName(resultSet.getString("name"));

                pokemon.setDescription(resultSet.getString("description"));
                pokemon.setHeight(resultSet.getString("height"));
                pokemon.setWeight(resultSet.getString("weight"));
                pokemon.setEvolution((resultSet.getString("evolution")));
                pokemon.setEvolutions(getListOfEvols(resultSet.getInt("id")));

                pokemon.setImgSrc(resultSet.getString("imgSrc"));
                pokemon.setFavourite(resultSet.getBoolean("isFav"));
                if(resultSet.getInt("isFav") == 1)
                {
                    ls.add(pokemon);
                }

                pokemon = new Pokemon();

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ls;
    }

    public void favoritesView()
    {
        pokemonsGrid.getChildren().clear();

        pokemons = new ArrayList<>(getPokemonsFromDBFavs());
        // System.out.println("ARRAY SIZE : " + pokemons.size());
        int column = 0;
        int row = 1;
        for(Pokemon pokemon : pokemons)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pokemonFXML.fxml"));

            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PokemonController pokemonController = fxmlLoader.getController();
            pokemonController.setPokemon(pokemon);
            pokemonController.setData(pokemon);
            if(column == 3)
            {
                column = 0;
                ++row;
            }
            pokemonsGrid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(20));
        }
    }

    public void homeView()
    {
        pokemonsGrid.getChildren().clear();

        pokemons = new ArrayList<>(getPokemonsFromDB());
        // System.out.println("ARRAY SIZE : " + pokemons.size());
        int column = 0;
        int row = 1;
        for(Pokemon pokemon : pokemons)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pokemonFXML.fxml"));

            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PokemonController pokemonController = fxmlLoader.getController();
            pokemonController.setPokemon(pokemon);
            pokemonController.setData(pokemon);
            if(column == 3)
            {
                column = 0;
                ++row;
            }
            pokemonsGrid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(20));
        }
    }

    public void filter()
    {
        String searchBarText = SearchBar.getText();
        String option = getFilterType();
        if(option.equals("Type")){
            filterByType();
        }
        else {
            filterByName();
        }

    }



    public void filterByType()
    {
        String type = SearchBar.getText();
        System.out.println("Search invoked " + type);
        pokemons = new ArrayList<>(getPokemonsFromDBByType(type));
        // System.out.println("ARRAY SIZE : " + pokemons.size());
        int column = 0;
        int row = 1;
        pokemonsGrid.getChildren().clear();
        for(Pokemon pokemon : pokemons)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pokemonFXML.fxml"));

            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PokemonController pokemonController = fxmlLoader.getController();
            pokemonController.setPokemon(pokemon);
            pokemonController.setData(pokemon);
            if(column == 3)
            {
                column = 0;
                ++row;
            }
            pokemonsGrid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(20));
        }
    }


    public void filterByName()
    {
        String name = SearchBar.getText();
        System.out.println("Search invoked ");
        pokemons = new ArrayList<>(getPokemonsFromDBByName(name));
        // System.out.println("ARRAY SIZE : " + pokemons.size());
        int column = 0;
        int row = 1;
        pokemonsGrid.getChildren().clear();
        for(Pokemon pokemon : pokemons)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("pokemonFXML.fxml"));

            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PokemonController pokemonController = fxmlLoader.getController();
            pokemonController.setPokemon(pokemon);
            pokemonController.setData(pokemon);
            if(column == 3)
            {
                column = 0;
                ++row;
            }
            pokemonsGrid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(20));
        }
    }
}