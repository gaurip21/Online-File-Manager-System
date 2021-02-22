package cs5220stu23.hw3.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import cs5220stu23.hw3.model.Folder;
import cs5220stu23.hw3.model.User;

@Entity
@Table(name = "files")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = true)
    private String type;
    
    @Column(name="name")
    private String name;

    @Column(nullable = true)
    private long size;
    
    @Column(name="data")
    private byte[] data;
    
    @Column(name="location",nullable = true)
    private String location;
    
    @Column(name="status",nullable = true)
    private int status;
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable = true)
    private User user_id;
    
    @ManyToOne
    @JoinColumn(name="folder_id", nullable = true)
	private Folder parentFolderId;
    
    @Column(name="version", nullable= true)
    private Integer version;

	public Integer getVersion() {
		
		return version;
	}

	public void setVersion(Integer version) {
		
		this.version = version;
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
	
	public byte[] getData() {
		
		return data;
		
	}

	public void setData(byte[] data) {
		
		this.data = data;
		
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

	public void setSize(long l) {
		
		this.size = l;
		
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

	public User getUser() {
		
		return user_id;
		
	}

	public void setUser(User user) {
		
		this.user_id = user;
		
	}

	public Folder getFolder() {
		
		return parentFolderId;
		
	}

	public void setFolder(Folder folder) {
		
		this.parentFolderId = folder;
		
	}
   
   
}
