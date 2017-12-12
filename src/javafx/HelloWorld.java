package javafx;
/*
JavaFXアプリケーションのメイン・クラスによって、javafx.application.Applicationクラスが拡張。
start()メソッドは、すべてのJavaFXアプリケーションのエントリ・ポイント。

JavaFXアプリケーションでは、ステージおよびシーンを使用してユーザー・インタフェース・コンテナが定義される。
JavaFX Stageクラスは、最上位レベルのJavaFXコンテナです。
JavaFX Sceneクラスは、すべてのコンテンツのコンテナです。
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}