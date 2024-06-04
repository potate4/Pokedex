package com.example.pokemonproject;


import java.sql.*;

public class myJDBC {
    public static void main(String[] args)
    {
        try{

            String url = "jdbc:mysql://127.0.0.1:3306/javafxdb";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM javafxdb.pokemoninfo";

            ResultSet resultSet = statement.executeQuery(query);
            Pokemon pokemon = new Pokemon();
            while(resultSet.next())
            {
                pokemon.setType(resultSet.getString("type"));
                pokemon.setDescription(resultSet.getString("description"));
                pokemon.setHeight(resultSet.getString("height"));
                pokemon.setWeight(resultSet.getString("weight"));
                pokemon.setEvolution((resultSet.getString("evolution")));
                pokemon.setImgSrc(resultSet.getString("imgSrc"));
                pokemon.setFavourite(resultSet.getBoolean("isFav"));
                //ls.add(pokemon);
                pokemon = new Pokemon();

                System.out.println(pokemon);


            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }


    }
}
