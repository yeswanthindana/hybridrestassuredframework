package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtility {
	


	    public  FileInputStream fi;
	    public  FileOutputStream fo;
	    public  XSSFWorkbook wb;
	    public  XSSFSheet sh;
	    public  XSSFRow row;
	    public  XSSFCell cell;
	    public  CellStyle style;
	    String path;
	    
	    public excelUtility(String path) {
	    	this.path = path;
	    }

	    public int getRowCount(String sheet) throws IOException {
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        sh = wb.getSheet(sheet);
	        int rowcount = sh.getLastRowNum();
	        wb.close();
	        fi.close();
	        return rowcount;
	    }

	    public int getCellCount(String sheet,int rownum) throws IOException {
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        sh = wb.getSheet(sheet);
	        row = sh.getRow(rownum);
	        int cellcount = row.getLastCellNum();
	        wb.close();
	        fi.close();
	        return cellcount;
	    }

	    public String getCellData(String sheet,int rownum,int cellnum) throws IOException {
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        sh = wb.getSheet(sheet);
	        row = sh.getRow(rownum);
	        cell = row.getCell(cellnum);
	        //return cell.toString();

	        String data;
	        try {
	            //data=cell.toString();
	            DataFormatter formatter= new DataFormatter();
	            data = formatter.formatCellValue(cell);
	            //Returns the formatted value of a cell as a String regardless
	            // of cell type

	        } catch (Exception e){
	            data = "";
	            // if cell is having no data then it assigns null and throws exception
	            // therefore we are assigning to empty by above
	        }

	        wb.close();
	        fi.close();
	        return data;
	    }

	    public void setCellData(String sheet,int rownum,int cellnum,String data) throws IOException {
	        //Both read and write is done here
	    	File xlfile = new File(path);
	    	if(!xlfile.exists()) //if file doesnt exists create new file
	    	{
	    		wb = new XSSFWorkbook();
	    		fo = new FileOutputStream(path);
	    		wb.write(fo);
	    	}
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        
	        if(wb.getSheetIndex(sheet)==-1) //if sheet not exists cteate sheet
	        	wb.createSheet(sheet);
	        sh = wb.getSheet(sheet);
	        	
	        if(sh.getRow(rownum)==null)  //i9f row not exists create new row
	        	sh.createRow(rownum);
	        row= sh.getRow(rownum);
	        		
	      
	        cell =row.createCell(cellnum);
	        cell.setCellValue(data);
	        fo = new FileOutputStream(path);
	        wb.write(fo);
	        wb.close();
	        fi.close();
	        fo.close();

	    }

	    public void fllGreencolour(String sheet,int rownum,int cellnum) throws IOException {
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        sh = wb.getSheet(sheet);
	        row = sh.getRow(rownum);
	        cell = row.getCell(cellnum);

	        style = wb.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	        cell.setCellStyle(style);
	        fo = new FileOutputStream(path);
	        wb.write(fo);
	        wb.close();
	        fi.close();
	        fo.close();



	    }

	    public void fillRedColour(String sheet,int rownum, int cellnum) throws IOException {
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        sh = wb.getSheet(sheet);
	        row = sh.getRow(rownum);
	        cell = row.getCell(cellnum);

	        style = wb.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.RED.getIndex());
	       style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	        cell.setCellStyle(style);
	        fo = new FileOutputStream(path);
	        wb.write(fo);
	        wb.close();
	        fi.close();
	        fo.close();


	    }
	}

