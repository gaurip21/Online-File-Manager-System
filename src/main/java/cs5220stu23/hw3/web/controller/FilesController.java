package cs5220stu23.hw3.web.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cs5220stu23.hw3.model.File;
import cs5220stu23.hw3.model.FileDto;
import cs5220stu23.hw3.model.Folder;
import cs5220stu23.hw3.model.dao.FileDao;
import cs5220stu23.hw3.model.dao.FolderDao;

@CrossOrigin
@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FileDao fileDao;
    
    private FolderDao folderDao;
    
   
    
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public @ResponseBody List<FileDto> list(ModelMap model)
    {
    	List<File> files = fileDao.getFiles();
        List<FileDto> dtos = new ArrayList<FileDto>();
        
        for(File f : files)
        	dtos.add(new FileDto(f));
        
        return dtos;
    }
    
    @GetMapping("/{id}")
    public @ResponseBody List<FileDto> get(@PathVariable Integer id)
    {
    	List<File> files = fileDao.getFile(id);
        List<FileDto> dtos = new ArrayList<FileDto>();
        
        for(File f : files)
        	dtos.add(new FileDto(f));
        
        return dtos;
    	
    }
    
    @PostMapping(value={"/upload", "/upload/{id}"})
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@PathVariable(required = false) Integer id, @RequestParam("name") String name,
            @RequestParam("data") MultipartFile file) throws IOException
    {
    
    	try
    	{
    	
	    	if (!file.isEmpty()) {
	    		
	    		File f = new File();
	    		
	    		f.setName(name);
	    		
	    		f.setData(file.getBytes());
	    		
	    		f.setType(file.getContentType());
	    		
	    		f.setSize(file.getSize());
	    		
	    		int parentfolderid = 0;
	    		
	    		if(id != null)
	    	    {
	    	    		
	    			Folder parentfolder = entityManager.find( Folder.class, id );
	    	    		
	    	        f.setFolder(parentfolder);
	    	        
	    	        parentfolderid = id;
	    	    		
	    	    }
	    		
	    		int version = fileDao.fileVersion(name,parentfolderid);
	            
	    		if(version == 0)
	    		{
	    			
	    			f.setVersion(0+1);
	    			f = fileDao.saveFile(f);
	    			
	    			return "File upload Successful.";
	    		}
	    		
	    		else
	    		{
	    			
	    			f.setVersion((version+1));
	    			f = fileDao.saveFile(f);
	    			
	    			return "File upload Successful.";
	    		}
	    		
	    	}
	    	
	    	else
	    	
	    		return "File upload Failed"; 
    	}
    	
    	catch(Exception e)
    	{
    		return "File upload Failed";
    	}
	}
    
    @GetMapping(value= {"/download/{name}", "/download/{name}/{version}"})
    public @ResponseBody String get(@PathVariable String name, @PathVariable(required=false) Integer version) throws IOException
    {
    	
    	File f;
    	if(version == null)
    	{
    		f =  (File) entityManager.createNativeQuery( "select * from files where name = '" + name + "' order by version desc limit 0,1", File.class )
    				.getSingleResult();
    		
    		
    	}
    	else
    	{
    		f =  (File) entityManager.createNativeQuery( " select * from files where name = '" + name + "' and version = "+ version, File.class )
			.getSingleResult();
    	}
    	
    	try
    	{
    	
				byte[] bytes = f.getData();
				
				String mimeType = f.getType();
				
				String[] arr = mimeType.split("/"); 
				
				String home = System.getProperty("user.home");
				
				writeBytesToFile(home+"/Downloads/" + name + "."+ arr[1], bytes);
				
				return "File downloaded.";
			
    	}
    	
    	catch(Exception e)
    	{
    		return e.toString();
    	}
		
		
    	
    }
    
    private static void writeBytesToFile(String fileOutput, byte[] bytes) throws IOException {

            try (FileOutputStream fos = new FileOutputStream(fileOutput)) {
                fos.write(bytes);
            }

     }
    
    @GetMapping("/delete/{name}")
    @Transactional
    public @ResponseBody String delete(@PathVariable String name) throws IOException
    {
    	
    	List<File> f =  entityManager.createQuery( " from File where name = '" + name + "'", File.class )
				.getResultList();
    	
    	try {
    		for(File f1 : f)
	    	{
	    		entityManager.remove(f1);
	    	
	    	}
    		
    		return "File successfully deleted.";
    	}
    	
    	catch(Exception e) 
    	{
    		
    		return "No file found.";
    		
    	}
    	
    }
    
    
    
}
    
    

