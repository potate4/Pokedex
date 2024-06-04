module com.example.pokemonproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.pokemonproject to javafx.fxml;
    exports com.example.pokemonproject;
}