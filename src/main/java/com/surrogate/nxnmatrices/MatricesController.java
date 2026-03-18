package com.surrogate.nxnmatrices;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MatricesController implements Initializable {
    @FXML
    HBox root;
    int maxRows=50,maxCols=50;

    GridPane grid = new GridPane();
    SmartGrid smartGrid= new SmartGrid(grid, 50, 50);
    Button addColumns = new Button("aniadir columnas ");
    Button addRows = new Button("aniadir filas ");
    Button reset = new Button("reset");
    Button deleteRows = new Button("eliminar filas");
    VBox vbox = new VBox();
    Text text = new Text("Negrura de mierda la connnn");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbox.setPadding(new Insets(10, 10, 10, 10));

        vbox.setSpacing(10);
        vbox.getChildren().add(addColumns);
        vbox.getChildren().add(addRows);
        vbox.getChildren().add(text);
        vbox.getChildren().add(reset);
        grid.setPadding(new Insets(100));
        grid.setHgap(10);
        grid.setVgap(10);
        vbox.getChildren().add(deleteRows);

        addColumns.setOnAction(event ->
            setText()
        );
        addRows.setOnAction(event ->{
            if(cols==0){
                text.setText("debes aniadir cols primero");
            }else{
            if(rows>=maxRows-1){
                text.setText("no puedes aniadir mas filas");

            }
            addRowsToGrid();}});
        deleteRows.setOnAction(event ->{
            if(rows==0 || cols==0){
                text.setText("debes aniadir filas primero");
            }
            else{
                //deleteRowsFromGrid();
                }
        });
        addColumns.setOnAction(event-> {
            if(cols==maxCols-1){
                text.setText("no puedes aniadir mas columnas");
            }
            addColumnsToGrid();

        });
        reset.setOnAction(event ->reset());
        vbox.getChildren().add(smartGrid.grid);
        root.getChildren().add(vbox);


    }
    public void setText(){
        addColumns.setText("Stop");
    }
    public void reset(){
        smartGrid.clear();

        rows=0;
        cols=0;
        System.out.flush();
    }
    int rows=0;
    int cols=0;
    public void addColumnsToGrid()  {


        if(rows==0) {
            for (int i = 0; i <= rows; i++) {
                ;
                for (int j = 0; j <= cols; j++) {
                    if (smartGrid.isEmpty(j, i)) {

                        smartGrid.add(new TextField(), j, i);
                    }
                }
            }
            cols++;
        }else {
            if (rows >= 1) {
                for (int i = 0; i < rows; i++) {
                    ;
                    for (int j = 0; j <= cols; j++) {
                        if (smartGrid.isEmpty(j, i)) {
                            smartGrid.add(new TextField(), j, i);
                        }
                    }
                }
                cols++;

            }
        }
        }

        boolean isFirst = true;
//    public void deleteRowsFromGrid(){
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (!smartGrid.isEmpty(i,j ) && j==rows-1) {
//                smartGrid.remove(i,j);}
//            }
//        }


//obtener los x y z .... n
    public void getTextFromGrid(){
        for (Node node : smartGrid.grid.getChildren()) {
            if (node instanceof TextField textField) {
                String strTextField = textField.getText();
                if(strTextField == null || strTextField.isBlank()){
                    text.setText("debe ingresar todos los valores");
                }



            }
        }

    }


    public void deleteColumnsFromGrid(){
    }
    public void addRowsToGrid()  {

        if(rows==0){

            rows++;
            isFirst = false;
        } ;
        ;
        if(cols==0){
            for (int i = 0; i<=cols; i++) {
                ;
                for (int j = 0; j <= rows; j++) {
                    if (smartGrid.isEmpty(i, j)) {
                        ;
                        smartGrid.add(new TextField(), i,j);
                    }
                }
            }
        }
        for (int i = 0; i < cols; i++) {
            ;
            for (int j = 0; j <= rows; j++) {
                if (smartGrid.isEmpty(i, j)) {
                    ;
                    smartGrid.add(new TextField(), i,j);
                }
            }
        }
        if(!isFirst){
            rows++;
        }




}
//clase auxiliar para que sea mas facil obtener el indice de cada uno de los numeros

static class SmartGrid {

     final GridPane grid;
     Node[][] matrix;

    public SmartGrid(GridPane grid, int rows, int cols) {
        this.grid = grid;
        this.matrix = new Node[rows][cols];
    }

    public boolean isEmpty(int row, int col) {
        return matrix[row][col] == null;
    }

    public void add(Node node, int row, int col) {
        if (!isEmpty(row, col)) {
            throw new IllegalStateException("mfkin celda ocupada, si tiro una maldita excepcion y cual hay gil");
        }

        matrix[row][col] = node;
        grid.add(node, row, col);
    }

    public void remove(int row, int col) {
        Node node = matrix[row][col];

        if (node != null) {
            grid.getChildren().remove(node);
            matrix[row][col] = null;
        }
    }
    public void clear(){
        this.grid.getChildren().clear();
        matrix= new Node[50][50];
    }

    public Node get(int row, int col) {
        return matrix[row][col];
    }

    public void move(int fromRow, int fromCol, int toRow, int toCol) {
        if (isEmpty(fromRow, fromCol)) return;
        if (!isEmpty(toRow, toCol)) {
            throw new IllegalStateException("Destino ocupado");
        }

        Node node = matrix[fromRow][fromCol];

        remove(fromRow, fromCol);
        add(node, toRow, toCol);
    }
}}