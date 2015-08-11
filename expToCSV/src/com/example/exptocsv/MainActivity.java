package com.example.exptocsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
//import java.util.ArrayList;
//import java.util.List;
//import android.widget.TextView;
//import java.lang.reflect.Field;
//import android.widget.TextView;

@SuppressLint("ShowToast")

public class MainActivity extends Activity {
	
	Pessoa p;
	String _nome;
	int _idade;
	int _cpf;
	
	List<Pessoa> lista = new ArrayList<Pessoa>();
	
	public void Salvar(View v){
		
		EditText textNome = (EditText) findViewById(R.id.editTextNome);
		EditText textIdade = (EditText) findViewById(R.id.editTextIdade);
		EditText textCPF = (EditText) findViewById(R.id.editTextCPF);
		
		_nome = textNome.getText().toString();
		_idade = Integer.parseInt(textIdade.getText().toString());
		_cpf = Integer.parseInt(textCPF.getText().toString());
		
		if(_nome.isEmpty() && _idade > 0 && _idade < 150 && _cpf != 11)
			
			Toast.makeText(getApplicationContext(), 
					"Todos os campos devem ser preenchidos corretamente", Toast.LENGTH_LONG).show();
		
		else{
			
			p = new Pessoa();
			p.setNome(_nome);
			p.setIdade(_idade);
			p.setCpf(_cpf);
			
			lista.add(p);
			
			Toast.makeText(getApplicationContext(), 
					"Pessoa adicionada com sucesso no arquivo!", Toast.LENGTH_LONG).show();
			
		}
	}
	
	public void onClick(View view){

		Pessoa pessoa = new Pessoa();
		
		String padraoString = pessoa.toString() + "\n";

		File root = Environment.getExternalStorageDirectory();
		
		if (root.canRead()) {

			File dir = new File(root.getAbsolutePath());
			dir.mkdirs();
			Date date = new Date();
		
			// gera o arquivo .csv
			@SuppressWarnings("deprecation")
			File file = new File(dir, "Report_" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + ".csv");

			try {
				
				FileOutputStream out = new FileOutputStream(file);
				
				out.write(padraoString.getBytes());
				
				for(Pessoa p : lista){
					String combinedString = p.toStringAt() + "\n";
					out.write(combinedString.getBytes());
				}
			
				out.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Toast.makeText(getApplicationContext(), "Exportado para /Phone Storage", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

}
