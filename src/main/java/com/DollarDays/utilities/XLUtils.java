package com.DollarDays.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	

	public static int getRowCount(String xlfileName,String xlsheet) throws IOException 
	{
		String xlfilePath= System.getProperty("user.dir")+"/src/test/resources/testData/" + xlfileName;
		fi=new FileInputStream(xlfilePath);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	
	
	public static int getCellCount(String xlfileName,String xlsheet,int rownum) throws IOException
	{
		String xlfilePath= System.getProperty("user.dir")+"/src/test/resources/testData/" + xlfileName;
		fi=new FileInputStream(xlfilePath);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	
	public static String getCellData(String xlfileName,String xlsheet,int rownum,int colnum) throws IOException
	{
		String xlfilePath= System.getProperty("user.dir")+"/src/test/resources/testData/" + xlfileName;
		fi=new FileInputStream(xlfilePath);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	
	
	public static void setCellData(String xlfileName,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		String xlfilePath= System.getProperty("user.dir")+"/src/test/resources/testData/" + xlfileName;
		fi=new FileInputStream(xlfilePath);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfilePath);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
	
	//Get the entire data(except the header) into a 2 dimension array from a specified sheet of an excel
	public static String[][] getSheetData(String xlfileName,String xlsheet) throws IOException
	{
		String xlfilePath= System.getProperty("user.dir")+"/src/test/resources/testData/" + xlfileName;
		fi=new FileInputStream(xlfilePath);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		String[][] emptyArray = {};
		try 
		{
			int totalrows=ws.getLastRowNum();
			int totalcols=ws.getRow(1).getLastCellNum();		
			String data[][]=new String[totalrows][totalcols];
			for(int i=1;i<=totalrows;i++)   //i=0 row is the header row, so not fetching the data in it.
			{
				for(int j=0;j<totalcols;j++)
				{
					cell= ws.getRow(i).getCell(j);
					DataFormatter formatter = new DataFormatter();
		            String cellData = formatter.formatCellValue(cell);
		            data[i-1][j]=cellData;					
				}
					
			}	
					
			wb.close();
			fi.close();
			return data;
		}
		catch(Exception e)
		{
			return emptyArray;
		}
	}
	
	//Get the entire data of a specified column(except the header cells) from a specified sheet into a single dimension array.
	public static String[] getColumnData(String xlfileName,String xlsheet,int colnum) throws IOException
	{
		String xlfilePath= System.getProperty("user.dir")+"/src/test/resources/testData/" + xlfileName;
		fi=new FileInputStream(xlfilePath);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		String[] emptyArray = {};
		try 
		{
			int totalrows=ws.getLastRowNum();
			String data[]=new String[totalrows];
			for(int i=1;i<=totalrows;i++)   //i=0 row is the header row, so not fetching the data in it.
			{
					cell= ws.getRow(i).getCell(colnum);
					DataFormatter formatter = new DataFormatter();
		            String cellData = formatter.formatCellValue(cell);
		            data[i-1]=cellData;														
			}			
			wb.close();
			fi.close();
			return data;
		}
		catch(Exception e)
		{
			return emptyArray;
		}
	}
	
	//Get the entire data of a specified Row from a specified sheet into a single dimension array.
	public static String[] getRowData(String xlfileName, String xlsheet, int rownum) throws IOException
	{
		
		String xlfilePath= System.getProperty("user.dir")+"/src/test/resources/testData/" + xlfileName;
		fi=new FileInputStream(xlfilePath);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		String[] emptyArray = {};
		try 
		{
		int totalcols=ws.getRow(0).getLastCellNum();
		String[] data=new String[totalcols];
		for(int j=0;j<totalcols;j++)
		{
			cell=ws.getRow(rownum).getCell(j);
			DataFormatter  formatter=new DataFormatter();
			data[j]=formatter.formatCellValue(cell);
		}
		wb.close();
		fi.close();
		return data;
	}
	catch(Exception e)
	{
		return emptyArray;
	}
}
}
