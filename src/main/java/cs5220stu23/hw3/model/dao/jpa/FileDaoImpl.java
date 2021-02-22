package cs5220stu23.hw3.model.dao.jpa;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cs5220stu23.hw3.model.File;
import cs5220stu23.hw3.model.FileDto;
import cs5220stu23.hw3.model.Folder;
import cs5220stu23.hw3.model.dao.FileDao;


@Repository
public class FileDaoImpl implements FileDao {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<File> getFiles() {
		
		return entityManager.createQuery( "from File where folder_id = null", File.class )
				.getResultList();
		
	}
	
	@Override
    public List<File> getFile( Integer id )
    {
		 return entityManager.createQuery( "from File where folder_id = "+id, File.class )
				.getResultList();
		
	}

    @Override
    @Transactional
    public File saveFile( File file )
    {
        return entityManager.merge( file );
    }

	@Override
	public int fileVersion(String filename, int parentfolderid) {
		
		int version;
		
		
		if(parentfolderid != 0 )
		{
			try {
			version = (Integer) entityManager.createNativeQuery( "select version from files where name = '" + filename + " and folder_id = "+parentfolderid+"' order by version desc limit 0,1" )
				.getSingleResult();
			}
			catch(Exception e){
				version = 0;
			}
			
		}
		else {
			try {
				version =  (Integer) entityManager.createNativeQuery( "select version from files where name = '" + filename + "' order by version desc limit 0,1")
					.getSingleResult();
			}
			catch(Exception e)
			{
				version = 0;
			}
		}
		return version;
		
	}
	
	
	
	
}
