import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import java.util.Vector;
public class k extends Application {
	@Override
	public void start(Stage primaryStage) {
		CheckerPane checker = new CheckerPane(10, 100);
		// 사냥꾼 객체 생성
		Hunter hunter = new Hunter();
		Random r = new Random();

		Text text = new Text("Hunter's Hp : " + hunter.getHp());
		Text textScore = new Text("Your Score : " + hunter.getScore());
		Text textIsPossible = new Text(hunter.getPotionPossible() + " Potion Left");
		Set s = new Set();
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(20, 20, 20, 10));

		// setting 화면 만들기
		GridPane labelPane = new GridPane();
		labelPane.setPadding(new Insets(2, 2, 2, 2));
		labelPane.setAlignment(Pos.CENTER);
		labelPane.setPrefWidth(430.0);
		labelPane.setStyle("-fx-border-color: black");
		labelPane.setVgap(30);

		Label tigerNum = new Label("Set Number of Tiger :(0~5) ");
		TextField tigerField = new TextField();
		tigerField.setPromptText("Enter 1~5");
		labelPane.add(tigerNum, 0, 0);
		labelPane.add(tigerField, 1, 0);

		Label rabbitNum = new Label("Set Number of Rabbit :(0~10) ");
		TextField rabbitField = new TextField();
		rabbitField.setPromptText("Enter 1~10");
		labelPane.add(rabbitNum, 0, 1);
		labelPane.add(rabbitField, 1, 1);

		Label grassNum = new Label("Set Number of Grass :(0~10) ");
		TextField grassField = new TextField();
		grassField.setPromptText("Enter 1~10");
		labelPane.add(grassNum, 0, 2);
		labelPane.add(grassField, 1, 2);

		Label trapNum = new Label("Set Number of Trap :(1~3) ");
		TextField trapField = new TextField();
		trapField.setPromptText("Enter 1~3");
		labelPane.add(trapNum, 0, 3);
		labelPane.add(trapField, 1, 3);

		// 게임진행에 필요한 버튼들
		Button btStart = new Button("Start The Game!");
		Button btPotion = new Button("Potion");
		Button btUp = new Button("     Go Up     ");
		Button btRight = new Button("    Go Right    ");
		Button btDown = new Button("   Go Down   ");
		Button btLeft = new Button("    Go Left    ");
		Button btUpLeft = new Button("  Go UpLeft  ");
		Button btUpRight = new Button("   Go UpRight   ");
		Button btDownLeft = new Button("Go DownLeft");
		Button btDownRight = new Button("Go DownRight");
		// 객체 생성
		Tiger[] tigerArr = new Tiger[100];
		Rabbit[] rabbitArr = new Rabbit[100];
		Grass[] grassArr = new Grass[100];
		Trap[] trapArr = new Trap[10];
		labelPane.add(btStart, 1, 4);
		
