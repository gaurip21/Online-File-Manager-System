package cs5220stu23.hw3.model.dao;

import java.util.List;
import cs5220stu23.hw3.model.Folder;


public interface FolderDao {

	List<Folder> getFolders();
	
	List<Folder> getFolders( Integer id );
	
	Folder getFolder(Integer id);

	Folder saveFolder( Folder folder );

	int deleteFolder(Folder f);
	
}
