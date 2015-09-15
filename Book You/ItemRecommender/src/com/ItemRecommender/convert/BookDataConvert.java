package com.ItemRecommender.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BookDataConvert {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(
				"data/BX-Books.csv"));

		BufferedWriter bw = new BufferedWriter(new FileWriter("data/books.csv"));

		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(";+", 500);
			bw.write(values[0] + "," + values[1] + "," + values[2] + ","
					+ values[3] + "," + values[4] + values[5] + "," + values[6]
					+ "," + values[7] + "\n");
		}

		br.close();
		bw.close();

	}
}