		// start 버튼 클릭 event
		btStart.setOnAction(e -> {
			s.setTiger(Integer.parseInt(tigerField.getText()));
			s.setRabbit(Integer.parseInt(rabbitField.getText()));
			s.setGrass(Integer.parseInt(grassField.getText()));
			s.setTrap(Integer.parseInt(trapField.getText()));
			
			labelPane.add(btPotion, 0, 5);
			labelPane.add(text, 0, 6);
			labelPane.add(textIsPossible, 0, 7);
			labelPane.add(btUp, 1, 8);
			labelPane.add(btLeft, 0, 9);
			labelPane.add(btRight, 2, 9);
			labelPane.add(btDown, 1, 10);
			labelPane.add(btUpLeft, 0, 8);
			labelPane.add(btUpRight, 2, 8);
			labelPane.add(btDownLeft, 0, 10);
			labelPane.add(btDownRight, 2, 10);
			labelPane.add(textScore, 0, 11);
			
			//랜덤으로 중복 배치
			int location[][] = randomSelction();
			int[] rowList = new int[30];
			int[] colList = new int[30];
			for (int row = 0, i = 0; row < location.length; row++) {
				for (int column = 0; column < location[row].length; column++) {
					if (location[row][column] == 1) {
						rowList[i] = row;
						colList[i] = column;
						i++;
					}
				}
			}

			for (int i = 0; i < s.getNumOfTiger(); i++) {
				tigerArr[i] = new Tiger();
				int j = r.nextInt(30);
				for (int k = 0; k < 30; k++) {
					if (rowList[j] == -1) {
						j = r.nextInt(30);
					} 
					else
						break;
				}
				tigerArr[i].setLocation(rowList[j], colList[j]);
				rowList[j] = -1;
				colList[j] = -1;
				checker.getChildren().add(tigerArr[i].imageView);
			}
			for (int i = 0; i < s.getNumOfRabbit(); i++) {
				rabbitArr[i] = new Rabbit();
				int j = r.nextInt(30);
				for (int k = 0; k < 30; k++) {
					if (rowList[j] == -1) {
						j = r.nextInt(30);
					} 
					else
						break;
				}
				rabbitArr[i].setLocation(rowList[j], colList[j]);
				rowList[j] = -1;
				colList[j] = -1;
				checker.getChildren().add(rabbitArr[i].imageView);
			}
			for (int i = 0; i < s.getNumOfGrass(); i++) {
				grassArr[i] = new Grass();
				int j = r.nextInt(30);
				for (int k = 0; k < 30; k++) {
					if (rowList[j] == -1) {
						j = r.nextInt(30);
					} 
					else
						break;
				}
				grassArr[i].setLocation(rowList[j], colList[j]);
				rowList[j] = -1;
				colList[j] = -1;
				checker.getChildren().add(grassArr[i].imageView);
			}
			for (int i = 0; i < s.getNumOfTrap(); i++) {
				trapArr[i] = new Trap();
				int j = r.nextInt(30);
				for (int k = 0; k < 30; k++) {
					if (rowList[j] == -1) {
						j = r.nextInt(30);
					} 
					else
						break;
				}
				//trapArr[i].setLocation(colList[j], rowList[j]);
				trapArr[i].setLocation(rowList[j], colList[j]);
				rowList[j] = -1;
				colList[j] = -1;
				checker.getChildren().add(trapArr[i].imageView);
			}

			int hunterPosition = r.nextInt(30);
			for (int k = 0; k < 30; k++) {
				if (rowList[hunterPosition] == -1) {
					hunterPosition = r.nextInt(30);
				} 
				else
					break;
			}
			hunter.setLocation(rowList[hunterPosition], colList[hunterPosition]);
			checker.getChildren().add(hunter.imageView);
			//객체 배치 완료
		});

