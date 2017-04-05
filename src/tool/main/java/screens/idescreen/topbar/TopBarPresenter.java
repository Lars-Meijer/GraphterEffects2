package screens.idescreen.topbar;

import general.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import screens.idescreen.topbar.buttonbar.ButtonBarView;
import screens.idescreen.topbar.menubar.MenuBarView;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TopBarPresenter implements Initializable{

    @Inject ViewModel viewModel;
    public SplitPane splitPane;
    public AnchorPane topAnchorPane;
    public AnchorPane bottomAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuBarView menuBarView = new MenuBarView();
        ButtonBarView buttonBarView = new ButtonBarView();

        //TODO: DO this with bind and properties
        splitPane.setDividerPosition(1,splitPane.getHeight() - splitPane.getInsets().getBottom());

        splitPane.setPrefHeight(65);
        splitPane.setMinHeight(65);
        topAnchorPane.setPrefHeight(30);
        bottomAnchorPane.setPrefHeight(35);
        bottomAnchorPane.setMinHeight(35);

        topAnchorPane.getChildren().add(menuBarView.getView());
        bottomAnchorPane.getChildren().add(buttonBarView.getView());

        bind();
    }

    public void bind() {
        splitPane.setStyle("-fx-background-color: #fff52c;");
        splitPane.prefWidthProperty().bind( ((Pane) (viewModel.getMainView()).getParent()).widthProperty() );
    }
}