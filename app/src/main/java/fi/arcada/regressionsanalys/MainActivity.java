package fi.arcada.regressionsanalys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Två datamängder med längd och skonummer för 26 olika personer
    // hänger ihop på så vis att xData[0] och yData[0] är skonummer och längd  för den första personen, osv.
    // Observera att de är primitiva arrays, inte ArrayLists, så de behandlas lite annorlunda.
    double[] xData = { 47,  42,  43,  42,  41,  48,  46,  44,  42,  43,  39,  43,  39,  42,  44,  45,  43,  44,  45,  42,  43,  32,  48,  43,  45,  45};
    double[] yData = { 194, 188, 181, 177, 182, 197, 179, 176, 177, 188, 164, 171, 170, 180, 171, 185, 179, 182, 180, 178, 178, 148, 197, 183, 179, 198};

    // Deklarera yValue för längd, Denna variabel ska sedan få ett värde som hämtas från en EditText-box i appens GUI
    double yValue;

    // Deklarera övriga variabler och objekt du behöver, t.ex. TextViews osv.

    TextView txtShoesize, txtCorrelation;
    EditText etxHeight;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShoesize = findViewById(R.id.txtShoesize);
        txtCorrelation = findViewById(R.id.txtCorrelation);
        etxHeight = findViewById(R.id.etxHeight);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(this::getEstimate);
    }

    public void getEstimate(View view) {
        yValue = Double.parseDouble(etxHeight.getText().toString());

        RegressionLine regressionLine = new RegressionLine(xData, yData);
        double correlationCoefficient = regressionLine.getCorrelationCoefficient();
        double shoesize = regressionLine.getX(yValue);
        String correlationGrade = regressionLine.getCorrelationGrade();

        txtShoesize.setText(String.format("Skostorlek: %.2f", shoesize));
        txtCorrelation.setText(String.format("Korrelationsefficient: %.2f (%s)", correlationCoefficient, correlationGrade));

    }

}
