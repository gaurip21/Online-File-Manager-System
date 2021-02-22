package cs5220stu23.hw3.model.dao.jpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs5220stu23.hw3.model.Folder;
import cs5220stu23.hw3.model.dao.FolderDao;



@Repository
public class FolderDaoImpl implements FolderDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	int counter = 0;

	@Override
	public List<Folder> getFolders() {
		
		return entityManager.createQuery( "from Folder where folder_id = null", Folder.class )
				.getResultList();
		
	}
	
	@Override
    public Folder getFolder( Integer id )
    {
		return entityManager.find( Folder.class, id );
    }
	
	@Override
    public List<Folder> getFolders( Integer id )
    {
		return entityManager.createQuery( "from Folder where folder_id = "+id, Folder.class )
				.getResultList();
    }

    @Override
    @Transactional
    public Folder saveFolder( Folder folder )
    {
        return entityManager.merge( folder );
    }
    
    @Override
    @Transactional
    public int deleteFolder(Folder f)
    {
    	
    	try {
	    	counter++;
			
			List<Folder> childrenFolder = entityManager.createQuery("from Folder where folder_id = :parentId", Folder.class).setParameter("parentId", f).getResultList();
			
			for( Folder folder : childrenFolder )
	        { 
				
				deleteFolder(folder);
				
			}
			
			entityManager.remove(f);
			
			return counter;
    	}
    	
    	catch(Exception e)
    	{
    		return counter;
    	}
		
    	
    }

		
}