		// potion 사용 가능 여부 출력
		btPotion.setOnAction(e -> {
			if (hunter.potionPossible > 0) {
				hunter.potion();
				hunter.potionPossible--;
				text.setText("Hunter's Hp : " + hunter.getHp());
				textIsPossible.setText(hunter.getPotionPossible() + " Potion Left");
				//System.out.print(hunter.getRow());
			} 
			else {
				text.setText("You Can Not Use Potion!!");
			}
		});
		// 버튼을 누르면 이동
		btUp.setOnAction(e -> {
			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveUp(checker, hunter);
			//객체들간의 상호작용
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});
		//이하동일
		btRight.setOnAction(e -> {
			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveRight(checker, hunter);
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});
		btDown.setOnAction(e -> {

			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveDown(checker, hunter);
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});
		btLeft.setOnAction(e -> {

			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveLeft(checker, hunter);
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});
		btDownLeft.setOnAction(e -> {

			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveDownLeft(checker, hunter);
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});
		btUpRight.setOnAction(e -> {

			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveUpRight(checker, hunter);
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});
		btUpLeft.setOnAction(e -> {

			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveUpLeft(checker, hunter);
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});
		btDownRight.setOnAction(e -> {
		
			hunter.setHp(hunter.getHp() - 1);
			text.setText("Hunter's Hp : " + hunter.getHp());
			hunter.moveDownRight(checker, hunter);
			interaction(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
			textScore.setText("Your Score : " + hunter.getScore());
		});

		pane.setLeft(checker);
		pane.setRight(labelPane);

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Hunter_Game");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void interaction(CheckerPane checker, Hunter hunter, Tiger[] tigerArr, Rabbit[] rabbitArr, Grass[] grassArr, Trap[] trapArr, Set s) {
		hunter.catchObj(checker, hunter, tigerArr, rabbitArr, grassArr, trapArr, s);
		//tiger
		for (int i = 0; i < s.getNumOfTiger(); i++) {
			tigerArr[i].move(checker, tigerArr[i]);
		}
		for (int i = 0; i < s.getNumOfTiger(); i++) {
			tigerArr[i].catchObj(checker, hunter, tigerArr, tigerArr[i], rabbitArr, grassArr, trapArr, s);
		}
		/*for (int i = 0; i < s.getNumOfTiger(); i++) {
			tigerArr[i].starve(checker,tigerArr[i], tigerArr, s, i);
		}*/
		if(tigerArr[0] != null) {
			tigerArr[0].starve(checker, tigerArr, s);
		}
		for (int i = 0; i < s.getNumOfTiger(); i++) {
			tigerArr[i].breed(checker, tigerArr[i], tigerArr, rabbitArr, grassArr, trapArr, s);
		}
		
		//rabbit
		for (int i = 0; i < s.getNumOfRabbit(); i++) {
			rabbitArr[i].move(checker, rabbitArr[i]);
		}
		for (int i = 0; i < s.getNumOfRabbit(); i++) {
			rabbitArr[i].catchObj(checker, hunter, rabbitArr, rabbitArr[i], tigerArr, grassArr, trapArr, s);
		}
		if(rabbitArr[0] != null) {
			rabbitArr[0].starve(checker, rabbitArr, s);
		}
		for (int i = 0; i < s.getNumOfRabbit(); i++) {
			rabbitArr[i].breed(checker, rabbitArr[i], rabbitArr, tigerArr, grassArr, trapArr, s);
		}
		
		if (hunter.getHp() <= 0) {
			hunter.die();
			Platform.exit();
		}
	}
	// 중복 없이 랜덤한 자리 뽑기
	public static int[][] randomSelction() {
		int[][] arr = new int[10][10];
		Random r = new Random();
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < arr[row].length; column++) {
				int a = r.nextInt(10);
				int b = r.nextInt(10);
				arr[a][b] = 1;
			}
		}
		int count = 0;
		for (int row = 0; row < arr.length; row++) {
			for (int column = 0; column < arr[row].length; column++) {
				if (arr[row][column] == 1) {
					count++;
				}
			}
		}
		if (count != 30) {
			for (int row = 0; row < arr.length; row++) {
				for (int column = 0; column < arr[row].length; column++) {
					if (arr[row][column] == 0 && count != 30) {
						arr[row][column] = 1;
						count++;
					}
					if (count == 30) {
						break;
					}
				}
			}
		}
		return arr;
	}

	class Set {
		private int totalNumOfTiger = 0;
		private int totalNumOfRabbit = 0;
		private int totalNumOfGrass = 0;
		private int totalNumOfTrap = 0;

		public void setTiger(int num) {
			totalNumOfTiger = num;
		}

		public void setRabbit(int num) {
			totalNumOfRabbit = num;
		}

		public void setGrass(int num) {
			totalNumOfGrass = num;
		}

		public void setTrap(int num) {
			totalNumOfTrap = num;
		}

		public int getNumOfTiger() {
			return totalNumOfTiger;
		}

		public int getNumOfRabbit() {
			return totalNumOfRabbit;
		}

		public int getNumOfGrass() {
			return totalNumOfGrass;
		}

		public int getNumOfTrap() {
			return totalNumOfTrap;
		}
	}

	// 게임 판 만들기
	class CheckerPane extends GridPane {
		CheckerPane() {

		}

		CheckerPane(int n, int squareSize) {
			for (int col = 0; col < n; col++) {
				for (int row = 0; row < n; row++) {
					Rectangle rect = new Rectangle(0, 0, squareSize, squareSize);
					rect.setFill(Color.WHITE);
					rect.setStroke(Color.BLACK);
					super.add(rect, col, row);
				}
			}
		}
		public void addNode(ImageView i, int col, int row) {
			super.add(i, col, row);
		}
	}

