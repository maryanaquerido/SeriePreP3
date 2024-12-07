package br.edu.fateczl.serieprep3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNum;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvTitulo2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNum = findViewById(R.id.etNum);
        etNum.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> calc());

    }

    private void calc(){
        int num;
        //Pegar valor digitado e tratar entradas inválidas
        try {
            num = Integer.parseInt(etNum.getText().toString());
        } catch (NumberFormatException e) {
            tvResultado.setText("Digite um número válido!");
            return;
        }

        //Validar se N está no intervalo (N>0 e N<20)
        if (!validaNum(num)) {
            etNum.requestFocus();
            return;
        }

        //Calcular a série e exibir o resultado
        double resp = serie(num);
        tvResultado.setText(Double.toString(resp));
        limpaCampos();

    }

    private boolean validaNum(int n) {
        if (n <= 0) {
            tvResultado.setText("N precisa ser maior que 0! Digite novamente.");
            return false;
        } else if (n >= 20) {
            tvResultado.setText("N precisa ser menor que 20! Digite novamente.");
            return false;
        }
        return true;
    }

    private double serie(int n) {
        if (n == 1){
            return 1;
        } else {
            return (1.0/n + serie (n-1));
        }
    }

    private void limpaCampos(){
        etNum.setText("");
    }
}