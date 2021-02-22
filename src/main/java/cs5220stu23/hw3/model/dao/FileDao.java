package cs5220stu23.hw3.model.dao;

import java.util.List;

import cs5220stu23.hw3.model.File;
import cs5220stu23.hw3.model.Folder;

public interface FileDao {

	List<File> getFiles();
	
	List<File> getFile( Integer id );

	File saveFile( File file );	
	
	int fileVersion(String filename, int parentid);

}
