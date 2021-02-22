package cs5220stu23.hw3.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cs5220stu23.hw3.model.User;

@Entity
@Table(name = "folders")
public class Folder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(nullable = true)
    private String location;
    
    @Column(nullable = true)
    private int status;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable = true)
    private User user;
    
    @ManyToOne
    @JoinColumn(name="folder_id", nullable = true)
    private Folder parentfolder;
    
    
    @OneToMany(mappedBy = "parentfolder", cascade = {CascadeType.ALL}, orphanRemoval = true)
    protected Set<Folder> childrenFolder;

	public Set<Folder> getChildrenFolder() {
		
		return childrenFolder;
		 
	}

	public void setChildrenFolder(Set<Folder> childrenFolder) {
		
		this.childrenFolder = childrenFolder;
		
	}

	public Folder getParentFolder() {
		
		return parentfolder;
		
	}

	public void setParentFolder(Folder folder) {
		
		this.parentfolder = folder;
		
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

	public User getUser() {
		
		return user;
		
	}

	public void setUser(User user) {
		
		this.user = user;
		
	}
    
}