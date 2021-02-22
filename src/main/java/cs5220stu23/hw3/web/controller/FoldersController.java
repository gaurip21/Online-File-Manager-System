package cs5220stu23.hw3.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cs5220stu23.hw3.model.File;
import cs5220stu23.hw3.model.Folder;
import cs5220stu23.hw3.model.FolderDto;
import cs5220stu23.hw3.model.dao.FolderDao;


@CrossOrigin
@RestController
@RequestMapping("/folders")
public class FoldersController {

    @Autowired
    private FolderDao folderDao;
    
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public @ResponseBody List<FolderDto> list()
    {
    	List<Folder> folders = folderDao.getFolders();
        List<FolderDto> dtos = new ArrayList<FolderDto>();
        
        for(Folder f : folders)
        	dtos.add(new FolderDto(f));
        
        return dtos;
    }
    
    @GetMapping("/{id}")
    public @ResponseBody List<FolderDto> get(@PathVariable Integer id)
    {
    	List<Folder> folders = folderDao.getFolders(id);
        List<FolderDto> dtos = new ArrayList<FolderDto>();
        
       for(Folder f : folders)
        	dtos.add(new FolderDto(f));
        
        Folder folder = folderDao.getFolder(id);
        
        return dtos;
    	
    }
    
    @GetMapping("folder/{id}")
    public @ResponseBody String getFolder(@PathVariable Integer id)
    {
    	
       Folder folder = folderDao.getFolder(id);
       
       String name = folder.getName();
        
        return name;
    	
    }
    
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FolderDto add(@RequestBody FolderDto dto,@PathVariable Integer id)
    {
    	
    	Folder f = new Folder();
    	f.setName(dto.getName());
    	f.setLocation(dto.getLocation());
    	Folder parentfolder = folderDao.getFolder(id);
    	f.setParentFolder(parentfolder);
    	f = folderDao.saveFolder(f);
    	
    	return new FolderDto(f);
    	
    	
    }
    
   
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderDto add(@RequestBody FolderDto dto)
    {
    	
    	Folder f = new Folder();
    	f.setName(dto.getName());
    	f.setLocation(dto.getLocation());
    	if(dto.getParentFolderId() != null)
    	{
    		Folder parentfolder = folderDao.getFolder(dto.getParentFolderId());
    		
    		f.setParentFolder(parentfolder);
    		
    	}
    
    	f = folderDao.saveFolder(f);
    	
    	return new FolderDto(f);
    	
    	
    }
    
    
    @GetMapping("/delete/{id}")
    public @ResponseBody String delete(@PathVariable Integer id)
    {
    	Folder f = folderDao.getFolder(id);
    	
    	try {
    	
    		int count = folderDao.deleteFolder(f);
	    	
	    	List<File> files = entityManager.createQuery( " from File where folder_id =" + id , File.class )
	    			.getResultList();
	    	
	    	if(!files.isEmpty())
	    	{
	    	
		    	for(File f1 : files)
		    	{
		    		entityManager.remove(f1);
		    		
		    		return "File successfully deleted.";
				
		    	}
	    	}
	    	
	    	return count+" folders deleted.";
    	}
    	
    	catch(Exception e)
    	{
    		return e.toString();
    	}
    		
    	
    }
    

}
