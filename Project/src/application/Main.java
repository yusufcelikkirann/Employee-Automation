package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


/*In this JavaFX application user is able to add employee's,
 * update there are,
 * and keep them in txt file
 * but the txt file is removing when user start this application second time.*/

public class Main extends Application {

	private File myEmployeeFile = new File("employee.txt"); //Define a txt file.

	private int index = 0; //This is employeArray's index and employee's social security number.

	private Employee[] employeeArray = new Employee[100]; //This is an array for employees

	
	//Define UI elements.
	private BorderPane borderpane = new BorderPane();
	private BorderPane pane = new BorderPane();
	private GridPane gridpane = new GridPane();
	private HBox hBoxButton = new HBox(5);
	private HBox hBoxChoice = new HBox(2);

	private Scene scene = new Scene(borderpane,800,400);
	private Scene sceneInfo = new Scene(pane, 300, 200);

	private TextField firstNameTF = new TextField();
	private TextField lastNameTF = new TextField();
	private TextField ssnTF = new TextField();
	private TextField operationSsnTF = new TextField();
	private TextField grossSalesTF = new TextField();
	private TextField commisionRateTF = new TextField();
	private TextField baseSalaryTF = new TextField();
	private TextField weeklySalaryTF = new TextField();
	private TextField wageTF = new TextField();
	private TextField hoursTF = new TextField();

	private	Text firstName = new Text("First Name");
	private	Text lastName = new Text("Last Name");
	private	Text ssn = new Text("SSN");
	private	Text operationSsn = new Text("Search/Update SSN");
	private	Text salary = new Text("Salary");
	private	Text grossSales = new Text("Gross Sales");
	private	Text commisionRate = new Text("Commision Rate");
	private	Text baseSalary = new Text("Base Salary");
	private	Text weeklySalary = new Text("Weekly Salary");
	private	Text wage = new Text("Wage");
	private	Text hours = new Text("Hours");
	private	Text employee = new Text("Choose Employee Type");
	private	Text calculatedSalary = new Text("");
	private Text developers = new Text("Developed By:\n" + "Yusuf Çelikkıran");

	private	Button buttonAdd = new Button("Add");
	private	Button buttonSearchSsn = new Button("Search by SSN");
	private	Button buttonUpdateSsn = new Button("Update by SSN");
	private	Button buttonClean = new Button("Clean textFields");
	private Button buttonInfo = new Button("Info");


	@SuppressWarnings("rawtypes")
	private ChoiceBox choiceEmployee = new ChoiceBox();

	//This function for clear button.
	//If you had a press the clear button textFields' values are cleaning with this function.
	private void cleanTextFields() {
		firstNameTF.clear();
		lastNameTF.clear();
		ssnTF.clear();
		operationSsnTF.clear();
		grossSalesTF.clear();
		commisionRateTF.clear();
		baseSalaryTF.clear();
		weeklySalaryTF.clear();
		wageTF.clear();
		hoursTF.clear();
		calculatedSalary.setText("");
		choiceEmployee.getSelectionModel().clearSelection();
		on();

	}

	//Somewhere we must disable textFields and somewhere we must enable these are.
	//This function for enable textFileds.
	private void on() {
		grossSalesTF.setDisable(false);
		commisionRateTF.setDisable(false);
		baseSalaryTF.setDisable(false);
		wageTF.setDisable(false);
		hoursTF.setDisable(false);
		firstNameTF.setDisable(false);
		lastNameTF.setDisable(false);
		ssnTF.setDisable(false);
		operationSsnTF.setDisable(false);
		weeklySalaryTF.setDisable(false);
		choiceEmployee.setDisable(false);
	}

	//Somewhere we must disable textFields.
	//This function for disable textFileds.
	private void off() {
		grossSalesTF.setDisable(true);
		commisionRateTF.setDisable(true);
		baseSalaryTF.setDisable(true);
		wageTF.setDisable(true);
		hoursTF.setDisable(true);
		firstNameTF.setDisable(true);
		lastNameTF.setDisable(true);
		ssnTF.setDisable(true);
		operationSsnTF.setDisable(true);
		weeklySalaryTF.setDisable(true);
	}

