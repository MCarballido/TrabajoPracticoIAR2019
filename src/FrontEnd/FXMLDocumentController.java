package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import BackEnd.*;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class FXMLDocumentController implements Initializable
{
    private Grafo g;
    private LinkedList<String[]> arcList = new LinkedList<>();
    
    @FXML
    private TextField txtNewNode;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> cmbNodoFrom;
    @FXML
    private ComboBox<String> cmbNodoTo;
    @FXML
    private Button btnNuevoNodo;
    @FXML
    private Button btnNuevoArco;
    @FXML
    private ListView<String> lstNodes;
    @FXML
    private Button btnClearListView;
    @FXML
    private Button btnGenerarNodos;
    @FXML
    private Label lblNewArc;
    @FXML
    private Label lblArcArrow;
    @FXML
    private ListView<String> lstArcs;
    @FXML
    private Button btnClearArcs;
    @FXML
    private Label lblNewNode;
    @FXML
    private Button btnGenerateNew;
    @FXML
    private Label label1;
    @FXML
    private Label lblStartDFS;
    @FXML
    private Label lblEndDFS;
    @FXML
    private ComboBox<String> cmbStartDFS;
    @FXML
    private ComboBox<String> cmbEndDFS;
    @FXML
    private Button btnSearchDFS;
    @FXML
    private Label lblPathDFS;
    @FXML
    private TextField txtResultDFS;
    @FXML
    private Label lblStartBFS;
    @FXML
    private Label lblEndBFS;
    @FXML
    private ComboBox<String> cmbStartBFS;
    @FXML
    private ComboBox<String> cmbEndBFS;
    @FXML
    private Button btnSearchBFS;
    @FXML
    private Label lblPathBFS;
    @FXML
    private TextField txtResultBFS;
    
    @FXML
    private void onNodeAddPress(ActionEvent event)
    {
        String nodeText = txtNewNode.getText();
        
        if (lstNodes.getItems().contains(nodeText) || nodeText == "" || nodeText.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Operación no válida");
            alert.setHeaderText(null);
            alert.setContentText("No se puede ingresar un nodo ya existente o vacío");
            alert.showAndWait();
            return;
        }
        
        lstNodes.getItems().add(nodeText);
        cmbNodoFrom.getItems().add(nodeText);
        cmbNodoTo.getItems().add(nodeText);
        cmbStartDFS.getItems().add(nodeText);
        cmbEndDFS.getItems().add(nodeText);
        cmbStartBFS.getItems().add(nodeText);
        cmbEndBFS.getItems().add(nodeText);
        txtNewNode.clear();
    }

    @FXML
    private void onClearNodesPress(ActionEvent event)
    {
        g = new Grafo();
        
        lstNodes.getItems().clear();
        cmbNodoFrom.getItems().clear();
        cmbNodoTo.getItems().clear();
        cmbStartDFS.getItems().clear();
        cmbEndDFS.getItems().clear();
        cmbStartBFS.getItems().clear();
        cmbEndBFS.getItems().clear();
    }
    
    @FXML
    private void onGenerateNodesPress(ActionEvent event)
    {
        int n = lstNodes.getItems().size();
        g = new Grafo(n);
        
        for (String item : lstNodes.getItems())
        {
            g.setNodo(item);
        }
        
        lblNewArc.setDisable(false);
        lblArcArrow.setDisable(false);
        btnNuevoArco.setDisable(false);
        btnClearArcs.setDisable(false);
        cmbNodoFrom.setDisable(false);
        cmbNodoTo.setDisable(false);
        
        lblNewNode.setDisable(true);
        btnNuevoNodo.setDisable(true);
        btnClearListView.setDisable(true);
        btnGenerarNodos.setDisable(true);
        txtNewNode.setDisable(true);
    }

    @FXML
    private void onNewArcPress(ActionEvent event)
    {
        String n1 = cmbNodoFrom.getValue();
        String n2 = cmbNodoTo.getValue();
        
        arcList.add(new String[]{n1, n2});
        
        g.unir(n1, n2);
        
        lstArcs.getItems().add(n1 + " -> " + n2);
        
        cmbNodoFrom.getSelectionModel().clearSelection();
        cmbNodoTo.getSelectionModel().clearSelection();
    }

    @FXML
    private void onClearArcsPress(ActionEvent event)
    {
        lstArcs.getItems().clear();
        
        for (String[] arc : arcList)
        {
            g.cortar(arc[0], arc[1]);
        }
    }

    @FXML
    private void onCreateNewGraphPress(ActionEvent event)
    {
        onClearArcsPress(null);
        onClearNodesPress(null);
        
        lblNewArc.setDisable(true);
        lblArcArrow.setDisable(true);
        btnNuevoArco.setDisable(true);
        btnClearArcs.setDisable(true);
        cmbNodoFrom.setDisable(true);
        cmbNodoTo.setDisable(true);
        
        lblNewNode.setDisable(false);
        btnNuevoNodo.setDisable(false);
        btnClearListView.setDisable(false);
        btnGenerarNodos.setDisable(false);
        txtNewNode.setDisable(false);
        
        txtResultDFS.setText("");
        txtResultBFS.setText("");
    }

    @FXML
    private void onDFS(ActionEvent event)
    {
        String start = cmbStartDFS.getValue();
        String end = cmbEndDFS.getValue();
        
        txtResultDFS.setText(g.buscarCaminoDFS(start, end));
    }

    @FXML
    private void onBFS(ActionEvent event)
    {
        String start = cmbStartBFS.getValue();
        String end = cmbEndBFS.getValue();
        
        txtResultBFS.setText(g.buscarCaminoBFS(start, end));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
}
