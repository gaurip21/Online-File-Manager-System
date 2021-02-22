  package cs5220stu23.hw3.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class FolderDto {
	
	private Integer id;

    private String name;

    private String location;
    
    private int status;
    
    private Integer userId;

    private String userName;
    
    private Integer parentfolderId;
    
    private String parentfolderName;
    
    public FolderDto() {
		
	}
	
	public FolderDto( Folder folder )
    {
		id = folder.getId();
        
        name = folder.getName();
        
        location = folder.getLocation();
        
        status = folder.getStatus();
        
        if( folder.getUser() != null )
        {
        	userId = folder.getUser().getId();
        	
        	userName = folder.getUser().getName();
        }
        
        if(folder.getParentFolder() != null)
        {
        	
        	parentfolderId = folder.getParentFolder().getId();
        	
        	parentfolderName = folder.getParentFolder().getName();
        	
        }
    }

	

	public Integer getParentFolderId() {
		
		return parentfolderId; 
		
	}

	public void setParentFolderId(Integer folderId) {
		
		this.parentfolderId = folderId;
		
	}

	public String getParentFolderName() {
		
		return parentfolderName;
		
	}

	public void setParentFolderName(String folderName) {
		
		this.parentfolderName = folderName;
		
	}
	
	public Integer getId() {
		
		return id;
		
	}

	public void setId(Integer id) {
		
		this.id = id;
		
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public String getLocation() {
		
		return location;
		
	}

	public void setLocation(String location) {
		
		this.location = location;
		
	}

	public int getStatus() {
		
		return status;
		
	}

	public void setStatus(int status) {
		
		this.status = status;
		
	}

	public Integer getUserId() {
		
		return userId;
		
	}

	public void setUserId(Integer userId) {
		
		this.userId = userId;
		
	}

	public String getUserName() {
		
		return userName;
		
	}

	public void setUserName(String userName) {
		
		this.userName = userName;
		
	}

}
