package A_Star;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;



//import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;


public class Arquivo {
	
	private String nome_arquivo;
	private HSSFWorkbook excel;
	private HSSFSheet planilha;
	private HSSFRow linha;
	

	public Arquivo (String nome_arquivo){
		
		this.nome_arquivo = nome_arquivo;
		this.excel = new HSSFWorkbook();   					//Nome do arquivo do excel
		this.planilha = excel.createSheet("Dados");      //Nome da planilha

	}	

	public void setCelula (double valor,int num_linha, int coluna){
		
		linha = planilha.getRow(num_linha);
		if(linha == null){
			linha = planilha.createRow(num_linha);
		}
		if(linha.getCell(coluna) == null){
			linha.createCell(coluna).setCellValue(valor);
		}else linha.getCell(coluna).setCellValue(valor);
	}
	
	//Escrever em arquivo a planilha
	public void Escrever () throws IOException {
		
		FileOutputStream fluxo = new FileOutputStream(nome_arquivo);
		excel.write(fluxo);
		
	}

}