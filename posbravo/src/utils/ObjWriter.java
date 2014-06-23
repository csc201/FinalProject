package utils;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;

public class ObjWriter<T> {
	private Date date;
	T obj;
	private String fileName;

	public ObjWriter() {

	}

	public ObjWriter(T obj, String fileName) {
		date = new Date();
		this.obj = obj;
		this.fileName = fileName;
	}

	public void writeObjFile() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(this.fileName));
		output.writeObject(this.obj);
		System.out.println("Save file to " + fileName);

	}

	public T readObjFile(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				fileName));
		this.obj = (T) input.readObject();
		System.out.println("Complete reading " + fileName);
		return this.obj;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}