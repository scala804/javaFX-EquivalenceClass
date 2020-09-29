package com.autoTest.javaFXEquivalenceClass.extend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class DateTimePickerPopup extends VBox implements Initializable {

	@FXML
	private TextFlow hour;

	private final DateTimePicker parentControl;

	public DateTimePickerPopup(final DateTimePicker parentControl) {
		/*this.hour = parentControl.dateTimeProperty().get().getHour();
		this.minute = parentControl.dateTimeProperty().get().getMinute();
		this.second = parentControl.dateTimeProperty().get().getSecond();*/

		this.parentControl = parentControl;
		/*this.hoursPicker = new HoursPicker(this);
		this.minutesPicker = new MinuteSecondPicker(this);
		this.secondsPicker = new MinuteSecondPicker(this);*/

		// Load FXML
		final FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource(
						"/view/DateTimePickerPopup.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Text text1 = new Text("Big italic red text dfgsdg gsgsg sdgsdghsd sdgdgdg sdgsdgsdgasfgsg gdfgsdghdgd sdgsdgsdg sgsgsg");
		Text text2 = new Text(" little bold blue text");
		hour.getChildren().addAll(text1, text2);
	}

	void setTime(final LocalTime time) {
		/*hour = time.getHour();
		minute = time.getMinute();
		second = time.getSecond();*/
		/*setTimeButtonText();*/
	}

	@FXML
	private void handleOk() {
		System.out.println("2222222");
	}




}
