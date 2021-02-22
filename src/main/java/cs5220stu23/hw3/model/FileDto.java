package cs5220stu23.hw3.model;

import cs5220stu23.hw3.model.File;

public class FileDto {
	
	private Integer id;
	
	private String name;

    private String type;

    private long size;
    
    private String location;
    
    private byte[] data;
    
    private int status;
    
    private Integer userId;

    private String userName;
    
    private Integer parentFolderId;
    
    private String parentFolderName;
    
    private Integer version;

	public Integer getVersion() {
		
		return version;
	}

	public void setVersion(Integer version) {
		
		this.version = version;
	}

	public FileDto() {
		
	}
	
	public FileDto( File file )
    {
        id = file.getId();
        
        name = file.getName();
        
        type = file.getType();
        
        size = file.getSize();
        
        location = file.getLocation();
        
        status = file.getStatus();
        
        data = file.getData();
        
        version = file.getVersion();
        
        if( file.getUser() != null )
        {
        	
        	userId = file.getUser().getId();
        	
        	userName = file.getUser().getName();
        	
        }
        
        if( file.getFolder() != null )
        {
        	
        	parentFolderId = file.getFolder().getId();
        	
        	parentFolderName = file.getFolder().getName();
        	
        }
    }
	
	
	public Integer getId() {
		
		return id;
		
	}

	public void setId(Integer id) {
		
		this.id = id;
		
	}

	public Integer getParentFolderId() {
		
		return parentFolderId;
		
	}

	public void setParentFolderId(Integer folderId) {
		
		this.parentFolderId = folderId;
		
	}

	public String getParentFolderName() {
		
		return parentFolderName;
		
	}

	public void setParentFolderName(String folderName) {
		
		this.parentFolderName = folderName;
		
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public String getType() {
		
		return type;
		
	}

	public void setType(String type) {
		
		this.type = type;
		
	}

	public long getSize() {
		
		return size;
		
	}

	public void setSize(int size) {
		
		this.size = size;
		
	}
	
	public byte[] getData() {
		
		return data;
		
	}

	public void setData(byte[] data) {
		
		this.data = data;
		
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
