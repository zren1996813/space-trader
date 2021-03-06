import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ShipInventoryViewController {
    @FXML
    private Text good1NameText;
    @FXML
    private Text good2NameText;
    @FXML
    private Text good3NameText;
    @FXML
    private Text good4NameText;
    @FXML
    private Text good5NameText;
    @FXML
    private Text good6NameText;
    @FXML
    private Text good7NameText;
    @FXML
    private Text good8NameText;
    @FXML
    private Text good9NameText;
    @FXML
    private Text good10NameText;
    @FXML
    private Text good11NameText;
    @FXML
    private Text good12NameText;
    @FXML
    private Text good13NameText;
    @FXML
    private Text good14NameText;
    @FXML
    private Text good15NameText;
    @FXML
    private Text good1CapacityText;
    @FXML
    private Text good2CapacityText;
    @FXML
    private Text good3CapacityText;
    @FXML
    private Text good4CapacityText;
    @FXML
    private Text good5CapacityText;
    @FXML
    private Text good6CapacityText;
    @FXML
    private Text good7CapacityText;
    @FXML
    private Text good8CapacityText;
    @FXML
    private Text good9CapacityText;
    @FXML
    private Text good10CapacityText;
    @FXML
    private Text good11CapacityText;
    @FXML
    private Text good12CapacityText;
    @FXML
    private Text good13CapacityText;
    @FXML
    private Text good14CapacityText;
    @FXML
    private Text good15CapacityText;
    @FXML
    private Text good1Quantity;
    @FXML
    private Text good2Quantity;
    @FXML
    private Text good3Quantity;
    @FXML
    private Text good4Quantity;
    @FXML
    private Text good5Quantity;
    @FXML
    private Text good6Quantity;
    @FXML
    private Text good7Quantity;
    @FXML
    private Text good8Quantity;
    @FXML
    private Text good9Quantity;
    @FXML
    private Text good10Quantity;
    @FXML
    private Text good11Quantity;
    @FXML
    private Text good12Quantity;
    @FXML
    private Text good13Quantity;
    @FXML
    private Text good14Quantity;
    @FXML
    private Text good15Quantity;
    @FXML
    private Text playerInfoText;
    @FXML
    private Text errorMessage;
    @FXML
    private TextField refillTextField;
    @FXML
    private TextField repairTextField;

    private Text[] goodNameText;
    private Text[] goodCapacityText;
    private Text[] goodQuantityText;
    private Good[] good;

    private Player player;
    private Good[] shipInventory;
    private int numberOfGood;

    public void initialize() {
        setUpUIObject();

        player = MapViewController.getPlayer();
        shipInventory = MapViewController.getPlayer().getShip().getItemInventory();
        good = MapViewController.getGood();
        numberOfGood = good.length;

        // set up layout of the UI
        updateGoodInfo();
        updateCharacterInfo();
    }

    private void setUpUIObject() {
        goodNameText = new Text[]{good1NameText, good2NameText,
            good3NameText, good4NameText, good5NameText,
            good6NameText, good7NameText, good8NameText,
            good9NameText, good10NameText, good11NameText,
            good12NameText, good13NameText, good14NameText,
            good15NameText};
        goodCapacityText = new Text[]{good1CapacityText, good2CapacityText,
            good3CapacityText, good4CapacityText,
            good5CapacityText, good6CapacityText, good7CapacityText,
            good8CapacityText, good9CapacityText,
            good10CapacityText, good11CapacityText, good12CapacityText,
            good13CapacityText, good14CapacityText,
            good15CapacityText};
        goodQuantityText = new Text[]{good1Quantity, good2Quantity,
            good3Quantity, good4Quantity, good5Quantity,
            good6Quantity, good7Quantity, good8Quantity,
            good9Quantity, good10Quantity, good11Quantity,
            good12Quantity, good13Quantity, good14Quantity,
            good15Quantity};
    }

    public void updateGoodInfo() {
        // update all ui texts
        for (int i = 0; i < numberOfGood; i++) {
            goodNameText[i].setText(good[i].getName());
            goodCapacityText[i].setText(String.valueOf(good[i].getVolume()));
            goodQuantityText[i].setText(String.valueOf(shipInventory[i].getQuantity()));
        }
    }

    public void updateCharacterInfo() {
        String characterInfo = String.format("Player: %s\n", player.getName())
            + String.format("Ship: %s\n", player.getShip().getName())
            + String.format("Balance: %d\n", player.getBalance())
            + String.format("Ship Capacity: %d\n", player.getShip().getCargoCapacity())
            + String.format("Ship Health: %d\n", player.getShip().getHealth())
            + String.format("Ship Fuel: %d\n", player.getShip().getFuelCapacity());

        playerInfoText.setText(characterInfo);
    }


    public void marketBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("MarketView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void refillBtnPressed(ActionEvent actionEvent) {
        int refillAmount = 0;
        if (refillTextField.getText().isEmpty()) {
            errorMessage.setText("Error: empty refill amount");
        }

        try {
            refillAmount = Integer.parseInt(refillTextField.getText());
        } catch (NumberFormatException notInt) {
            errorMessage.setText("The refilled gas has to be an integer");
        }
        if (refillAmount > (100 - player.getShip().getFuelCapacity())) {
            errorMessage.setText("You reached the maximum of your fuel capacity");
            System.out.println(player.getShip().getFuelCapacity());
        } else if (player.getBalance() < refillAmount) {
            errorMessage.setText("You don't have enough balance to refill");
        } else {
            player.getShip().setFuelCapacity(player.getShip().getFuelCapacity() + refillAmount);
            player.setBalance(player.getBalance() - refillAmount);
            updateCharacterInfo();
        }
    }

    public void exitBtnPressed(ActionEvent actionEvent) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("MapView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void repairBtnPressed(ActionEvent actionEvent) {
        int repairAmount = 0;
        try {
            repairAmount = Integer.parseInt(repairTextField.getText());
        } catch (NumberFormatException notInt) {
            errorMessage.setText("The amount has to be an integer");
        }
        if (repairAmount > (100 - player.getShip().getHealth())) {
            errorMessage.setText("You reached the maximum of ship's health");
        } else if (player.getBalance() < repairAmount * 100
                * (1 - player.getEngineerSkill() / 10.0)) {
            errorMessage.setText("You don't have enough balance to repair");
        } else {
            player.getShip().setHealth(player.getShip().getHealth() + repairAmount);
            player.setBalance((int) (player.getBalance()
                    - repairAmount * 100 * (1 - player.getEngineerSkill() / 10.0)));
            updateCharacterInfo();
        }
    }
}