	//This function enable or disable textFileds according to employee type which is user want to add.
	private void choice () {

		int selectedIndex = choiceEmployee.getSelectionModel().getSelectedIndex();

		switch (selectedIndex) {
		case 0:
			off();
			firstNameTF.setDisable(false);
			lastNameTF.setDisable(false);
			wageTF.setDisable(false);
			hoursTF.setDisable(false);
			operationSsnTF.setDisable(false);
			break;
		case 1:
			off();
			firstNameTF.setDisable(false);
			lastNameTF.setDisable(false);
			weeklySalaryTF.setDisable(false);
			operationSsnTF.setDisable(false);
			break;
		case 2:
			off();
			firstNameTF.setDisable(false);
			lastNameTF.setDisable(false);
			operationSsnTF.setDisable(false);
			baseSalaryTF.setDisable(false);
			grossSalesTF.setDisable(false);
			commisionRateTF.setDisable(false);
			break;
		case 3:
			off();
			firstNameTF.setDisable(false);
			lastNameTF.setDisable(false);	
			operationSsnTF.setDisable(false);
			grossSalesTF.setDisable(false);
			commisionRateTF.setDisable(false);
			break;
		case 4:
			off();
			break;
		}
	}

	//This function add employees to employeeArray and calculate theirs salary.
	private void addAndCalculateSalary () throws FileNotFoundException {

		int selectedIndex = choiceEmployee.getSelectionModel().getSelectedIndex();
		int ssn = index;

		String firstName = firstNameTF.getText();
		String lastName = lastNameTF.getText();
		String ssnString = String.valueOf(ssn);
		String wage = wageTF.getText();
		String grossSales = grossSalesTF.getText();
		String commisionRate = commisionRateTF.getText();
		String baseSalary = baseSalaryTF.getText();
		String weeklySalary = weeklySalaryTF.getText();
		String hours = hoursTF.getText();


		try {
			switch(selectedIndex) {
			case 0:
				double waged = Double.valueOf(wage);
				double hoursd = Double.valueOf(hours);

				HourlyEmployee hourlyEmployee = new HourlyEmployee(waged, hoursd, firstName, lastName, ssnString);

				employeeArray[index] = hourlyEmployee;

				double hourlySalary = hourlyEmployee.getPaymentAmount();
				String salaryString = Double.toString(hourlySalary);

				calculatedSalary.setText(salaryString);
				ssnTF.setText(ssnString);
				index++;
				break;
			case 1:
				double weeklySalaryd = Double.valueOf(weeklySalary);
				SalariedEmployee salariedEmployee = new SalariedEmployee(weeklySalaryd, firstName, lastName, ssnString);

				double salariedSalary = salariedEmployee.getPaymentAmount();
				String salariedSalaryString = Double.toString(salariedSalary);

				calculatedSalary.setText(salariedSalaryString);
				employeeArray[index] = salariedEmployee;
				ssnTF.setText(ssnString);
				index++;
				break;
			case 2:
				double baseSalaryd = Double.valueOf(baseSalary);
				double grossSalesd = Double.valueOf(grossSales);
				double commisionRated = Double.valueOf(commisionRate);

				BasePlusCommisionEmployee basePlusCommisionEmployee = new BasePlusCommisionEmployee(commisionRated, grossSalesd, baseSalaryd,firstName, lastName, ssnString);
				double baseCommisionSalary = basePlusCommisionEmployee.getPaymentAmount();
				String baseCommisionSalaryString = Double.toString(baseCommisionSalary);

				calculatedSalary.setText(baseCommisionSalaryString);
				employeeArray[index] = basePlusCommisionEmployee;
				ssnTF.setText(ssnString);
				index++;
				break;
			case 3:
				double grossSalesd2 = Double.valueOf(grossSales);
				double commisionRated2 = Double.valueOf(commisionRate);

				CommisionEmployee commisionEmployee = new CommisionEmployee(commisionRated2, grossSalesd2,firstName, lastName, ssnString);
				double commisionSalary = commisionEmployee.getPaymentAmount();
				String commisionSalaryString = Double.toString(commisionSalary);

				calculatedSalary.setText(commisionSalaryString);
				employeeArray[index] = commisionEmployee;
				ssnTF.setText(ssnString);
				index++;
				break;
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//This function run when user press Info button.
	//This function for show to developers.
	private void info() {

		Stage stageInfo = new Stage();
		stageInfo.setTitle("Info");
		stageInfo.setScene(sceneInfo);
		stageInfo.show();

		pane.setCenter(developers);
	}

	//User can search any employee and see theirs informations.
	//These informations come from employeeArray.
	private void searchBySsn () {

		String enteredSsn = operationSsnTF.getText();
		int enteredSsnInteger = Integer.valueOf(enteredSsn);

		for (int counter = 0 ; counter < index ; counter++) {

			if (counter == enteredSsnInteger) {

				firstNameTF.setText(employeeArray[enteredSsnInteger].getFirstName());
				lastNameTF.setText(employeeArray[enteredSsnInteger].getLastName());
				ssnTF.setText(employeeArray[enteredSsnInteger].getSocialSecurityNumber());

				if (employeeArray[enteredSsnInteger] instanceof HourlyEmployee) {

					HourlyEmployee hourlyEmployee = (HourlyEmployee) employeeArray[enteredSsnInteger];

					String wageString = Double.toString(hourlyEmployee.getWage());
					String hoursString = Double.toString(hourlyEmployee.getHours());

					wageTF.setText(wageString);
					hoursTF.setText(hoursString);
					calculatedSalary.setText(Double.toString(hourlyEmployee.getPaymentAmount()));
					choiceEmployee.getSelectionModel().select(0);
				} else if (employeeArray[enteredSsnInteger] instanceof SalariedEmployee) {
					SalariedEmployee salariedEmployee = (SalariedEmployee) employeeArray[enteredSsnInteger];

					weeklySalaryTF.setText(Double.toString(salariedEmployee.getPaymentAmount()));
					calculatedSalary.setText(Double.toString(salariedEmployee.getPaymentAmount()));
					choiceEmployee.getSelectionModel().select(1);
				} else if (employeeArray[enteredSsnInteger] instanceof CommisionEmployee) {
					CommisionEmployee commisionEmployee = (CommisionEmployee) employeeArray[enteredSsnInteger];

					grossSalesTF.setText(Double.toString(commisionEmployee.getGrossSales()));
					commisionRateTF.setText(Double.toString(commisionEmployee.getCommisionRate()));
					calculatedSalary.setText(Double.toString(commisionEmployee.getPaymentAmount()));
					choiceEmployee.getSelectionModel().select(3);
					if (employeeArray[enteredSsnInteger] instanceof BasePlusCommisionEmployee) {
						BasePlusCommisionEmployee basePlusCommisionEmployee = (BasePlusCommisionEmployee) employeeArray[enteredSsnInteger];

						baseSalaryTF.setText(Double.toString(basePlusCommisionEmployee.getBaseSalary()));
						choiceEmployee.getSelectionModel().select(2);
					}
				}
			}		
		}
		choiceEmployee.setDisable(true);
	}

	//User is able to update any employee's informations with this function.
	private void update() {

		String enteredSsn = operationSsnTF.getText();
		int enteredSsnInteger = Integer.valueOf(enteredSsn);

		int selectedIndex = choiceEmployee.getSelectionModel().getSelectedIndex();

		String firstName = firstNameTF.getText();
		String lastName = lastNameTF.getText();
		String ssnString = enteredSsn;
		String wage = wageTF.getText();
		String grossSales = grossSalesTF.getText();
		String commisionRate = commisionRateTF.getText();
		String baseSalary = baseSalaryTF.getText();
		String weeklySalary = weeklySalaryTF.getText();
		String hours = hoursTF.getText();


		try {
			switch(selectedIndex) {
			case 0:
				double waged = Double.valueOf(wage);
				double hoursd = Double.valueOf(hours);

				HourlyEmployee hourlyEmployee = new HourlyEmployee(waged, hoursd, firstName, lastName, ssnString);

				employeeArray[enteredSsnInteger] = hourlyEmployee;

				double hourlySalary = hourlyEmployee.getPaymentAmount();
				String salaryString = Double.toString(hourlySalary);

				calculatedSalary.setText(salaryString);
				ssnTF.setText(ssnString);
				break;
			case 1:
				double weeklySalaryd = Double.valueOf(weeklySalary);

				SalariedEmployee salariedEmployee = new SalariedEmployee(weeklySalaryd, firstName, lastName, ssnString);

				double salariedSalary = salariedEmployee.getPaymentAmount();
				String salariedSalaryString = Double.toString(salariedSalary);

				calculatedSalary.setText(salariedSalaryString);
				employeeArray[enteredSsnInteger] = salariedEmployee;
				ssnTF.setText(ssnString);
				break;
			case 2:
				double baseSalaryd = Double.valueOf(baseSalary);
				double grossSalesd = Double.valueOf(grossSales);
				double commisionRated = Double.valueOf(commisionRate);

				BasePlusCommisionEmployee basePlusCommisionEmployee = new BasePlusCommisionEmployee(baseSalaryd, grossSalesd, commisionRated,firstName, lastName, ssnString);

				double baseCommisionSalary = basePlusCommisionEmployee.getPaymentAmount();
				String baseCommisionSalaryString = Double.toString(baseCommisionSalary);

				calculatedSalary.setText(baseCommisionSalaryString);
				employeeArray[enteredSsnInteger] = basePlusCommisionEmployee;
				ssnTF.setText(ssnString);
				break;
			case 3:
				double grossSalesd2 = Double.valueOf(grossSales);
				double commisionRated2 = Double.valueOf(commisionRate);

				CommisionEmployee commisionEmployee = new CommisionEmployee(grossSalesd2, commisionRated2,firstName, lastName, ssnString);

				double commisionSalary = commisionEmployee.getPaymentAmount();
				String commisionSalaryString = Double.toString(commisionSalary);

				calculatedSalary.setText(commisionSalaryString);
				employeeArray[enteredSsnInteger] = commisionEmployee;
				ssnTF.setText(ssnString);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (myEmployeeFile.delete()) {
			@SuppressWarnings("unused")
			File myEmployeeFile = new File("employee.txt");
			printArrayToFile();
		}	
	}

	//This function print to file employees from employeeArray.
	private void printArrayToFile() {
		try {
			FileWriter writer = new FileWriter("employee.txt");
			for (int counter = 0 ; counter < index ; counter++) {
				writer.write(employeeArray[counter] + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {

		try {
			
			//User when click any button run these codes.
			buttonClean.setOnAction(e -> cleanTextFields());
			choiceEmployee.setOnAction(e -> choice());
			buttonInfo.setOnAction(e -> info());
			buttonSearchSsn.setOnAction(e -> searchBySsn());
			buttonUpdateSsn.setOnAction(e -> update());
			buttonAdd.setOnAction(e -> {

				try {
					addAndCalculateSalary();
					printArrayToFile();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			});

			//We added choice box's values.
			choiceEmployee.getItems().add(0, "Hourly Employee");
			choiceEmployee.getItems().add(1, "Salaried Employee");
			choiceEmployee.getItems().add(2, "Base Plus Commision Employee");
			choiceEmployee.getItems().add(3, "Commision Employee");
			choiceEmployee.getItems().add(4, "None");
			choiceEmployee.setPrefWidth(150);

			//Gridpane's gridLines are not visibility with this code.
			gridpane.setGridLinesVisible(false);

			//We added UI elements in gridpane and these elements' position are determined to center.
			//There are 20px vertical and horizontal gap between UI elements.
			gridpane.addRow(0, firstName, firstNameTF, grossSales, grossSalesTF);
			gridpane.addRow(1, lastName, lastNameTF, commisionRate, commisionRateTF);
			gridpane.addRow(2, ssn, ssnTF, baseSalary, baseSalaryTF);
			gridpane.addRow(3, operationSsn, operationSsnTF, weeklySalary, weeklySalaryTF);
			gridpane.addRow(4, wage, wageTF, hours, hoursTF);
			gridpane.addRow(5, salary, calculatedSalary);
			gridpane.setAlignment(Pos.CENTER);
			gridpane.setVgap(20);
			gridpane.setHgap(20);

			//We added UI element to Hbox.
			//These elemtents' position is center
			hBoxButton.getChildren().addAll(buttonAdd, buttonSearchSsn, buttonUpdateSsn, buttonClean, buttonInfo);
			hBoxButton.setAlignment(Pos.CENTER);
			hBoxChoice.getChildren().addAll(employee, choiceEmployee);
			hBoxChoice.setAlignment(Pos.CENTER);

			borderpane.setCenter(gridpane);
			borderpane.setBottom(hBoxButton);
			borderpane.setTop(hBoxChoice);

			primaryStage.setTitle("Employee Automation");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