//클래스

	class Hunter extends CheckerPane {
		private int Hp = 4;
		private int potionNum = 3;
		private int potionPossible = 3;
		private int score;
		private int row;
		private int col;
		public Image image = new Image("hunter.jpg");
		public ImageView imageView = new ImageView(image);

		Hunter() {
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
		}

		public void setLocation(int col, int row) {
			super.addNode(imageView, col, row);
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

		public void potion() {
			setHp(4);
		}

		public int getPotionPossible() {
			return potionPossible;
		}

		public void setHp(int Hp) {
			this.Hp = Hp;
		}

		public int getHp() {
			return Hp;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public int getScore() {
			return score;
		}

		// 이동 메소드
		public void moveUp(CheckerPane c, Hunter h) {
			if (getRow() <= 0) {
				// 가장자리 예외처리
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move Up!");
				alert.showAndWait();
			} 
			else {
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col, row - 1);
				c.getChildren().add(h.imageView);
			}

		}

		public void moveLeft(CheckerPane c, Hunter h) {
			if (getCol() <= 0) {
				// 가장자리 예외처리
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move Left!");
				alert.showAndWait();

			} 
			else {
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col - 1, row);
				c.getChildren().add(h.imageView);
			}
		}

		public void moveDown(CheckerPane c, Hunter h) {
			if (getRow() >= 9) {
				// 가장자리 예외처리
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move Down!");
				alert.showAndWait();
			} 
			else {
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col, row + 1);
				c.getChildren().add(h.imageView);
			}
		}

		public void moveRight(CheckerPane c, Hunter h) {
			if (getCol() >= 9) {
				// 가장자리 예외처리
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move Right!");
				alert.showAndWait();

			} 
			else {
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col + 1, row);
				c.getChildren().add(h.imageView);
			}
		}

		public void moveUpRight(CheckerPane c, Hunter h) {
			if (getCol() >= 9 || getRow() <= 0) {
				// 가장자리 예외처리
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move UpRight!");
				alert.showAndWait();

			} 
			else {
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col + 1, row - 1);
				c.getChildren().add(h.imageView);
			}
		}

		public void moveUpLeft(CheckerPane c, Hunter h) {
			if (getCol() <= 0 || getRow() <= 0) {
				// 가장자리 예외처리
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move UpLeft!");
				alert.showAndWait();

			} 
			else {
				// 가장자리 예외처리
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col - 1, row - 1);
				c.getChildren().add(h.imageView);
			}
		}

		public void moveDownRight(CheckerPane c, Hunter h) {
			if (getCol() >= 9 || getRow() >= 9) {
				// 가장자리 예외처리//
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move DownRight!");
				alert.showAndWait();

			} 
			else {
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col + 1, row + 1);
				c.getChildren().add(h.imageView);
			}
		}

		public void moveDownLeft(CheckerPane c, Hunter h) {
			if (getCol() <= 0 || getRow() >= 9) {
				// 가장자리 예외처리
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Move Problem");
				alert.setHeaderText("Can Not Move!");
				alert.setContentText("You Can Not Move DownLeft!");
				alert.showAndWait();

			} 
			else {
				int row = getRow();
				int col = getCol();
				c.getChildren().remove(h.imageView);
				h.setLocation(col - 1, row + 1);
				c.getChildren().add(h.imageView);
			}
		}

		public void die() {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Finish Of Game");
			alert.setHeaderText("Game Over!");
			alert.setContentText("Your Score Is " + getScore());
			alert.showAndWait();
		}

		public void catchObj(CheckerPane c, Hunter h, Tiger[] t, Rabbit[] r, Grass[] g, Trap[] tr, Set s) {
			int row = h.getRow();
			int col = h.getCol();

			for (int i = 0; i < s.getNumOfTiger(); i++) {
				if (t[i].getRow() == row && t[i].getCol() == col) {
					c.getChildren().remove(t[i].imageView);
					for (int j = i; j < s.getNumOfTiger() - 1; j++) {
						t[j] = t[j + 1];
					}
					//i--;
					s.setTiger(s.getNumOfTiger() - 1);
					h.setScore(h.getScore() + 10);
				}
			}
			for (int i = 0; i < s.getNumOfRabbit(); i++) {
				if (r[i].getRow() == row && r[i].getCol() == col) {
					c.getChildren().remove(r[i].imageView);
					for (int j = i; j < s.getNumOfRabbit() - 1; j++) {
						r[j] = r[j + 1];
					}
					//i--;
					s.setRabbit(s.getNumOfRabbit()  - 1);
					h.setScore(h.getScore() + 5);
				}

			}
			for (int i = 0; i < s.getNumOfTrap(); i++) {
				if (tr[i].getRow() == col && tr[i].getCol() == row) {
					c.getChildren().clear();
					h.die();
					Platform.exit();
				}
			}

		}
	}

	class Tiger extends CheckerPane {
		private int row;
		private int col;
		private int life = 4;
		private int age = 0;
		Image image = new Image("tiger.jpg");
		ImageView imageView = new ImageView(image);

		Tiger() {
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
		}
		public void setLife(int life) {
			this.life = life;
		}
		public int getLife() {
			return life;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getAge() {
			return age;
		}
		public void eatRabbit() {
			setLife(4);
		}
		public void setLocation(int col, int row) {
			super.addNode(imageView, col, row);
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}
		public void move(CheckerPane c, Tiger t) {
				t.setLife(t.getLife()-1);
				t.setAge(t.getAge()+1);
				Random r1 = new Random();
				int whereToGo = r1.nextInt(4);
				switch (whereToGo) {
				case 0:// up
					if (t.getRow() <= 0) {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col, row + 1);
						c.getChildren().add(t.imageView);

					} 
					else {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col, row - 1);
						c.getChildren().add(t.imageView);

					}
					break;

				case 1:// right
					if (t.getCol() >= 9) {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col - 1, row);
						c.getChildren().add(t.imageView);

					} 
					else {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col + 1, row);
						c.getChildren().add(t.imageView);

					}
					break;
				case 2:// down
					if (t.getRow() >= 9) {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col, row - 1);
						c.getChildren().add(t.imageView);

					} 
					else {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col, row + 1);
						c.getChildren().add(t.imageView);

					}
					break;
				case 3:// left
					if (t.getCol() <= 0) {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col + 1, row);
						c.getChildren().add(t.imageView);

					} 
					else {
						int row = t.getRow();
						int col = t.getCol();
						c.getChildren().remove(t.imageView);
						t.setLocation(col - 1, row);
						c.getChildren().add(t.imageView);

					}
					break;
				}
				
		}
		public void starve(CheckerPane c, Tiger[] tArr, Set s) {
			for(int i=0;i<s.getNumOfTiger();i++) {
				if(tArr[i].getLife()<=0) {
					c.getChildren().remove(tArr[i].imageView);
					for (int j = i; j < s.getNumOfTiger() - 1; j++) {
						tArr[j] = tArr[j + 1];
					}
					i--;
					s.setTiger(s.getNumOfTiger() - 1);	
				}
			}
		}
		/*public int starve(CheckerPane c, Tiger t, Tiger[] tArr, Set s, int k) {
			if(t.getLife()<=0) {
				int row = t.getRow();
				int col = t.getCol();
				c.getChildren().remove(t.imageView);
				for(int i=0;i<s.getNumOfTiger();i++) {
					if(tArr[i].getRow() == row && tArr[i].getCol() == col) {
						for (int j = i; j < s.getNumOfTiger() - 1; j++) {
							tArr[j] = tArr[j + 1];
						}
						break;
					}
					return --k;
				}
				s.setTiger(s.getNumOfTiger() - 1);	
			}
			return k;
		}*/
		public void catchObj(CheckerPane c, Hunter h, Tiger[] t, Tiger tiger, Rabbit[] r, Grass[] g, Trap[] tr, Set s) {
			int row = tiger.getRow();
			int col = tiger.getCol();

			if (h.getRow() == row && h.getCol() == col) {
				c.getChildren().remove(tiger.imageView);
				for(int i=0;i < s.getNumOfTiger();i++) {
					if(t[i].getRow() == row && t[i].getCol() == col) {
						for (int j = i; j < s.getNumOfTiger() - 1; j++) {
							t[j] = t[j + 1];
						}					
						//i--;
					}
				}
				s.setTiger(s.getNumOfTiger() - 1);
				h.setScore(h.getScore() + 10);
			}
			
			for (int i = 0; i < s.getNumOfRabbit(); i++) {
				if (r[i].getRow() == row && r[i].getCol() == col) {
					c.getChildren().remove(r[i].imageView);
					
					for (int j = i; j < s.getNumOfRabbit() - 1; j++) {
						r[j] = r[j + 1];
					}
					//i--;
					tiger.eatRabbit();
					s.setRabbit(s.getNumOfRabbit() - 1);
				}
			}
			for (int i = 0; i < s.getNumOfTrap(); i++) {
				if (tr[i].getRow() == col && tr[i].getCol() == row) {
					c.getChildren().remove(tiger.imageView);
					for(int k=0; k < s.getNumOfTiger(); k++) {
						if(t[k].getRow() == row && t[k].getCol() == col) {
							for (int j = k; j < s.getNumOfTiger() - 1; j++) {
								t[j] = t[j + 1];
							}
						}
					}
					//i--;
					s.setTiger(s.getNumOfTiger() - 1);
				}
			}
		}
		
		public void breed(CheckerPane c, Tiger t, Tiger[] tArr, Rabbit[] rArr, Grass[] gArr, Trap[] trArr, Set s) {
			if(t.getAge() >= 4 && t.getAge() % 4 == 0) {
				boolean isEmpty = true;
				int row = t.getRow();
				int col = t.getCol();
				Random r1 = new Random();
				//int whereToBreed = 0;
				int whereToBreed = r1.nextInt(4);
				switch (whereToBreed) {//상하좌우가 비었는지 확인
				case 0:// 위가 비엇음
					if (t.getRow() <= 0) {
						isEmpty = false;
					} 
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row-1 && gArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(tArr[i].getRow() == row-1 && tArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(rArr[i].getRow() == row-1 && rArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col-1 && trArr[i].getCol() == row) {
							isEmpty = false;
							break;
						}
					}
					if(isEmpty) {
						tArr[s.getNumOfTiger()] = new Tiger();
						tArr[s.getNumOfTiger()].setLocation(col, row - 1);
						c.getChildren().add(tArr[s.getNumOfTiger()].imageView);
						s.setTiger(s.getNumOfTiger() + 1);
						break;
					}
					else
						isEmpty = true;

				case 1:// right
					if (t.getCol() <= 0) {
						isEmpty = false;	
					}
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row && gArr[i].getCol() == col + 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(tArr[i].getRow() == row && tArr[i].getCol() == col + 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(rArr[i].getRow() == row && rArr[i].getCol() == col + 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col && trArr[i].getCol() == row + 1) {
							isEmpty = false;
							break;
						}
					}
					if(isEmpty) {
						tArr[s.getNumOfTiger()] = new Tiger();
						tArr[s.getNumOfTiger()].setLocation(col - 1, row);
						c.getChildren().add(tArr[s.getNumOfTiger()].imageView);
						s.setTiger(s.getNumOfTiger() + 1);
						break;
					}
					else
						isEmpty = true;
				case 2:// down
					if (t.getRow() >= 9) {
						isEmpty = false;
					} 
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row + 1 && gArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(tArr[i].getRow() == row + 1 && tArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(rArr[i].getRow() == row + 1 && rArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col + 1 && trArr[i].getCol() == row) {
							isEmpty = false;
							break;
						}
					}
					
					if(isEmpty) {
						tArr[s.getNumOfTiger()] = new Tiger();
						tArr[s.getNumOfTiger()].setLocation(col, row + 1);
						c.getChildren().add(tArr[s.getNumOfTiger()].imageView);
						s.setTiger(s.getNumOfTiger() + 1);
						break;
					}
					else
						isEmpty = true;
				case 3:// left
					if (t.getCol() >= 9) {
						isEmpty = false;
					} 
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row && gArr[i].getCol() == col - 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(tArr[i].getRow() == row && tArr[i].getCol() == col - 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(rArr[i].getRow() == row && rArr[i].getCol() == col - 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col && trArr[i].getCol() == row - 1) {
							isEmpty = false;
							break;
						}
					}
					if(isEmpty) {
						tArr[s.getNumOfTiger()] = new Tiger();
						tArr[s.getNumOfTiger()].setLocation(col + 1, row);
						c.getChildren().add(tArr[s.getNumOfTiger()].imageView);
						s.setTiger(s.getNumOfTiger() + 1);
						break;
					}
					else
						isEmpty = true;
				}
			}
		}
	}

	class Rabbit extends CheckerPane {
		private int row;
		private int col;
		private int life = 4;
		private int age = 0;
		Image image = new Image("rabbit.jpg");
		ImageView imageView = new ImageView(image);

		Rabbit() {
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
		}
		public void setLife(int life) {
			this.life = life;
		}
		public int getLife() {
			return life;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getAge() {
			return age;
		}
		public void eatGrass() {
			setLife(4);
		}
		public void setLocation(int col, int row) {
			super.addNode(imageView, col, row);
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}
		public void starve(CheckerPane c, Rabbit[] tArr, Set s) {
			for(int i=0;i<s.getNumOfRabbit();i++) {
				if(tArr[i].getLife()<=0) {
					c.getChildren().remove(tArr[i].imageView);
					for (int j = i; j < s.getNumOfRabbit() - 1; j++) {
						tArr[j] = tArr[j + 1];
					}
					i--;
					s.setRabbit(s.getNumOfRabbit() - 1);	
				}
			}
		}

		public void move(CheckerPane c, Rabbit t) {
			t.setLife(t.getLife()-1);
			t.setAge(t.getAge()+1);
			Random r1 = new Random();
			int whereToGo = r1.nextInt(4);
			switch (whereToGo) {
			case 0:// up
				if (t.getRow() <= 0) {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col, row + 1);
					c.getChildren().add(t.imageView);

				} 
				else {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col, row - 1);
					c.getChildren().add(t.imageView);

				}
				break;

			case 1:// right
				if (t.getCol() >= 9) {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col - 1, row);
					c.getChildren().add(t.imageView);

				} 
				else {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col + 1, row);
					c.getChildren().add(t.imageView);

				}
				break;
			case 2:// down
				if (t.getRow() >= 9) {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col, row - 1);
					c.getChildren().add(t.imageView);

				} 
				else {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col, row + 1);
					c.getChildren().add(t.imageView);

				}
				break;
			case 3:// left
				if (t.getCol() <= 0) {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col + 1, row);
					c.getChildren().add(t.imageView);

				} 
				else {
					int row = t.getRow();
					int col = t.getCol();
					c.getChildren().remove(t.imageView);
					t.setLocation(col - 1, row);
					c.getChildren().add(t.imageView);

				}
				break;
			}
	}
		public void catchObj(CheckerPane c, Hunter h, Rabbit[] r, Rabbit rabbit, Tiger[] t, Grass[] g, Trap[] tr, Set s) {
			int row = rabbit.getRow();
			int col = rabbit.getCol();

			if (h.getRow() == row && h.getCol() == col) {
				c.getChildren().remove(rabbit.imageView);
				for(int i=0;i < s.getNumOfRabbit();i++) {
					if(r[i].getRow() == row && r[i].getCol() == col) {
						for (int j = i; j < s.getNumOfRabbit() - 1; j++) {
							r[j] = r[j + 1];
						}
						//i--;
					}
					
				}
				
				s.setRabbit(s.getNumOfRabbit() - 1);
				h.setScore(h.getScore() + 5);
			}
			
			for (int i = 0; i < s.getNumOfTiger(); i++) {
				if (t[i].getRow() == row && t[i].getCol() == col) {
					c.getChildren().remove(rabbit.imageView);
					for(int k = 0; k < s.getNumOfRabbit();k++) {
						if(r[k].getRow() == row && r[k].getCol() == col) {
							for (int j = k; j < s.getNumOfRabbit() - 1; j++) {
								r[j] = r[j + 1];
							}
						}
					}
					//i--;
					s.setRabbit(s.getNumOfRabbit() - 1);
				}
			}
			for (int i = 0; i < s.getNumOfGrass(); i++) {
				if (g[i].getRow() == row && g[i].getCol() == col) {
					rabbit.eatGrass();
					c.getChildren().remove(g[i].imageView);
					g[i].regrow(c,r, g[i], s);
				}
			}
			for (int i = 0; i < s.getNumOfTrap(); i++) {
				if (tr[i].getRow() == col && tr[i].getCol() == row) {
					c.getChildren().remove(rabbit.imageView);
					for(int k = 0; k < s.getNumOfRabbit();k++) {
						if(r[k].getRow() == row && r[k].getCol() == col) {
							for (int j = k; j < s.getNumOfRabbit() - 1; j++) {
								r[j] = r[j + 1];
							}
						}
					}
					//i--;
					s.setRabbit(s.getNumOfRabbit() - 1);
				}
			}
		}
		public void breed(CheckerPane c, Rabbit t, Rabbit[] tArr, Tiger[] rArr, Grass[] gArr, Trap[] trArr, Set s) {
			if(t.getAge() >= 4 && t.getAge() % 4 == 0) {
				boolean isEmpty = true;
				int row = t.getRow();
				int col = t.getCol();
				Random r1 = new Random();
				//int whereToBreed = 0;
				int whereToBreed = r1.nextInt(4);
				switch (whereToBreed) {//상하좌우가 비었는지 확인
				case 0:// 위가 비엇음
					if (t.getRow() <= 0) {
						isEmpty = false;
					} 
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row-1 && gArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(tArr[i].getRow() == row-1 && tArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(rArr[i].getRow() == row-1 && rArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col-1 && trArr[i].getCol() == row) {
							isEmpty = false;
							break;
						}
					}
					if(isEmpty) {
						tArr[s.getNumOfRabbit()] = new Rabbit();
						tArr[s.getNumOfRabbit()].setLocation(col, row - 1);
						c.getChildren().add(tArr[s.getNumOfRabbit()].imageView);
						s.setRabbit(s.getNumOfRabbit() + 1);
						break;
					}
					else
						isEmpty = true;

				case 1:// right
					if (t.getCol() <= 0) {
						isEmpty = false;	
					}
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row && gArr[i].getCol() == col + 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(tArr[i].getRow() == row && tArr[i].getCol() == col + 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(rArr[i].getRow() == row && rArr[i].getCol() == col + 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col && trArr[i].getCol() == row + 1) {
							isEmpty = false;
							break;
						}
					}
					if(isEmpty) {
						tArr[s.getNumOfRabbit()] = new Rabbit();
						tArr[s.getNumOfRabbit()].setLocation(col - 1, row);
						c.getChildren().add(tArr[s.getNumOfRabbit()].imageView);
						s.setRabbit(s.getNumOfRabbit() + 1);
						break;
					}
					else
						isEmpty = true;
				case 2:// down
					if (t.getRow() >= 9) {
						isEmpty = false;
					} 
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row + 1 && gArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(tArr[i].getRow() == row + 1 && tArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(rArr[i].getRow() == row + 1 && rArr[i].getCol() == col) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col + 1 && trArr[i].getCol() == row) {
							isEmpty = false;
							break;
						}
					}
					
					if(isEmpty) {
						tArr[s.getNumOfRabbit()] = new Rabbit();
						tArr[s.getNumOfRabbit()].setLocation(col, row + 1);
						c.getChildren().add(tArr[s.getNumOfRabbit()].imageView);
						s.setRabbit(s.getNumOfRabbit() + 1);
						break;
					}
					else
						isEmpty = true;
				case 3:// left
					if (t.getCol() >= 9) {
						isEmpty = false;
					} 
					for(int i=0;i < s.getNumOfGrass();i++) {
						if(gArr[i].getRow() == row && gArr[i].getCol() == col - 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfRabbit();i++) {
						if(tArr[i].getRow() == row && tArr[i].getCol() == col - 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTiger();i++) {
						if(rArr[i].getRow() == row && rArr[i].getCol() == col - 1) {
							isEmpty = false;
							break;
						}
					}
					for(int i=0;i < s.getNumOfTrap();i++) {
						if(trArr[i].getRow() == col && trArr[i].getCol() == row - 1) {
							isEmpty = false;
							break;
						}
					}
					if(isEmpty) {
						tArr[s.getNumOfRabbit()] = new Rabbit();
						tArr[s.getNumOfRabbit()].setLocation(col + 1, row);
						c.getChildren().add(tArr[s.getNumOfRabbit()].imageView);
						s.setRabbit(s.getNumOfRabbit() + 1);
						break;
					}
					else
						isEmpty = true;
				}
			}
		}
	}

	class Grass extends CheckerPane {
		private int row;
		private int col;
		Image image = new Image("grass.jpg");
		ImageView imageView = new ImageView(image);

		Grass() {
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
		}

		public void setLocation(int col, int row) {
			super.addNode(imageView, col, row);
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}
		public void regrow(CheckerPane c, Rabbit[] r, Grass g, Set s) {
			Random random = new Random();
			int rowLocation = random.nextInt(10);
			int colLocation = random.nextInt(10);
			//c.getChildren().add(g.imageView);
			for(int i=0;i<s.getNumOfRabbit();i++) {
				if(r[i].getRow() == rowLocation && r[i].getCol() == colLocation) {
					//c.getChildren().remove(g.imageView);
					regrow(c, r, g, s);
					r[i].eatGrass();
				}
				else {
					g.setLocation(colLocation, rowLocation);
					c.getChildren().add(g.imageView);
					break;
				}
			}
		}
	}

	class Trap extends CheckerPane {
		private int row;
		private int col;
		Image image = new Image("trap.jpg");
		ImageView imageView = new ImageView(image);

		Trap() {
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
		}

		public void setLocation(int row, int col) {
			super.addNode(imageView, row, col);
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
