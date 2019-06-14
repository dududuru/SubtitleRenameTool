package frame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

	public static String iconPath = "icon/����.jpg";
	public void readIni() {
		File ini = new File("config/setting.ini");
		if (ini.exists()) {
			FileReader fileReader;
			try {
				fileReader = new FileReader(ini);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				iconPath = bufferedReader.readLine();
				bufferedReader.close();
				fileReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeIni() {
		File ini = new File("config/setting.ini");
		if (ini.exists()) {
			ini.delete();
		}
		try {
			if (!iconPath.equals("icon/����.jpg")) {
				File drc = ini.getParentFile();
				if (!drc.exists()) {
					drc.mkdirs();
					System.out.println(drc + "�����ɹ�");
				}
				ini.createNewFile();
				System.out.println("ini�����ɹ�" + ini);
				FileWriter fileWriter = new FileWriter(ini);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(iconPath);
				bufferedWriter.newLine();
				bufferedWriter.write("��һ��Ϊ����ͼ����·����icon/����.jpgΪĬ�ϱ���ͼ");
				bufferedWriter.close();
				fileWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
